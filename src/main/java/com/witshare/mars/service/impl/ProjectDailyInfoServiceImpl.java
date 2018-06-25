package com.witshare.mars.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.witshare.mars.constant.EnumProjectStatus;
import com.witshare.mars.dao.mysql.ProjectDailyInfoMapper;
import com.witshare.mars.dao.mysql.ProjectSummaryMapper;
import com.witshare.mars.dao.mysql.RecordUserTxMapper;
import com.witshare.mars.dao.mysql.SysProjectMapper;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.pojo.domain.*;
import com.witshare.mars.pojo.dto.ProjectDailyInfoBean;
import com.witshare.mars.pojo.dto.ProjectSummaryBean;
import com.witshare.mars.pojo.dto.RecordUserTxBean;
import com.witshare.mars.pojo.dto.SysProjectBean;
import com.witshare.mars.service.ProjectDailyInfoService;
import com.witshare.mars.service.SysProjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ProjectDailyInfoServiceImpl implements ProjectDailyInfoService {

    @Autowired
    private ProjectDailyInfoMapper projectDailyInfoMapper;
    @Autowired
    private ProjectSummaryMapper projectSummaryMapper;
    @Autowired
    private SysProjectMapper sysProjectMapper;
    @Autowired
    private RecordUserTxMapper recordUserTxMapper;
    @Autowired
    private SysProjectService sysProjectService;
    @Autowired
    private RedisCommonDao redisCommonDao;

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

//        BigDecimal actualGetEthAmount = projectDailyInfos.stream().map(ProjectDailyInfo::getActualGetEthAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
//        BigDecimal actualPayTokenAmount = projectDailyInfos.stream().map(ProjectDailyInfo::getActualPayTokenAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
//        BigDecimal getEthAmount = projectDailyInfos.stream().map(ProjectDailyInfo::getGetEthAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
//        BigDecimal payTokenAmount = projectDailyInfos.stream().map(ProjectDailyInfo::getPayTokenAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
//        int txAddressCount = projectDailyInfos.stream().mapToInt(ProjectDailyInfo::getTxCount).sum();
//        int txUserCount = projectDailyInfos.stream().mapToInt(ProjectDailyInfo::getUserCount).sum();
//        int actualTxAddressCount = projectDailyInfos.stream().mapToInt(ProjectDailyInfo::getActualTxCount).sum();
//        int actualTxUserCount = projectDailyInfos.stream().mapToInt(ProjectDailyInfo::getActualTxUserCount).sum();
    }

    @Override
    public ProjectDailyInfoBean get(String projectGid, Date date) {
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
            ProjectDailyInfoBean projectDailyInfoBean = ProjectDailyInfoBean.newInstance();
            BeanUtils.copyProperties(projectDailyInfo, projectDailyInfoBean);
            return projectDailyInfoBean;
        }
        return null;
    }


    //更新每日统计表和汇总表
    public void syncDailyInfo() {
        //查询 还未打币完成的项目
        SysProjectExample sysProjectExample = new SysProjectExample();
        sysProjectExample.or().andProjectStatusIn(EnumProjectStatus.getStatisticStatuses());
        List<SysProject> sysProjects = sysProjectMapper.selectByExample(sysProjectExample);
        LinkedList<String> projects = new LinkedList<>();
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
        //将所有交易分组
        Map<String, Map<LocalDate, List<RecordUserTxBean>>> collect = recordUserTxBeans.stream()
                .collect(Collectors.groupingBy(RecordUserTxBean::getProjectGid,
                        Collectors.groupingBy(RecordUserTxBean::getLocalDate,
                                Collectors.toList())));
        //
        LinkedList<ProjectDailyInfoBean> projectDailyInfoBeans = new LinkedList<>();
        LinkedList<ProjectSummaryBean> projectSummaryBeans = new LinkedList<>();



        collect.forEach((projectGid, v1) -> {

            SysProjectBean sysProjectBean = sysProjectService.selectByProjectGid(projectGid);
            String projectToken = sysProjectBean.getProjectToken();
            LinkedList<RecordUserTxBean> projectList = new LinkedList<>();
            Timestamp current = new Timestamp(System.currentTimeMillis());
            v1.forEach((localDate, v2) -> {
                //获取统计数据
                BigDecimal actualGetEthAmount = v2.stream().map(RecordUserTxBean::getActualGetAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal actualPayTokenAmount = v2.stream().map(RecordUserTxBean::getShouldGetAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal getEthAmount = v2.stream().map(RecordUserTxBean::getPayAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal payTokenAmount = v2.stream().map(RecordUserTxBean::getHopeGetAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

                Set<String> userSet = v2.stream().map(RecordUserTxBean::getUserGid).collect(Collectors.toSet());
                int txCount = v2.size();
                int userCount = userSet.size();

                Set<String> actualUserSet = v2.stream().filter(p -> (BigDecimal.ZERO.compareTo(p.getShouldGetAmount()) < 0)).map(RecordUserTxBean::getUserGid).collect(Collectors.toSet());
                int actualTxCount = (int) v2.stream().filter(p -> (BigDecimal.ZERO.compareTo(p.getShouldGetAmount()) < 0)).count();
                int actualUserCount = actualUserSet.size();


                ZoneId zoneId = ZoneId.systemDefault();
                ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
                Date current_day = Date.from(zdt.toInstant());

                //组装统计对象
                ProjectDailyInfoBean projectDailyInfoBean = ProjectDailyInfoBean.newInstance()
                        .setProjectGid(projectGid)
                        .setProjectToken(projectToken)
                        .setActualGetEthAmount(actualGetEthAmount)
                        .setActualPayTokenAmount(actualPayTokenAmount)
                        .setGetEthAmount(getEthAmount)
                        .setPayTokenAmount(payTokenAmount)
                        .setTxCount(txCount)
                        .setUserCount(userCount)
                        .setActualTxCount(actualTxCount)
                        .setActualUserCount(actualUserCount)
                        .setCreateTime(current)
                        .setUpdateTime(current)
                        .setCurrentDay(current_day);

                //置入list
                projectList.addAll(v2);
                projectDailyInfoBeans.add(projectDailyInfoBean);
            });

            BigDecimal actualGetEthAmount = projectList.stream().map(RecordUserTxBean::getActualGetAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal actualPayTokenAmount = projectList.stream().map(RecordUserTxBean::getShouldGetAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal getEthAmount = projectList.stream().map(RecordUserTxBean::getPayAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal payTokenAmount = projectList.stream().map(RecordUserTxBean::getHopeGetAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            Set<String> userSet = projectList.stream().map(RecordUserTxBean::getUserGid).collect(Collectors.toSet());
            int txCount = projectList.size();
            int userCount = userSet.size();

            Set<String> actualUserSet = projectList.stream().filter(p -> (BigDecimal.ZERO.compareTo(p.getShouldGetAmount()) < 0)).map(RecordUserTxBean::getUserGid).collect(Collectors.toSet());
            int actualTxCount = (int) projectList.stream().filter(p -> (BigDecimal.ZERO.compareTo(p.getShouldGetAmount()) < 0)).count();
            int actualUserCount = actualUserSet.size();

            ProjectSummaryBean projectSummaryBean = ProjectSummaryBean.newInstance()
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
        });


    }


}
