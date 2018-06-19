package com.witshare.mars.service.impl;


import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.google.gson.Gson;
import com.witshare.mars.config.CurrentThreadContext;
import com.witshare.mars.constant.*;
import com.witshare.mars.dao.mysql.StaticProjectDescriptionMapper;
import com.witshare.mars.dao.mysql.StaticProjectWebsiteMapper;
import com.witshare.mars.dao.mysql.StaticSysProjectMapper;
import com.witshare.mars.dao.mysql.SysProjectMapper;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.job.Task;
import com.witshare.mars.pojo.domain.SysProject;
import com.witshare.mars.pojo.domain.SysProjectExample;
import com.witshare.mars.pojo.dto.ProjectDescriptionBean;
import com.witshare.mars.pojo.dto.ProjectReqBean;
import com.witshare.mars.pojo.dto.SysProjectBean;
import com.witshare.mars.pojo.vo.SysProjectBeanFrontInfoVo;
import com.witshare.mars.pojo.vo.SysProjectBeanFrontListVo;
import com.witshare.mars.pojo.vo.SysProjectBeanVo;
import com.witshare.mars.pojo.vo.SysProjectListVo;
import com.witshare.mars.service.ProjectDailyInfoService;
import com.witshare.mars.service.ProjectWebSiteService;
import com.witshare.mars.service.QingyunStorageService;
import com.witshare.mars.service.SysProjectService;
import com.witshare.mars.util.RedisKeyUtil;
import com.witshare.mars.util.WitshareUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.witshare.mars.constant.CacheConsts.WHITE_PAPER_LINK;


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
    private StaticProjectDescriptionMapper staticProjectDescriptionMapper;
    @Autowired
    private QingyunStorageService qingyunStorageService;
    @Autowired
    private StaticSysProjectMapper staticSysProjectMapper;
    @Autowired
    private StaticProjectWebsiteMapper staticProjectWebsiteMapper;
    @Autowired
    private PropertiesConfig propertiesConfig;
    @Autowired
    private ProjectWebSiteService projectWebSiteService;
    @Autowired
    private ProjectDailyInfoService projectDailyInfoService;
    @Autowired
    private Task task;
    @Autowired
    private RedisCommonDao redisCommonDao;


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


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String jsonBody) {
        if (StringUtils.isEmpty(jsonBody)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }

        SysProjectBean sysProjectBean = new Gson().fromJson(jsonBody, SysProjectBean.class);
        BigDecimal softCap = sysProjectBean.getSoftCap();
        BigDecimal startPriceRate = sysProjectBean.getStartPriceRate();
        BigDecimal endPriceRate = sysProjectBean.getEndPriceRate();
        Timestamp endTime = new Timestamp(sysProjectBean.getEndTimeLong());
        Timestamp startTime = new Timestamp(sysProjectBean.getStartTimeLong());
        BigDecimal hardCap = sysProjectBean.getHardCap();
        BigDecimal minPurchaseAmount = sysProjectBean.getMinPurchaseAmount();
        String token = sysProjectBean.getProjectToken();
        String log = sysProjectBean.getLog();
        String instructionEn = sysProjectBean.getInstructionEn();
        String contentEn = sysProjectBean.getContentEn();
        String officialLink = sysProjectBean.getOfficialLink();
        String facebook = sysProjectBean.getFacebook();
        String twitter = sysProjectBean.getTwitter();
        String biYong = sysProjectBean.getBiYong();
        String gitHub = sysProjectBean.getGitHub();
        String reddit = sysProjectBean.getReddit();
        String telegram = sysProjectBean.getTelegram();
        String whitePaperLinkEn = sysProjectBean.getWhitePaperLinkEn();
        Timestamp current = new Timestamp(System.currentTimeMillis());
        //校验必要项是否完整
        if (StringUtils.isEmpty(sysProjectBean.getProjectNameEn())
                || StringUtils.isEmpty(sysProjectBean.getTokenAddress())
                || StringUtils.isEmpty(sysProjectBean.getProjectAddress())
                || startTime.after(endTime)
                || current.after(startTime)
                || startPriceRate == null || startPriceRate.compareTo(endPriceRate) < 0
                || endPriceRate == null || endPriceRate.compareTo(BigDecimal.ZERO) < 0
                || softCap == null || softCap.compareTo(BigDecimal.ZERO) <= 0
                || hardCap == null || softCap.compareTo(hardCap) > 0
                || minPurchaseAmount == null || softCap.compareTo(minPurchaseAmount) <= 0
                || StringUtils.isEmpty(token)
                || StringUtils.isEmpty(log)
//                || StringUtils.isEmpty(view)
                || StringUtils.isEmpty(instructionEn)
                || StringUtils.isEmpty(contentEn)
                || StringUtils.isEmpty(officialLink)
                || StringUtils.isEmpty(facebook)
                || StringUtils.isEmpty(twitter)
                || StringUtils.isEmpty(biYong)
                || StringUtils.isEmpty(gitHub)
                || StringUtils.isEmpty(reddit)
                || StringUtils.isEmpty(telegram)
                || StringUtils.isEmpty(whitePaperLinkEn)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }

        //检验是否有主键重复的
        checkExist(sysProjectBean);
        sysProjectBean.setProjectGid(WitshareUtils.getUUID());

        //存储s3
        String objectName = qingyunStorageService.uploadToQingyun(log, sysProjectBean.getProjectGid(), EnumStorage.Log);
        sysProjectBean.setProjectLogoLink(objectName);

        //todo 获取平台地址
        sysProjectBean.setPlatformAddress("")
                .setProjectImgLink("")
                .setIsAvailable(1)
                .setStartTime(startTime)
                .setEndTime(endTime)
                .setCreateTime(current)
                .setUpdateTime(current);
        SysProject sysProject = new SysProject();
        BeanUtils.copyProperties(sysProjectBean, sysProject);

        //存表
        sysProjectMapper.insertSelective(sysProject);

        staticProjectDescriptionMapper.saveOrUpdate(sysProjectBean);

        staticProjectWebsiteMapper.saveOrUpdate(sysProjectBean);
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
//        if (requestBody == null || requestBody.size() < 10) {
//            throw new WitshareException(EnumResponseText.ErrorRequest);
//        }
//        String projectGid = (String) requestBody.get(SysProjectBean.PROJECT_GID);
//        String projectNameZh = (String) requestBody.get(SysProjectBean.PROJECT_NAME_ZH);
//        String projectNameEn = (String) requestBody.get(SysProjectBean.PROJECT_NAME_EN);
//        String token = (String) requestBody.get(SysProjectBean.TOKEN);
//
//        String gradeStr = (String) requestBody.get(SysProjectBean.GRADE_STR);
//        String instructionZh = (String) requestBody.get(SysProjectBean.INSTRUCTION_ZH);
//        String instructionEn = (String) requestBody.get(SysProjectBean.INSTRUCTION_EN);
//        String contentZh = (String) requestBody.get(SysProjectBean.CONTENT_ZH);
//        String contentEn = (String) requestBody.get(SysProjectBean.CONTENT_EN);
//        String officialLink = (String) requestBody.get(SysProjectBean.OFFICIAL_LINK);
//        String whitePaperLinkZh = (String) requestBody.get(SysProjectBean.WHITE_PAPER_LINK_ZH);
//        String whitePaperLinkEn = (String) requestBody.get(SysProjectBean.WHITE_PAPER_LINK_EN);
//        String locationZh = (String) requestBody.get(SysProjectBean.LOCATION_ZH);
//        String locationEn = (String) requestBody.get(SysProjectBean.LOCATION_EN);
//        String accepting = (String) requestBody.get(SysProjectBean.ACCEPTING);
//        Long projectType = Long.parseLong(requestBody.get(SysProjectBean.PROJECT_TYPE) + "");
//
//        Integer ico = Integer.parseInt(requestBody.get(SysProjectBean.ICO) + "");
//        double teamScore = Double.parseDouble(requestBody.get(SysProjectBean.TEAM_SCORE) + "");
//        double productScore = Double.parseDouble(requestBody.get(SysProjectBean.PRODUCT_SCORE) + "");
//        double scheduleScore = Double.parseDouble(requestBody.get(SysProjectBean.SCHEDULE_SCORE) + "");
//        double commercialSubstanceScore = Double.parseDouble(requestBody.get(SysProjectBean.COMMERCIAL_SUBSTANCE_SCORE) + "");
//        double tokensOperationScore = Double.parseDouble(requestBody.get(SysProjectBean.TOKENS_OPERATION_SCORE) + "");
//
//        List<WebSiteManagementBean> exchangeList = (List<WebSiteManagementBean>) requestBody.get(SysProjectBean.EXCHANGE_LIST);
//        List<WebSiteManagementBean> socialList = (List<WebSiteManagementBean>) requestBody.get(SysProjectBean.SOCIAL_LIST);
//        LinkedList<WebSiteManagementBean> websiteList = new LinkedList<>();
//        websiteList.addAll(exchangeList);
//        websiteList.addAll(socialList);
//        EnumProjectGrade proGrade = EnumProjectGrade.getProGradeBygrade(gradeStr);
//        //校验必要项是否完整
//        if (StringUtils.isEmpty(projectNameZh)
//                || StringUtils.isEmpty(projectNameEn)
//                || StringUtils.isEmpty(token)
//                || proGrade == null
//                || StringUtils.isEmpty(instructionZh)
//                || StringUtils.isEmpty(instructionEn)
//                || StringUtils.isEmpty(contentZh)
//                || StringUtils.isEmpty(contentEn)
//                || StringUtils.isEmpty(officialLink)
//                || StringUtils.isEmpty(whitePaperLinkZh)
//                || StringUtils.isEmpty(whitePaperLinkEn)
//                || StringUtils.isEmpty(locationZh)
//                || StringUtils.isEmpty(locationEn)
//                || StringUtils.isEmpty(accepting)
//                || projectType == null
//                || !WitshareUtils.rangeInDefined(ico, 0, 2)
//                || !WitshareUtils.rangeInDefined(teamScore, 0, 100)
//                || !WitshareUtils.rangeInDefined(productScore, 0, 100)
//                || !WitshareUtils.rangeInDefined(scheduleScore, 0, 100)
//                || !WitshareUtils.rangeInDefined(commercialSubstanceScore, 0, 100)
//                || !WitshareUtils.rangeInDefined(tokensOperationScore, 0, 100)) {
//            throw new WitshareException(EnumResponseText.ErrorRequest);
//        }
//
//        SysProjectExample sysProjectExample = new SysProjectExample();
//        sysProjectExample.or().andProjectGidEqualTo(projectGid);
//        List<SysProject> sysProjects = sysProjectMapper.selectByExample(sysProjectExample);
//        if (CollectionUtils.isEmpty(sysProjects)) {
//            throw new WitshareException(EnumResponseText.ErrorId);
//        }
//        Timestamp current = new Timestamp(System.currentTimeMillis());
//        SysProjectBean sysProjectBean = new SysProjectBean();
//        sysProjectBean.setProjectGid(projectGid);
//        sysProjectBean.setProjectNameEn(projectNameEn);
//        sysProjectBean.setProjectNameZh(projectNameZh);
//        sysProjectBean.setToken(token);
//        //检验是否有主键重复的
//        checkExist(sysProjectBean);
//        String log = (String) requestBody.get(SysProjectBean.LOG_STR);//need storage
//        String pdfZh = (String) requestBody.get(SysProjectBean.PDF_ZH);//need storage
//        String pdfEn = (String) requestBody.get(SysProjectBean.PDF_EN);//need storage
//        String view = (String) requestBody.get(SysProjectBean.VIEW);//need storage
//        String pdfZhName = (String) requestBody.get(SysProjectBean.PDF_ZH_NAME);
//        String pdfEnName = (String) requestBody.get(SysProjectBean.PDF_EN_NAME);
//        CountDownLatch countDownLatch = new CountDownLatch(4);
//        if (!StringUtils.isEmpty(pdfEn) && !StringUtils.isEmpty(pdfEnName)) {
//            sysProjectBean.setPdfEnName(pdfEnName);
//            task.qingYunStorage(sysProjectBean, pdfEn, EnumStorage.PdfEn, countDownLatch);
//        } else {
//            countDownLatch.countDown();
//        }
//        if (!StringUtils.isEmpty(pdfZh) && !StringUtils.isEmpty(pdfZhName)) {
//            sysProjectBean.setPdfZhName(pdfZhName);
//            task.qingYunStorage(sysProjectBean, pdfZh, EnumStorage.PdfZh, countDownLatch);
//        } else {
//            countDownLatch.countDown();
//        }
//        if (!StringUtils.isEmpty(log)) {
//            task.qingYunStorage(sysProjectBean, log, EnumStorage.Log, countDownLatch);
//        } else {
//            countDownLatch.countDown();
//        }
//        if (!StringUtils.isEmpty(view)) {
//            task.qingYunStorage(sysProjectBean, view, EnumStorage.View, countDownLatch);
//        } else {
//            countDownLatch.countDown();
//        }
//        try {
//            countDownLatch.await();
//        } catch (Exception e) {
//            LOGGER.error("qingYunStorage,error", e);
//            throw new WitshareException(e.getMessage());
//        }
//        sysProjectBean.setWebsiteList(websiteList);
//        SysProject sysProject = new SysProject();
//        sysProject.setProjectGid(projectGid);
//        sysProject.setProjectToken(token);
//        sysProject.setProjectLogoLink(sysProjectBean.getLog());
//        sysProject.setProjectImgLink(sysProjectBean.getView());
//        sysProject.setUpdateTime(current);
//        sysProjectMapper.updateByPrimaryKeySelective(sysProject);
//
//        ProjectDescriptionCnExample projectDescriptionZhExample = new ProjectDescriptionCnExample();
//        projectDescriptionZhExample.or().andProjectGidEqualTo(projectGid);
//        ProjectDescriptionCn projectDescriptionZh = new ProjectDescriptionCn();
//        projectDescriptionZh.setProjectName(projectNameZh);
//        projectDescriptionZh.setProjectInstruction(instructionZh);
//        projectDescriptionZh.setProjectContent(contentZh);
//        projectDescriptionZh.setWhitePaperLink(whitePaperLinkZh);
//        projectDescriptionZh.setGradeReportLink(sysProjectBean.getPdfZh());
//        projectDescriptionZh.setUpdateTime(current);
//        projectDescriptionZhMapper.updateByExampleSelective(projectDescriptionZh, projectDescriptionZhExample);
//
//        ProjectDescriptionEnExample projectDescriptionEnExample = new ProjectDescriptionEnExample();
//        projectDescriptionEnExample.or().andProjectGidEqualTo(projectGid);
//        ProjectDescriptionEn projectDescriptionEn = new ProjectDescriptionEn();
//        projectDescriptionEn.setProjectName(projectNameEn);
//        projectDescriptionEn.setProjectInstruction(instructionEn);
//        projectDescriptionEn.setProjectContent(contentEn);
//        projectDescriptionEn.setWhitePaperLink(whitePaperLinkEn);
//        projectDescriptionEn.setGradeReportLink(sysProjectBean.getPdfEn());
//        projectDescriptionEn.setUpdateTime(current);
//        projectDescriptionEnMapper.updateByExampleSelective(projectDescriptionEn, projectDescriptionEnExample);
//        staticProjectWebsiteMapper.save(sysProjectBean);
//
//        //清缓存
//        redisCommonDao.delRedisKey(RedisKeyUtil.getIndexProjectKey());
//        redisCommonDao.delRedisKey(RedisKeyUtil.getProjectStatisticKey(projectGid));
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

    @Override
    public SysProjectBeanVo selectManagementByGid(String projectGid) {
        //查询主表
        SysProjectBean sysProjectBean = this.selectByProjectGid(projectGid);
        if (sysProjectBean == null) {
            throw new WitshareException(EnumResponseText.ErrorProjectGId);
        }
        SysProjectBeanVo sysProjectBeanVo = new SysProjectBeanVo();
        BeanUtils.copyProperties(sysProjectBean, sysProjectBeanVo);
        //依此查询描述表
        Map<String, ProjectDescriptionBean> description = sysProjectBeanVo.getDescriptions();
        Arrays.stream(EnumI18NProject.values()).forEach(p -> {
            String tableName = p.getTableName();
            String language = p.getRequestLanguage();
            ProjectDescriptionBean projectDescriptionBean = staticProjectDescriptionMapper.selectByTableName(tableName, projectGid);
            description.put(language, projectDescriptionBean);
        });
        //查询关联网站
        Map<String, String> webSiteMap = projectWebSiteService.select(projectGid);
        sysProjectBeanVo.setWebsites(webSiteMap);
        return sysProjectBeanVo;
    }

    /**
     * TODO 通过userGid查询主表,需要动态的修改表的数据，需要存缓存
     */
    @Override
    public SysProjectBean selectByProjectGid(String projectGid) {
        if (StringUtils.isEmpty(projectGid)) {
            return null;
        }
        SysProjectExample sysProjectExample = new SysProjectExample();
        sysProjectExample.or().andProjectGidEqualTo(projectGid);
        List<SysProject> sysProjects = sysProjectMapper.selectByExample(sysProjectExample);
        if (CollectionUtils.isNotEmpty(sysProjects)) {
            SysProjectBean sysProjectBean = new SysProjectBean();
            BeanUtils.copyProperties(sysProjects.get(0), sysProjectBean);
            sysProjectBean.setProjectLogoLink(getPictureUrl(sysProjectBean.getProjectLogoLink()))
                    .setProjectImgLink(getPictureUrl(sysProjectBean.getProjectImgLink()));
            return sysProjectBean;
        }
        return null;

    }


    /**
     * @see SysProjectService#selectSysProjects(ProjectReqBean)
     **/
    @Override
    public PageInfo<SysProjectBeanFrontListVo> selectSysProjects(ProjectReqBean projectReqBean) {
        if (projectReqBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        Integer pageNum = projectReqBean.getPageNum();
        Integer pageSize = projectReqBean.getPageSize();
        if (pageNum == null || pageSize == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        if (StringUtil.isNotEmpty(projectReqBean.getOrderCondition())) {
            projectReqBean.setOrderCondition(OrderCondition.getCondition(projectReqBean.getOrderCondition()));
        }
        String i18N = CurrentThreadContext.getI18N();
        projectReqBean.setTableName(EnumI18NProject.getObjByLanguage(i18N).getTableName());
        //设置要查询的表
        PageInfo<SysProjectBeanFrontListVo> projectPageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> staticSysProjectMapper.selectProjectList(projectReqBean));
        List<SysProjectBeanFrontListVo> projectList = projectPageInfo.getList();
        projectList.forEach(p -> {
            p.setProjectLogoLink(getPictureUrl(p.getProjectLogoLink()));
        });
        return projectPageInfo;

    }

    /**
     * @see SysProjectService#selectProject(String)
     **/
    @Override
    public SysProjectBeanFrontInfoVo selectProject(String projectGid) {
        SysProjectBeanFrontInfoVo frontInfoVo = null;
        if (StringUtils.isEmpty(projectGid)) {
            throw new WitshareException(EnumResponseText.ErrorProjectGId);
        }
        EnumI18NProject i18n = EnumI18NProject.getObjByLanguage(CurrentThreadContext.getInternationalTableName());
        String projectDetailName = i18n.getProjectDetailName();
        //查找redis
        String projectStatisticKey = RedisKeyUtil.getProjectStatisticKey(projectGid);
        String projectDetail = redisCommonDao.getHash(projectStatisticKey, projectDetailName);
        if (StringUtils.isNotEmpty(projectDetail)) {
            frontInfoVo = gson.fromJson(projectDetail, SysProjectBeanFrontInfoVo.class);
            return frontInfoVo;
        }
        //查主表
        SysProjectBean sysProjectBean = this.selectByProjectGid(projectGid);
        if (sysProjectBean == null) {
            throw new WitshareException(EnumResponseText.ErrorProjectGId);
        }
        frontInfoVo = SysProjectBeanFrontInfoVo.newInstance();
        BeanUtils.copyProperties(sysProjectBean, frontInfoVo);

        // 获取des
        ProjectDescriptionBean descriptionBean = staticProjectDescriptionMapper.selectByTableName(i18n.getTableName(), projectGid);
        frontInfoVo.setProjectInstruction(descriptionBean.getProjectInstruction())
                .setProjectContent(descriptionBean.getProjectContent())
                .setProjectName(descriptionBean.getProjectName());

        //获取webSite
        Map<String, String> webSiteMap = projectWebSiteService.select(projectGid);
        webSiteMap.put(WHITE_PAPER_LINK, descriptionBean.getWhitePaperLink());
        frontInfoVo.setWebsites(webSiteMap);
        //获取 价格、已售信息、下一个价格时间间隔
        Timestamp current = new Timestamp(System.currentTimeMillis());
        frontInfoVo.setPriceRate(projectDailyInfoService.getPrice(projectGid, current));

        frontInfoVo.setNextPriceInterval(123456789L);
        frontInfoVo.setSoldAmount(new BigDecimal(123456));

        redisCommonDao.putHash(projectStatisticKey, projectDetailName, gson.toJson(frontInfoVo));

        return frontInfoVo;
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
    public void hideProject(String projectGid) {
        if (StringUtils.isEmpty(projectGid)) {
            throw new WitshareException(EnumResponseText.ErrorProjectGId);
        }
        SysProjectBean sysProjectBean = this.selectByProjectGid(projectGid);
        if (sysProjectBean == null) {
            throw new WitshareException(EnumResponseText.ErrorProjectGId);
        }
        Long id = sysProjectBean.getId();
        staticSysProjectMapper.modifyProjectStatus(id);
        //清缓存
        redisCommonDao.delRedisKey(RedisKeyUtil.getProjectStatisticKey(projectGid));

    }

}