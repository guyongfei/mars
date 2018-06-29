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
import com.witshare.mars.pojo.domain.SysProject;
import com.witshare.mars.pojo.domain.SysProjectExample;
import com.witshare.mars.pojo.dto.ProjectDescriptionBean;
import com.witshare.mars.pojo.dto.ProjectReqBean;
import com.witshare.mars.pojo.dto.ProjectSummaryBean;
import com.witshare.mars.pojo.dto.SysProjectBean;
import com.witshare.mars.pojo.vo.SysProjectBeanFrontInfoVo;
import com.witshare.mars.pojo.vo.SysProjectBeanFrontListVo;
import com.witshare.mars.pojo.vo.SysProjectBeanVo;
import com.witshare.mars.pojo.vo.SysProjectListVo;
import com.witshare.mars.service.*;
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
import java.util.concurrent.TimeUnit;

import static com.witshare.mars.constant.CacheConsts.WHITE_PAPER_LINK;


/**
 * @see SysProjectService
 **/
@Service
public class SysProjectServiceImpl implements SysProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysProjectServiceImpl.class);

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
    private PlatformAddressService platformAddressService;
    @Autowired
    private RedisCommonDao redisCommonDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String jsonBody) {
        if (StringUtils.isEmpty(jsonBody)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        SysProjectBean sysProjectBean = new Gson().fromJson(jsonBody, SysProjectBean.class);
        //校验
        checkProjectBean(sysProjectBean, true);
        checkExist(sysProjectBean);

        sysProjectBean.setProjectGid(WitshareUtils.getUUID());
        //存储s3
        String objectName = qingyunStorageService.uploadToQingyun(sysProjectBean.getLog(), sysProjectBean.getProjectGid(), EnumStorage.Log);
        sysProjectBean.setProjectLogoLink(objectName);

        Timestamp current = new Timestamp(System.currentTimeMillis());
        String platformAddress = platformAddressService.getPlatformAddress();
        if (StringUtils.isEmpty(platformAddress)) {
            throw new WitshareException(EnumResponseText.NoPlatformAddress);
        }
        sysProjectBean.setPlatformAddress(platformAddress)
                .setStartTime(sysProjectBean.getStartTime())
                .setEndTime(sysProjectBean.getEndTime())
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
     * @see SysProjectService#update(String)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(String jsonBody) {
        if (StringUtils.isEmpty(jsonBody)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        SysProjectBean sysProjectBean = new Gson().fromJson(jsonBody, SysProjectBean.class);
        String projectGid = sysProjectBean.getProjectGid();
        SysProjectBean sysProjectBeanDb = this.selectByProjectGid(projectGid);
        if (sysProjectBeanDb == null) {
            throw new WitshareException(EnumResponseText.ErrorProjectGId);
        }
        //校验
        checkProjectBean(sysProjectBean, false);
        checkExist(sysProjectBean);
        int projectStatus = sysProjectBeanDb.getProjectStatus();

        //状态不同特定值不能更改
        if (projectStatus >= EnumProjectStatus.Status1.getStatus()) {
            sysProjectBean.setStartTime(sysProjectBeanDb.getStartTime());
        }
        if (projectStatus >= EnumProjectStatus.Status2.getStatus()) {
            sysProjectBean.setSoftCap(sysProjectBeanDb.getSoftCap());
        }
        if (projectStatus >= EnumProjectStatus.Status3.getStatus()) {
            sysProjectBean.setHardCap(sysProjectBeanDb.getHardCap());
            sysProjectBean.setPriceRate(sysProjectBeanDb.getPriceRate());
            sysProjectBean.setMinPurchaseAmount(sysProjectBeanDb.getMinPurchaseAmount());
            sysProjectBean.setEndTime(sysProjectBeanDb.getEndTime());
        }

        //存储s3
        String log = sysProjectBean.getLog();
        if (StringUtils.isNotEmpty(log)) {
            String objectName = qingyunStorageService.uploadToQingyun(log, sysProjectBean.getProjectGid(), EnumStorage.Log);
            sysProjectBean.setProjectLogoLink(objectName);
        }

        //存表
        Timestamp current = new Timestamp(System.currentTimeMillis());
        SysProject sysProject = new SysProject();
        sysProjectBean.setId(sysProjectBeanDb.getId()).setUpdateTime(current);
        BeanUtils.copyProperties(sysProjectBean, sysProject);
        sysProjectMapper.updateByPrimaryKeySelective(sysProject);

        staticProjectDescriptionMapper.saveOrUpdate(sysProjectBean);

        staticProjectWebsiteMapper.saveOrUpdate(sysProjectBean);

        //删缓存
        this.deleteProjectCache(projectGid);
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
     * 检验项目属性值的合法性
     */
    private void checkProjectBean(SysProjectBean sysProjectBean, boolean isSave) {
        BigDecimal softCap = sysProjectBean.getSoftCap();
        BigDecimal priceRate = sysProjectBean.getPriceRate();
        Timestamp endTime = new Timestamp(sysProjectBean.getEndTimeLong());
        Timestamp startTime = new Timestamp(sysProjectBean.getStartTimeLong());
        BigDecimal hardCap = sysProjectBean.getHardCap();
        BigDecimal minPurchaseAmount = sysProjectBean.getMinPurchaseAmount();
        String token = sysProjectBean.getProjectToken();
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
                || priceRate.compareTo(BigDecimal.ZERO) < 0
                || softCap == null || softCap.compareTo(BigDecimal.ZERO) <= 0
                || hardCap == null || softCap.compareTo(hardCap) > 0
                || minPurchaseAmount == null || softCap.compareTo(minPurchaseAmount) <= 0
                || StringUtils.isEmpty(token)
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
        if (isSave) {
            String log = sysProjectBean.getLog();
            if (StringUtils.isEmpty(log)) {
                throw new WitshareException(EnumResponseText.ErrorRequest);
            }
        }
        sysProjectBean.setStartTime(startTime).setEndTime(endTime);
    }


    /**
     * @see SysProjectService#selectManagementList(SysProjectBean)
     */
    @Override
    public PageInfo<SysProjectListVo> selectManagementList(SysProjectBean sysProjectBean) {
        Integer pageNum = sysProjectBean.getPageNum();
        Integer pageSize = sysProjectBean.getPageSize();
        pageSize = pageSize == null ? 10 : pageSize;
        pageNum = pageNum == null ? 1 : pageNum;
        return PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> staticSysProjectMapper.selectManagementList(sysProjectBean));
    }

    @Override
    public SysProjectBeanVo selectManagementByProjectGid(String projectGid) {
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
     * @see SysProjectService#selectByProjectGid(String)
     */
    @Override
    public SysProjectBean selectByProjectGid(String projectGid) {
        if (StringUtils.isEmpty(projectGid)) {
            return null;
        }
        SysProjectBean sysProjectBean;
        String result = redisCommonDao.getString(RedisKeyUtil.getProjectKey(projectGid));
        if (StringUtils.isNotEmpty(result)) {
            sysProjectBean = new Gson().fromJson(result, SysProjectBean.class);
            this.updateProjectStatus(sysProjectBean);
            return sysProjectBean;
        }
        SysProjectExample sysProjectExample = new SysProjectExample();
        sysProjectExample.or().andProjectGidEqualTo(projectGid);
        List<SysProject> sysProjects = sysProjectMapper.selectByExample(sysProjectExample);
        if (CollectionUtils.isNotEmpty(sysProjects)) {
            sysProjectBean = SysProjectBean.newInstance();
            BeanUtils.copyProperties(sysProjects.get(0), sysProjectBean);
            sysProjectBean.setProjectLogoLink(getPictureUrl(sysProjectBean.getProjectLogoLink()))
                    .setProjectImgLink(getPictureUrl(sysProjectBean.getProjectImgLink()));
            this.updateProjectStatus(sysProjectBean);
            redisCommonDao.put(RedisKeyUtil.getProjectKey(projectGid), new Gson().toJson(sysProjectBean), 1, TimeUnit.DAYS);
            return sysProjectBean;
        }
        return null;
    }

    /**
     * 根据已售数量判断和更改项目状态
     *
     * @param sysProjectBean
     */
    private void updateProjectStatus(SysProjectBean sysProjectBean) {
        if (sysProjectBean == null) {
            return;
        }
        int projectStatus = sysProjectBean.getProjectStatus();
        String projectGid = sysProjectBean.getProjectGid();
        Timestamp startTime = sysProjectBean.getStartTime();
        Timestamp endTime = sysProjectBean.getEndTime();
        Timestamp current = new Timestamp(System.currentTimeMillis());
        BigDecimal softCap = sysProjectBean.getSoftCap();
        BigDecimal hardCap = sysProjectBean.getHardCap();
        BigDecimal actualGetEthAmount = BigDecimal.ZERO;
        ProjectSummaryBean summary = projectDailyInfoService.getSummary(projectGid);
        if (summary != null) {
            actualGetEthAmount = summary.getActualGetEthAmount();
        }
        //状态修改
        int projectStatusNow = 0;
        if (startTime.after(current)) {
            projectStatusNow = EnumProjectStatus.Status1.getStatus();
        }
        if (softCap.compareTo(actualGetEthAmount) >= 0) {
            projectStatusNow = EnumProjectStatus.Status1.getStatus();
        }
        if (hardCap.compareTo(actualGetEthAmount) >= 0) {
            projectStatusNow = EnumProjectStatus.Status2.getStatus();
        }
        if (endTime.before(current)) {
            projectStatusNow = EnumProjectStatus.Status3.getStatus();
        }
        sysProjectBean.setProjectStatus(projectStatusNow);
        //更改数据库，删除redis
        if (projectStatus != projectStatusNow) {
            SysProject sysProject = new SysProject();
            sysProject.setUpdateTime(current);
            sysProject.setProjectGid(projectGid);
            sysProject.setProjectStatus(projectStatusNow);
            sysProjectMapper.updateByPrimaryKeySelective(sysProject);
            this.deleteProjectCache(projectGid);
        }
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
        String projectStatisticKey = RedisKeyUtil.getProjectFrontKey(projectGid);
        String projectDetail = null;
//        String projectDetail = redisCommonDao.getHash(projectStatisticKey, projectDetailName);
        if (StringUtils.isNotEmpty(projectDetail)) {
            frontInfoVo = gson.fromJson(projectDetail, SysProjectBeanFrontInfoVo.class);
            frontInfoVo.setSoldAmount(projectDailyInfoService.getSoldAmount(projectGid));
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

        //返回状态做修改
        frontInfoVo.setProjectStatus(this.getFrontProjectStatus(frontInfoVo.getProjectStatus()));

        redisCommonDao.putHash(projectStatisticKey, projectDetailName, gson.toJson(frontInfoVo));

        frontInfoVo.setSoldAmount(projectDailyInfoService.getSoldAmount(projectGid));
        return frontInfoVo;
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
        this.deleteProjectCache(projectGid);

    }

    /**
     * @see SysProjectService#getPictureUrl(String)
     */
    @Override
    public String getPictureUrl(String source) {
        return StringUtils.isEmpty(source) ? null : propertiesConfig.qingyunHttpUrl + source;
    }

    @Override
    public void deleteProjectCache(String projectGid) {
        redisCommonDao.delRedisKey(RedisKeyUtil.getProjectFrontKey(projectGid));
        redisCommonDao.delRedisKey(RedisKeyUtil.getProjectKey(projectGid));
    }

    @Override
    public int getFrontProjectStatus(int status) {
        if (status == EnumProjectStatus.Status2.getStatus()) {
            status = EnumProjectStatus.Status1.getStatus();
        }
        if (status == EnumProjectStatus.Status4.getStatus()) {
            status = EnumProjectStatus.Status3.getStatus();
        }
        return status;
    }

}