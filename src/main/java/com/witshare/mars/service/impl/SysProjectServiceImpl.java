package com.witshare.mars.service.impl;


import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.witshare.mars.config.CurrentThreadContext;
import com.witshare.mars.constant.*;
import com.witshare.mars.dao.mysql.*;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.job.Task;
import com.witshare.mars.pojo.domain.*;
import com.witshare.mars.pojo.dto.*;
import com.witshare.mars.pojo.vo.SysProjectBeanVo;
import com.witshare.mars.pojo.vo.SysProjectListVo;
import com.witshare.mars.service.ProjectWebSiteService;
import com.witshare.mars.service.SysProjectService;
import com.witshare.mars.service.SysUserService;
import com.witshare.mars.util.JsonUtils;
import com.witshare.mars.util.RedisKeyUtil;
import com.witshare.mars.util.WitshareUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static com.witshare.mars.constant.CommonStatisticItems.PROJECT_DETAIL_CN;
import static com.witshare.mars.constant.CommonStatisticItems.PROJECT_DETAIL_EN;


/**
 * @see SysProjectService
 **/
@Service
public class SysProjectServiceImpl implements SysProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysProjectServiceImpl.class);
    private static final long DEFULT_LIMIT = 1000;
    private static Gson gson = new Gson();
    @Autowired
    private SysProjectMapper sysProjectMapper;
    @Autowired
    private ProjectDescriptionCnMapper projectDescriptionZhMapper;
    @Autowired
    private ProjectDescriptionEnMapper projectDescriptionEnMapper;
    @Autowired
    private StaticProjectDescriptionMapper staticProjectDescriptionMapper;
    @Autowired
    private StaticSysProjectMapper staticSysProjectMapper;
    @Autowired
    private StaticProjectWebsiteMapper staticProjectWebsiteMapper;
    @Autowired
    private QingObjStoreAWS3 qingObjStoreAWS3;
    @Autowired
    private PropertiesConfig propertiesConfig;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ProjectWebSiteService projectWebSiteService;
    @Autowired
    private Task task;
    @Autowired
    private RedisCommonDao redisCommonDao;

    /**
     * @see SysProjectService#getStarProjects()
     **/
    @Override
    public List<ProjectPageBean> getStarProjects() {
        //找缓存
        ProjectReqBean projectReqBean = new ProjectReqBean();
        projectReqBean.setStarProject(Boolean.TRUE);
        projectReqBean.setProjectStatus(EnumStatus.Valid.getValue());
        projectReqBean.setPageNum(1);
        projectReqBean.setPageSize(8);
        PageInfo<ProjectPageBean> pageInfo = selectSysProjects(projectReqBean);
        List<ProjectPageBean> projectPageBeans = pageInfo.getList();
        return projectPageBeans;
    }

    /**
     * @see SysProjectService#selectSysProjects(ProjectReqBean)
     **/
    @Override
    public PageInfo<ProjectPageBean> selectSysProjects(ProjectReqBean projectReqBean) {
        if (projectReqBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String i18N = CurrentThreadContext.getI18N();
        String indexProjectKey = RedisKeyUtil.getIndexProjectKey();
        //首页项目 则 找缓存
        if (projectReqBean.getStarProject() != null && projectReqBean.getStarProject()) {
            String starProjectString = null;
            starProjectString = redisCommonDao.getHash(indexProjectKey, i18N);
            if (starProjectString != null) {
                List<ProjectPageBean> projectPageBeanList = gson.fromJson(starProjectString, new TypeToken<List<ProjectPageBean>>() {
                }.getType());

                PageInfo<ProjectPageBean> pageInfo = new PageInfo<>();
                long totalCount = sysProjectMapper.countByExample(new SysProjectExample());
                pageInfo.setList(projectPageBeanList);
                pageInfo.setTotal(totalCount);
                return pageInfo;
            }
        }
        if (projectReqBean.getPageNum() == null || projectReqBean.getPageSize() == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        if (StringUtil.isNotEmpty(projectReqBean.getOrderCondition())) {
            projectReqBean.setOrderCondition(OrderCondition.getCondition(projectReqBean.getOrderCondition()));
        }

        Integer pageNum = projectReqBean.getPageNum();
        Integer pageSize = projectReqBean.getPageSize();
        //设置要查询的表
        projectReqBean.setTableName(CurrentThreadContext.getInternationalTableName());
        PageInfo<ProjectPageBean> projectPageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> staticSysProjectMapper.selectProjectList(projectReqBean));
        List<ProjectPageBean> projectList = projectPageInfo.getList();
        SysUserBean currentUser = sysUserService.getCurrentUser();
        for (ProjectPageBean project : projectList) {
            //拼接图片url
            project.setProjectGrade(getPictureUrl(project.getProjectGrade()));
            project.setProjectLogoLink(getPictureUrl(project.getProjectLogoLink()));
            project.setProjectImgLink(getPictureUrl(project.getProjectImgLink()));
            //设置评级
            project.setProjectGrade(EnumProjectGrade.getProGradeByScore(project.getProjectGradeScore()).getGradeStr());


        }
        //存缓存
        if (projectReqBean.getStarProject() != null && projectReqBean.getStarProject()) {
            redisCommonDao.putHash(indexProjectKey, i18N, JsonUtils.objToJsonByGson(projectList));
        }
        return projectPageInfo;

    }

    /**
     * @see SysProjectService#selectProject(String)
     **/
    @Override
    public ProjectRespBean selectProject(String projectGid) {
        if (StringUtils.isEmpty(projectGid)) {
            throw new WitshareException(EnumResponseText.ErrorId);
        }

        String projectStatisticKey = RedisKeyUtil.getProjectStatisticKey(projectGid);

        String internationalTableName = CurrentThreadContext.getInternationalTableName();

        String projectDetail = null;

        if (EnumI18NProject.PROJECT_DESCRIPTION_EN.getTableName().equals(internationalTableName)) {
            projectDetail = redisCommonDao.getHash(projectStatisticKey, PROJECT_DETAIL_EN);
        } else {
            projectDetail = redisCommonDao.getHash(projectStatisticKey, PROJECT_DETAIL_CN);
        }
        if (projectDetail != null) {
            ProjectRespBean respBean = gson.fromJson(projectDetail, ProjectRespBean.class);
            return respBean;
        }

        SysProjectExample sysProjectExample = new SysProjectExample();
        sysProjectExample.or().andProjectGidEqualTo(projectGid);
        List<SysProject> sysProjects = sysProjectMapper.selectByExample(sysProjectExample);
        if (CollectionUtils.isEmpty(sysProjects)) {
            throw new WitshareException(EnumResponseText.ErrorId);
        }
        SysProject staticSysProject = sysProjects.get(0);


        //查询网站链接列表
        List<Map<String, Object>> socialWebsiteList = staticProjectWebsiteMapper.selectWebSiteList(projectGid, 0);
        List<Map<String, Object>> exchangeWebsiteList = staticProjectWebsiteMapper.selectWebSiteList(projectGid, 1);
        //拼接路径url
        resolveQYUrl(socialWebsiteList);
        resolveQYUrl(exchangeWebsiteList);

        ProjectRespBean projectRespBean = new ProjectRespBean();
        BeanUtils.copyProperties(staticSysProject, projectRespBean);


        //设置官网图标 和 白皮书图标  以交给前端显示了
//        projectRespBean.setOfficialLogoLink(getPictureUrl(propertiesConfig.getDefaultOfficialLogo()));
//        projectRespBean.setWhitePaperLogoLink(getPictureUrl(propertiesConfig.getDefaultWhitepaperLogo()));


        SysUserBean currentUser = sysUserService.getCurrentUser();


        projectRespBean.setGradeReportLink(getPictureUrl(projectRespBean.getGradeReportLink()));
        projectRespBean.setProjectLogoLink(getPictureUrl(projectRespBean.getProjectLogoLink()));
        projectRespBean.setProjectImgLink(getPictureUrl(projectRespBean.getProjectImgLink()));

        projectRespBean.setSocialList(socialWebsiteList);
        projectRespBean.setExchangeList(exchangeWebsiteList);


        if (EnumI18NProject.PROJECT_DESCRIPTION_EN.getTableName().equals(internationalTableName)) {
            redisCommonDao.putHash(projectStatisticKey, PROJECT_DETAIL_EN, gson.toJson(projectRespBean));
            redisCommonDao.setExpireByDay(projectStatisticKey, 7);
        } else {
            redisCommonDao.putHash(projectStatisticKey, PROJECT_DETAIL_CN, gson.toJson(projectRespBean));
        }

        return projectRespBean;
    }

    /**
     * 解析图片地址为连接
     **/
    public void resolveQYUrl(List<Map<String, Object>> list) {
        list.forEach(e -> {
            String pictureUrl = (String) e.get(SysProjectBean.PICTURE_URL);

            e.put(SysProjectBean.PICTURE_URL, this.getPictureUrl(pictureUrl));

        });
    }

    /**
     * @see SysProjectService#getPictureUrl(String)
     */
    @Override
    public String getPictureUrl(String source) {
        if (source == null)
            return null;
        else
            return propertiesConfig.qingyunHttpUrl + source;
    }

    /**
     * @see SysProjectService#save(Map)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Map<String, Object> requestBody) {
        if (requestBody == null || requestBody.size() < 10) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String projectNameZh = (String) requestBody.get(SysProjectBean.PROJECT_NAME_ZH);
        String projectNameEn = (String) requestBody.get(SysProjectBean.PROJECT_NAME_EN);

        String token = (String) requestBody.get(SysProjectBean.TOKEN);
        SysProjectBean sysProjectBean1 = new SysProjectBean();
        String gradeStr = (String) requestBody.get(SysProjectBean.GRADE_STR);
        String log = (String) requestBody.get(SysProjectBean.LOG_STR);//need storage
        String pdfZh = (String) requestBody.get(SysProjectBean.PDF_ZH);//need storage
        String pdfZhName = (String) requestBody.get(SysProjectBean.PDF_ZH_NAME);
        String pdfEn = (String) requestBody.get(SysProjectBean.PDF_EN);//need storage
        String pdfEnName = (String) requestBody.get(SysProjectBean.PDF_EN_NAME);
        String view = (String) requestBody.get(SysProjectBean.VIEW);//need storage
        String instructionZh = (String) requestBody.get(SysProjectBean.INSTRUCTION_ZH);
        String instructionEn = (String) requestBody.get(SysProjectBean.INSTRUCTION_EN);
        String contentZh = (String) requestBody.get(SysProjectBean.CONTENT_ZH);
        String contentEn = (String) requestBody.get(SysProjectBean.CONTENT_EN);
        String officialLink = (String) requestBody.get(SysProjectBean.OFFICIAL_LINK);
        String whitePaperLinkZh = (String) requestBody.get(SysProjectBean.WHITE_PAPER_LINK_ZH);
        String whitePaperLinkEn = (String) requestBody.get(SysProjectBean.WHITE_PAPER_LINK_EN);
        String locationZh = (String) requestBody.get(SysProjectBean.LOCATION_ZH);
        String locationEn = (String) requestBody.get(SysProjectBean.LOCATION_EN);
        String accepting = (String) requestBody.get(SysProjectBean.ACCEPTING);

        Integer projectType = Integer.parseInt((String) requestBody.get(SysProjectBean.PROJECT_TYPE));
        Integer ico = Integer.parseInt((String) requestBody.get(SysProjectBean.ICO));
        Integer teamScore = Integer.parseInt((String) requestBody.get(SysProjectBean.TEAM_SCORE));
        double productScore = Double.parseDouble((String) requestBody.get(SysProjectBean.PRODUCT_SCORE));
        double scheduleScore = Double.parseDouble((String) requestBody.get(SysProjectBean.SCHEDULE_SCORE));
        double commercialSubstanceScore = Double.parseDouble((String) requestBody.get(SysProjectBean.COMMERCIAL_SUBSTANCE_SCORE));
        double tokensOperationScore = Double.parseDouble((String) requestBody.get(SysProjectBean.TOKENS_OPERATION_SCORE));

        List<WebSiteManagementBean> exchangeList = (List<WebSiteManagementBean>) requestBody.get(SysProjectBean.EXCHANGE_LIST);
        List<WebSiteManagementBean> socialList = (List<WebSiteManagementBean>) requestBody.get(SysProjectBean.SOCIAL_LIST);
        LinkedList<WebSiteManagementBean> websiteList = new LinkedList<>();
        websiteList.addAll(exchangeList);
        websiteList.addAll(socialList);

        EnumProjectGrade proGrade = EnumProjectGrade.getProGradeBygrade(gradeStr);
        //校验必要项是否完整
        if (StringUtils.isEmpty(projectNameZh)
                || StringUtils.isEmpty(projectNameEn)
                || StringUtils.isEmpty(token)
                || StringUtils.isEmpty(log)
                || proGrade == null
                || StringUtils.isEmpty(pdfZh)
                || StringUtils.isEmpty(pdfZhName)
                || StringUtils.isEmpty(pdfEn)
                || StringUtils.isEmpty(pdfEnName)
                || StringUtils.isEmpty(view)
                || StringUtils.isEmpty(instructionZh)
                || StringUtils.isEmpty(instructionEn)
                || StringUtils.isEmpty(contentZh)
                || StringUtils.isEmpty(contentEn)
                || StringUtils.isEmpty(officialLink)
                || StringUtils.isEmpty(whitePaperLinkZh)
                || StringUtils.isEmpty(whitePaperLinkEn)
                || StringUtils.isEmpty(locationZh)
                || StringUtils.isEmpty(locationEn)
                || StringUtils.isEmpty(accepting)
                || projectType <= 0
                || !WitshareUtils.rangeInDefined(ico, 0, 2)
                || !WitshareUtils.rangeInDefined(teamScore, 0, 100)
                || !WitshareUtils.rangeInDefined(productScore, 0, 100)
                || !WitshareUtils.rangeInDefined(scheduleScore, 0, 100)
                || !WitshareUtils.rangeInDefined(commercialSubstanceScore, 0, 100)
                || !WitshareUtils.rangeInDefined(tokensOperationScore, 0, 100)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        SysProjectBean sysProjectBean = new SysProjectBean();
        sysProjectBean.setProjectType(projectType);
        sysProjectBean.setIco(ico);
        sysProjectBean.setTeamScore(teamScore);
        sysProjectBean.setProductScore((int) (productScore * 10));
        sysProjectBean.setScheduleScore((int) (scheduleScore * 10));
        sysProjectBean.setCommercialSubstanceScore((int) (commercialSubstanceScore * 10));
        sysProjectBean.setTokensOperationScore((int) (tokensOperationScore * 10));
        sysProjectBean.setToken(token);
        sysProjectBean.setProjectNameZh(projectNameZh);
        sysProjectBean.setProjectNameEn(projectNameEn);
        sysProjectBean.setGrade(new BigDecimal(proGrade.getMin() + "." + RandomUtils.nextInt(0, 9)));
        sysProjectBean.setInstructionZh(instructionZh);
        sysProjectBean.setInstructionEn(instructionEn);
        sysProjectBean.setContentZh(contentZh);
        sysProjectBean.setContentEn(contentEn);
        sysProjectBean.setOfficialLink(officialLink);
        sysProjectBean.setWhitePaperLinkZh(whitePaperLinkZh);
        sysProjectBean.setWhitePaperLinkEn(whitePaperLinkEn);
        sysProjectBean.setLocationEn(locationEn);
        sysProjectBean.setLocationZh(locationZh);
        sysProjectBean.setAccepting(accepting);
        sysProjectBean.setWebsiteList(websiteList);
        sysProjectBean.setPdfEnName(pdfEnName);
        sysProjectBean.setPdfZhName(pdfZhName);
        //检验是否有主键重复的
        checkExist(sysProjectBean);
        //多线程提交对象存储，返回link
        CountDownLatch countDownLatch = new CountDownLatch(4);
        task.qingYunStorage(sysProjectBean, pdfEn, EnumStorage.PdfEn, countDownLatch);
        task.qingYunStorage(sysProjectBean, pdfZh, EnumStorage.PdfZh, countDownLatch);
        task.qingYunStorage(sysProjectBean, log, EnumStorage.Log, countDownLatch);
        task.qingYunStorage(sysProjectBean, view, EnumStorage.View, countDownLatch);
        try {
            countDownLatch.await();
        } catch (Exception e) {
            LOGGER.error("qingYunStorage,error", e);
            throw new WitshareException(e.getMessage());
        }
        //存表
        staticSysProjectMapper.saveOrUpdate(sysProjectBean);

        staticProjectDescriptionMapper.saveOrUpdate(sysProjectBean);

        staticProjectWebsiteMapper.save(sysProjectBean);
    }

    /**
     * 检验名称或token是否已经存在
     */
    private void checkExist(SysProjectBean sysProjectBean) {
        int count = staticSysProjectMapper.checkExist(sysProjectBean);
        if (count > 0) {
            throw new WitshareException(EnumResponseText.ExistNameOrToken);
        }
    }

    /**
     * @see SysProjectService#update(Map)
     */
    @Override
    public void update(Map<String, Object> requestBody) {
        if (requestBody == null || requestBody.size() < 10) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String projectGid = (String) requestBody.get(SysProjectBean.PROJECT_GID);
        String projectNameZh = (String) requestBody.get(SysProjectBean.PROJECT_NAME_ZH);
        String projectNameEn = (String) requestBody.get(SysProjectBean.PROJECT_NAME_EN);
        String token = (String) requestBody.get(SysProjectBean.TOKEN);

        String gradeStr = (String) requestBody.get(SysProjectBean.GRADE_STR);
        String instructionZh = (String) requestBody.get(SysProjectBean.INSTRUCTION_ZH);
        String instructionEn = (String) requestBody.get(SysProjectBean.INSTRUCTION_EN);
        String contentZh = (String) requestBody.get(SysProjectBean.CONTENT_ZH);
        String contentEn = (String) requestBody.get(SysProjectBean.CONTENT_EN);
        String officialLink = (String) requestBody.get(SysProjectBean.OFFICIAL_LINK);
        String whitePaperLinkZh = (String) requestBody.get(SysProjectBean.WHITE_PAPER_LINK_ZH);
        String whitePaperLinkEn = (String) requestBody.get(SysProjectBean.WHITE_PAPER_LINK_EN);
        String locationZh = (String) requestBody.get(SysProjectBean.LOCATION_ZH);
        String locationEn = (String) requestBody.get(SysProjectBean.LOCATION_EN);
        String accepting = (String) requestBody.get(SysProjectBean.ACCEPTING);
        Long projectType = Long.parseLong(requestBody.get(SysProjectBean.PROJECT_TYPE) + "");

        Integer ico = Integer.parseInt(requestBody.get(SysProjectBean.ICO) + "");
        double teamScore = Double.parseDouble(requestBody.get(SysProjectBean.TEAM_SCORE) + "");
        double productScore = Double.parseDouble(requestBody.get(SysProjectBean.PRODUCT_SCORE) + "");
        double scheduleScore = Double.parseDouble(requestBody.get(SysProjectBean.SCHEDULE_SCORE) + "");
        double commercialSubstanceScore = Double.parseDouble(requestBody.get(SysProjectBean.COMMERCIAL_SUBSTANCE_SCORE) + "");
        double tokensOperationScore = Double.parseDouble(requestBody.get(SysProjectBean.TOKENS_OPERATION_SCORE) + "");

        List<WebSiteManagementBean> exchangeList = (List<WebSiteManagementBean>) requestBody.get(SysProjectBean.EXCHANGE_LIST);
        List<WebSiteManagementBean> socialList = (List<WebSiteManagementBean>) requestBody.get(SysProjectBean.SOCIAL_LIST);
        LinkedList<WebSiteManagementBean> websiteList = new LinkedList<>();
        websiteList.addAll(exchangeList);
        websiteList.addAll(socialList);
        EnumProjectGrade proGrade = EnumProjectGrade.getProGradeBygrade(gradeStr);
        //校验必要项是否完整
        if (StringUtils.isEmpty(projectNameZh)
                || StringUtils.isEmpty(projectNameEn)
                || StringUtils.isEmpty(token)
                || proGrade == null
                || StringUtils.isEmpty(instructionZh)
                || StringUtils.isEmpty(instructionEn)
                || StringUtils.isEmpty(contentZh)
                || StringUtils.isEmpty(contentEn)
                || StringUtils.isEmpty(officialLink)
                || StringUtils.isEmpty(whitePaperLinkZh)
                || StringUtils.isEmpty(whitePaperLinkEn)
                || StringUtils.isEmpty(locationZh)
                || StringUtils.isEmpty(locationEn)
                || StringUtils.isEmpty(accepting)
                || projectType == null
                || !WitshareUtils.rangeInDefined(ico, 0, 2)
                || !WitshareUtils.rangeInDefined(teamScore, 0, 100)
                || !WitshareUtils.rangeInDefined(productScore, 0, 100)
                || !WitshareUtils.rangeInDefined(scheduleScore, 0, 100)
                || !WitshareUtils.rangeInDefined(commercialSubstanceScore, 0, 100)
                || !WitshareUtils.rangeInDefined(tokensOperationScore, 0, 100)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }

        SysProjectExample sysProjectExample = new SysProjectExample();
        sysProjectExample.or().andProjectGidEqualTo(projectGid);
        List<SysProject> sysProjects = sysProjectMapper.selectByExample(sysProjectExample);
        if (CollectionUtils.isEmpty(sysProjects)) {
            throw new WitshareException(EnumResponseText.ErrorId);
        }
        Timestamp current = new Timestamp(System.currentTimeMillis());
        SysProjectBean sysProjectBean = new SysProjectBean();
        sysProjectBean.setProjectGid(projectGid);
        sysProjectBean.setProjectNameEn(projectNameEn);
        sysProjectBean.setProjectNameZh(projectNameZh);
        sysProjectBean.setToken(token);
        //检验是否有主键重复的
        checkExist(sysProjectBean);
        String log = (String) requestBody.get(SysProjectBean.LOG_STR);//need storage
        String pdfZh = (String) requestBody.get(SysProjectBean.PDF_ZH);//need storage
        String pdfEn = (String) requestBody.get(SysProjectBean.PDF_EN);//need storage
        String view = (String) requestBody.get(SysProjectBean.VIEW);//need storage
        String pdfZhName = (String) requestBody.get(SysProjectBean.PDF_ZH_NAME);
        String pdfEnName = (String) requestBody.get(SysProjectBean.PDF_EN_NAME);
        CountDownLatch countDownLatch = new CountDownLatch(4);
        if (!StringUtils.isEmpty(pdfEn) && !StringUtils.isEmpty(pdfEnName)) {
            sysProjectBean.setPdfEnName(pdfEnName);
            task.qingYunStorage(sysProjectBean, pdfEn, EnumStorage.PdfEn, countDownLatch);
        } else {
            countDownLatch.countDown();
        }
        if (!StringUtils.isEmpty(pdfZh) && !StringUtils.isEmpty(pdfZhName)) {
            sysProjectBean.setPdfZhName(pdfZhName);
            task.qingYunStorage(sysProjectBean, pdfZh, EnumStorage.PdfZh, countDownLatch);
        } else {
            countDownLatch.countDown();
        }
        if (!StringUtils.isEmpty(log)) {
            task.qingYunStorage(sysProjectBean, log, EnumStorage.Log, countDownLatch);
        } else {
            countDownLatch.countDown();
        }
        if (!StringUtils.isEmpty(view)) {
            task.qingYunStorage(sysProjectBean, view, EnumStorage.View, countDownLatch);
        } else {
            countDownLatch.countDown();
        }
        try {
            countDownLatch.await();
        } catch (Exception e) {
            LOGGER.error("qingYunStorage,error", e);
            throw new WitshareException(e.getMessage());
        }
        sysProjectBean.setWebsiteList(websiteList);
        SysProject sysProject = new SysProject();
        sysProject.setProjectGid(projectGid);
        sysProject.setProjectToken(token);
        sysProject.setProjectLogoLink(sysProjectBean.getLog());
        sysProject.setProjectImgLink(sysProjectBean.getView());
        sysProject.setUpdateTime(current);
        sysProjectMapper.updateByPrimaryKeySelective(sysProject);

        ProjectDescriptionCnExample projectDescriptionZhExample = new ProjectDescriptionCnExample();
        projectDescriptionZhExample.or().andProjectGidEqualTo(projectGid);
        ProjectDescriptionCn projectDescriptionZh = new ProjectDescriptionCn();
        projectDescriptionZh.setProjectName(projectNameZh);
        projectDescriptionZh.setProjectInstruction(instructionZh);
        projectDescriptionZh.setProjectContent(contentZh);
        projectDescriptionZh.setWhitePaperLink(whitePaperLinkZh);
        projectDescriptionZh.setGradeReportLink(sysProjectBean.getPdfZh());
        projectDescriptionZh.setUpdateTime(current);
        projectDescriptionZhMapper.updateByExampleSelective(projectDescriptionZh, projectDescriptionZhExample);

        ProjectDescriptionEnExample projectDescriptionEnExample = new ProjectDescriptionEnExample();
        projectDescriptionEnExample.or().andProjectGidEqualTo(projectGid);
        ProjectDescriptionEn projectDescriptionEn = new ProjectDescriptionEn();
        projectDescriptionEn.setProjectName(projectNameEn);
        projectDescriptionEn.setProjectInstruction(instructionEn);
        projectDescriptionEn.setProjectContent(contentEn);
        projectDescriptionEn.setWhitePaperLink(whitePaperLinkEn);
        projectDescriptionEn.setGradeReportLink(sysProjectBean.getPdfEn());
        projectDescriptionEn.setUpdateTime(current);
        projectDescriptionEnMapper.updateByExampleSelective(projectDescriptionEn, projectDescriptionEnExample);
        staticProjectWebsiteMapper.save(sysProjectBean);

        //清缓存
        redisCommonDao.delRedisKey(RedisKeyUtil.getIndexProjectKey());
        redisCommonDao.delRedisKey(RedisKeyUtil.getProjectStatisticKey(projectGid));
    }


    /**
     * @see SysProjectService#selectManagementList(SysProjectBean)
     */
    @Override
    public PageInfo<SysProjectListVo> selectManagementList(SysProjectBean sysProjectBean) {
        Integer pageNum = sysProjectBean.getPageNum();
        Integer pageSize = sysProjectBean.getPageSize();
        return PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> staticSysProjectMapper.selectManagementList(sysProjectBean));
    }

    /**
     * @see SysProjectService#selectManagementById(Long)
     */
    @Override
    public SysProjectBeanVo selectManagementById(Long id) {
        SysProjectBeanVo sysProjectBeanVo = staticSysProjectMapper.selectManagementById(id);
        //加载所有网站
        String projectGid = sysProjectBeanVo.getProjectGid();
        LinkedList<WebSiteManagementBean> webSiteManagementBeans = projectWebSiteService.select(projectGid);
        sysProjectBeanVo.getWebsiteList().addAll(webSiteManagementBeans);
        //图片
        sysProjectBeanVo.setLog(getPictureUrl(sysProjectBeanVo.getLog()));
        sysProjectBeanVo.setView(getPictureUrl(sysProjectBeanVo.getView()));
        sysProjectBeanVo.setPdfZh(getPictureUrl(sysProjectBeanVo.getPdfZh()));
        sysProjectBeanVo.setPdfEn(getPictureUrl(sysProjectBeanVo.getPdfEn()));
        return sysProjectBeanVo;

    }


    /**
     * @see SysProjectService#getProjectPdf(Long)
     */
    @Override
    public void getProjectPdf(Long projectId) {
        HttpServletResponse response = CurrentThreadContext.getResponse();
        String internationalTableName = CurrentThreadContext.getInternationalTableName();
        if (projectId == null) {
            response.setStatus(HttpServletResponseWrapper.SC_PAYMENT_REQUIRED);
            return;
        }
        ProjectPdfBean pdfBean = staticSysProjectMapper.selectPdfById(internationalTableName, projectId);
        if (pdfBean == null) {
            response.setStatus(HttpServletResponseWrapper.SC_PAYMENT_REQUIRED);
            return;
        }
        String linkName = pdfBean.getLinkName();
        //去掉时间戳
        linkName = linkName.substring(0, linkName.lastIndexOf("?"));
        String token = pdfBean.getToken();
        if (StringUtils.isEmpty(linkName) || StringUtils.isEmpty(token)) {
            response.setStatus(HttpServletResponseWrapper.SC_PAYMENT_REQUIRED);
            return;
        }
        byte[] b = new byte[1024];
        try {
            int namePosition = linkName.lastIndexOf("/");
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment;filename=" + linkName.substring(namePosition + 1));
            InputStream inputStream = qingObjStoreAWS3.getObjects(propertiesConfig.qingyunBucket, linkName);
            OutputStream out = response.getOutputStream();
            while ((inputStream.read(b)) != -1) {
                out.write(b);
            }
            out.flush();
            inputStream.close();
            out.close();
        } catch (IOException e) {
            response.setStatus(HttpServletResponseWrapper.SC_PAYMENT_REQUIRED);
            e.printStackTrace();
        }
    }


    /**
     * @see SysProjectService#delAllProjectCache()
     */
    @Override
    public void delAllProjectCache() {
        SysProjectExample sysProjectExample = new SysProjectExample();
        long count = sysProjectMapper.countByExample(sysProjectExample);
        for (int offset = 0; offset < count; offset += DEFULT_LIMIT) {
            List<String> projectIdList = staticSysProjectMapper.selectAllProjectId(offset, (int) DEFULT_LIMIT);
            if (CollectionUtils.isNotEmpty(projectIdList)) {
                projectIdList.forEach(p -> {
                    String projectStatisticKey = RedisKeyUtil.getProjectStatisticKey(p);
                    redisCommonDao.delRedisKey(projectStatisticKey);
                });
            }
        }
    }


    @Override
    public void hideProject(Long id) {
        if (id == null) {
            throw new WitshareException(EnumResponseText.ErrorId);
        }
        SysProjectBeanVo sysProjectBeanVo = selectManagementById(id);
        if (sysProjectBeanVo == null) {
            throw new WitshareException(EnumResponseText.ErrorId);
        }
        String projectGid = sysProjectBeanVo.getProjectGid();
        staticSysProjectMapper.modifyProjectStatus(id);
        //清缓存
        redisCommonDao.delRedisKey(RedisKeyUtil.getIndexProjectKey());
        redisCommonDao.delRedisKey(RedisKeyUtil.getProjectStatisticKey(projectGid));

    }

}

