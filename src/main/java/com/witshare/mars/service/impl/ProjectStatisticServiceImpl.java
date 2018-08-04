package com.witshare.mars.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.dao.mysql.*;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.domain.*;
import com.witshare.mars.pojo.dto.ProjectStatisticBean;
import com.witshare.mars.pojo.dto.ProjectSummaryBean;
import com.witshare.mars.pojo.dto.RecordUserTxBean;
import com.witshare.mars.pojo.dto.SysChannelBean;
import com.witshare.mars.service.ChannelService;
import com.witshare.mars.service.ProjectStatisticService;
import com.witshare.mars.service.SysProjectService;
import com.witshare.mars.util.RedisKeyUtil;
import com.witshare.mars.util.WitshareUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ProjectStatisticServiceImpl implements ProjectStatisticService {

    @Autowired
    private ProjectDailyInfoMapper projectDailyInfoMapper;
    @Autowired
    private ProjectChannelStatisticMapper projectChannelStatisticMapper;
    @Autowired
    private ProjectSummaryMapper projectSummaryMapper;
    @Autowired
    private SysProjectMapper sysProjectMapper;
    @Autowired
    private RecordUserTxMapper recordUserTxMapper;
    @Autowired
    private StaticProjectStatisticInfoMapper staticProjectStatisticInfoMapper;
    @Autowired
    private StaticProjectSummaryMapper staticProjectSummaryMapper;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private RedisCommonDao redisCommonDao;
    @Autowired
    private StaticSysUserTxMapper staticSysUserTxMapper;
    @Autowired
    private SysProjectService sysProjectService;

    public final static SimpleDateFormat SF = new SimpleDateFormat("0000-00-00");

    @Override
    public BigDecimal getSoldAmount(String projectGid) {
        ProjectSummaryBean summary = this.getSummary(projectGid);
        if (summary == null || summary.getActualGetEthAmount() == null) {
            return BigDecimal.ZERO;
        }
        return summary.getActualGetEthAmount();
    }


    @Override
    public ProjectSummaryBean getSummary(String projectGid) {
        if (StringUtils.isEmpty(projectGid)) {
            return null;
        }
        //查找数据库
        ProjectSummaryExample projectSummaryExample = new ProjectSummaryExample();
        projectSummaryExample.or().andProjectGidEqualTo(projectGid);
        List<ProjectSummary> projectSummaries = projectSummaryMapper.selectByExample(projectSummaryExample);
        if (CollectionUtils.isNotEmpty(projectSummaries)) {
            ProjectSummary projectSummary = projectSummaries.get(0);
            ProjectSummaryBean projectSummaryBean = ProjectSummaryBean.newInstance();
            BeanUtils.copyProperties(projectSummary, projectSummaryBean);
            return projectSummaryBean;
        }
        return null;
    }

    @Override
    public void syncSummaryToken() {
        ProjectSummaryBean projectSummaryBean = staticSysUserTxMapper.summaryToken();
        String summaryTokenKey = RedisKeyUtil.getSummaryTokenKey(projectSummaryBean.getProjectGid());
        redisCommonDao.setString(summaryTokenKey, new Gson().toJson(projectSummaryBean));
    }

    @Override
    public ProjectSummaryBean getSummaryToken(String projectGid) {
        String summaryTokenKey = RedisKeyUtil.getSummaryTokenKey(projectGid);
        String string = redisCommonDao.getString(summaryTokenKey);
        if (StringUtils.isBlank(string)) {
            return staticSysUserTxMapper.summaryToken();
        }
        return new Gson().fromJson(string, ProjectSummaryBean.class);
    }


    @Override
    public ProjectStatisticBean get(String projectGid, Date date) {
        if (StringUtils.isEmpty(projectGid)) {
            return null;
        }
        if (date == null) {
            date = new Date();
        }
        ProjectDailyInfoExample projectDailyInfoExample = new ProjectDailyInfoExample();
        projectDailyInfoExample.or().andProjectGidEqualTo(projectGid).andCurrentDayEqualTo(date);
        List<ProjectDailyInfo> projectDailyInfos = projectDailyInfoMapper.selectByExample(projectDailyInfoExample);
        if (CollectionUtils.isNotEmpty(projectDailyInfos)) {
            ProjectDailyInfo projectDailyInfo = projectDailyInfos.get(0);
            ProjectStatisticBean projectStatisticBean = ProjectStatisticBean.newInstance();
            BeanUtils.copyProperties(projectDailyInfo, projectStatisticBean);
            return projectStatisticBean;
        }
        return null;
    }


    @Override
    public PageInfo<ProjectStatisticBean> getList(ProjectStatisticBean projectStatisticBean) {
        if (projectStatisticBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        Integer pageNum = projectStatisticBean.getPageNum();
        Integer pageSize = projectStatisticBean.getPageSize();
        String projectGid = projectStatisticBean.getProjectGid();
        if (pageNum == null || pageSize == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        PageInfo<ProjectStatisticBean> projectDailyInfoBeanPageInfo = new PageInfo<>();
        if (StringUtils.isEmpty(projectGid)) {
            return projectDailyInfoBeanPageInfo;
        }
        String action = projectStatisticBean.getAction();

        if (StringUtils.equals("channel", action)) {
            ProjectChannelStatisticExample projectChannelStatisticExample = new ProjectChannelStatisticExample();
            projectChannelStatisticExample.or().andProjectGidEqualTo(projectGid);
            projectChannelStatisticExample.setOrderByClause("channel asc");
            PageInfo<ProjectChannelStatistic> pageInfos = PageHelper.startPage(pageNum, pageSize)
                    .doSelectPageInfo(() -> projectChannelStatisticMapper.selectByExample(projectChannelStatisticExample));
            List<ProjectChannelStatistic> list = pageInfos.getList();
            LinkedList<ProjectStatisticBean> projectStatisticBeans = new LinkedList<>();
            list.forEach(p -> {
                ProjectStatisticBean bean = new ProjectStatisticBean();
                BeanUtils.copyProperties(p, bean);
                projectStatisticBeans.add(bean);
            });
            pageInfos.setList(null);
            BeanUtils.copyProperties(pageInfos, projectDailyInfoBeanPageInfo);
            projectDailyInfoBeanPageInfo.setList(projectStatisticBeans);
        } else {
            ProjectDailyInfoExample projectDailyInfoExample = new ProjectDailyInfoExample();
            projectDailyInfoExample.or().andProjectGidEqualTo(projectGid);
            projectDailyInfoExample.setOrderByClause("current_day desc");
            PageInfo<ProjectDailyInfo> pageInfos = PageHelper.startPage(pageNum, pageSize)
                    .doSelectPageInfo(() -> projectDailyInfoMapper.selectByExample(projectDailyInfoExample));
            List<ProjectDailyInfo> list = pageInfos.getList();
            LinkedList<ProjectStatisticBean> projectStatisticBeans = new LinkedList<>();
            list.forEach(p -> {
                ProjectStatisticBean bean = new ProjectStatisticBean();
                BeanUtils.copyProperties(p, bean);
                projectStatisticBeans.add(bean);
            });
            pageInfos.setList(null);
            BeanUtils.copyProperties(pageInfos, projectDailyInfoBeanPageInfo);
            projectDailyInfoBeanPageInfo.setList(projectStatisticBeans);
        }
        return projectDailyInfoBeanPageInfo;
    }

    @Override
    public PageInfo<ProjectStatisticBean> getList(String projectGid) {
        if (StringUtils.isAnyBlank(projectGid)) {
            return null;
        }
        ProjectStatisticBean projectStatisticBean = new ProjectStatisticBean();
        projectStatisticBean.setProjectGid(projectGid)
                .setPageNum(1)
                .setPageSize(Integer.MAX_VALUE);
        return this.getList(projectStatisticBean);
    }


    @Override
    public PageInfo<ProjectStatisticBean> getChannelList(String projectGid) {
        if (StringUtils.isAnyBlank(projectGid)) {
            return null;
        }
        ProjectStatisticBean projectStatisticBean = new ProjectStatisticBean();
        projectStatisticBean.setProjectGid(projectGid)
                .setPageNum(1)
                .setPageSize(Integer.MAX_VALUE);
        projectStatisticBean.setAction("channel");
        return this.getList(projectStatisticBean);
    }

    //更新每日统计表和汇总表
    @Override
    public void syncDailyInfo() {
        LinkedList<String> projects = new LinkedList<>();
        LinkedList<ProjectStatisticBean> projectStatisticDailyBeans = new LinkedList<>();
        LinkedList<ProjectStatisticBean> projectStatisticChannelBeans = new LinkedList<>();
        LinkedList<ProjectSummaryBean> projectSummaryBeans = new LinkedList<>();

        //查询 首页展示项目
        SysProjectExample sysProjectExample = new SysProjectExample();
        sysProjectExample.or().andDefaultProjectEqualTo(1);
        List<SysProject> sysProjects = sysProjectMapper.selectByExample(sysProjectExample);
        sysProjects.forEach(p -> projects.add(p.getProjectGid()));

        //查询与项目有关的所有交易
        RecordUserTxExample recordUserTxExample = new RecordUserTxExample();
        recordUserTxExample.or().andProjectGidIn(projects);
        List<RecordUserTx> recordUserTxes = recordUserTxMapper.selectByExample(recordUserTxExample);
        LinkedList<RecordUserTxBean> recordUserTxBeans = new LinkedList<>();
        recordUserTxes.forEach(p -> {
            RecordUserTxBean recordUserTxBean = RecordUserTxBean.newInstance();
            BeanUtils.copyProperties(p, recordUserTxBean);
            recordUserTxBeans.add(recordUserTxBean);
        });
        //将所有交易按照日期分组
        Map<String, Map<LocalDate, List<RecordUserTxBean>>> dailyCollect = recordUserTxBeans.stream()
                .collect(Collectors.groupingBy(RecordUserTxBean::getProjectGid,
                        Collectors.groupingBy(RecordUserTxBean::getLocalDate,
                                Collectors.toList())));

        //将所有交易按照渠道号分组
        //2.按照渠道号分组
        Map<String, Map<String, List<RecordUserTxBean>>> channelCollect = recordUserTxBeans.stream()
                .collect(Collectors.groupingBy(RecordUserTxBean::getProjectGid,
                        Collectors.groupingBy(RecordUserTxBean::getChannel,
                                Collectors.toList())));
        channelCollect.forEach((projectGid, v1) -> {
            v1.forEach((channel, v2) -> {
                ProjectSummaryBean projectSummaryBean = getProjectSummaryBean(v2);
                if (projectSummaryBean == null) {
                    return;
                }
                ProjectStatisticBean projectStatisticBean = ProjectStatisticBean.newInstance();
                BeanUtils.copyProperties(projectSummaryBean, projectStatisticBean);
                String channelName = "";
                SysChannelBean sysChannelBean = channelService.get(channel);
                if (sysChannelBean != null) {
                    channelName = sysChannelBean.getName();
                }
                projectStatisticBean.setChannel(channel).setChannelName(channelName);
                projectStatisticChannelBeans.add(projectStatisticBean);
            });
        });


        //汇总数据
        dailyCollect.forEach((projectGid, v1) -> {
            LinkedList<RecordUserTxBean> userTxList = new LinkedList<>();
            v1.forEach((localDate, v2) -> {
                ProjectSummaryBean projectSummaryBean = getProjectSummaryBean(v2);
                if (projectSummaryBean == null) {
                    return;
                }
                ProjectStatisticBean projectStatisticBean = ProjectStatisticBean.newInstance();
                BeanUtils.copyProperties(projectSummaryBean, projectStatisticBean);
                Date currentDay = WitshareUtils.getDateByLocalDate(localDate);
                projectStatisticBean.setCurrentDay(currentDay);

                //置入list
                userTxList.addAll(v2);
                projectStatisticDailyBeans.add(projectStatisticBean);
            });
            //置入list
            ProjectSummaryBean projectSummaryBean = getProjectSummaryBean(userTxList);
            projectSummaryBeans.add(projectSummaryBean);
        });

        //存表
        staticProjectStatisticInfoMapper.saveOrUpdateDaily(projectStatisticDailyBeans);
        staticProjectStatisticInfoMapper.saveOrUpdateChannel(projectStatisticChannelBeans);
        staticProjectSummaryMapper.saveOrUpdate(projectSummaryBeans);
    }

    /**
     * 通过交易记录获得项目统计数据
     */
    private ProjectSummaryBean getProjectSummaryBean(List<RecordUserTxBean> userTxList) {

        if (CollectionUtils.isEmpty(userTxList)) {
            return null;
        }

        RecordUserTxBean recordUserTxBean = userTxList.get(0);
        String projectGid = recordUserTxBean.getProjectGid();
        String projectToken = recordUserTxBean.getProjectToken();
        Timestamp current = new Timestamp(System.currentTimeMillis());

        BigDecimal actualGetEthAmount = userTxList.stream().filter(p -> (BigDecimal.ZERO.compareTo(p.getShouldGetAmount()) < 0)).map(RecordUserTxBean::getActualPayAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal actualPayTokenAmount = userTxList.stream().filter(p -> (BigDecimal.ZERO.compareTo(p.getShouldGetAmount()) < 0)).map(RecordUserTxBean::getShouldGetAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal getEthAmount = userTxList.stream().map(RecordUserTxBean::getPayAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal payTokenAmount = userTxList.stream().map(RecordUserTxBean::getHopeGetAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        Set<String> userSet = userTxList.stream().map(RecordUserTxBean::getUserGid).collect(Collectors.toSet());
        int txCount = userTxList.size();
        int userCount = userSet.size();

        Set<String> actualUserSet = userTxList.stream().filter(p -> (BigDecimal.ZERO.compareTo(p.getShouldGetAmount()) < 0)).map(RecordUserTxBean::getUserGid).collect(Collectors.toSet());
        int actualTxCount = (int) userTxList.stream().filter(p -> (BigDecimal.ZERO.compareTo(p.getShouldGetAmount()) < 0)).count();
        int actualUserCount = actualUserSet.size();

        return ProjectSummaryBean.newInstance()
                .setProjectGid(projectGid)
                .setProjectToken(projectToken)
                .setActualUserCount(actualUserCount)
                .setUserCount(userCount)
                .setActualGetEthAmount(actualGetEthAmount)
                .setActualPayTokenAmount(actualPayTokenAmount)
                .setGetEthAmount(getEthAmount)
                .setPayTokenAmount(payTokenAmount)
                .setTxCount(txCount)
                .setUserCount(userCount)
                .setActualTxCount(actualTxCount)
                .setActualUserCount(actualUserCount)
                .setCreateTime(current)
                .setUpdateTime(current);

    }


}
