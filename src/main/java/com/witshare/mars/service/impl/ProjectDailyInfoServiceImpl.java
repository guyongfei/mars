package com.witshare.mars.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.witshare.mars.dao.mysql.ProjectDailyInfoMapper;
import com.witshare.mars.pojo.domain.ProjectDailyInfo;
import com.witshare.mars.pojo.domain.ProjectDailyInfoExample;
import com.witshare.mars.pojo.dto.ProjectDailyInfoBean;
import com.witshare.mars.pojo.dto.SysProjectBean;
import com.witshare.mars.service.ProjectDailyInfoService;
import com.witshare.mars.service.SysProjectService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


@Service
public class ProjectDailyInfoServiceImpl implements ProjectDailyInfoService {

    @Autowired
    private ProjectDailyInfoMapper projectDailyInfoMapper;
    @Autowired
    private SysProjectService sysProjectService;

    private static LocalDate getLocalDate(Timestamp dateTime) {
        return dateTime.toLocalDateTime().toLocalDate();
    }

    /**
     * 通过起止时间、价格来获得今日价格
     */

    private static BigDecimal getPrice(BigDecimal startPriceRate, BigDecimal endPriceRate,
                                       Timestamp startTime, Timestamp endTime, Timestamp dateTime) {
        BigDecimal price;
        LocalDate localDate = getLocalDate(dateTime);
        LocalDate startLocalDate = getLocalDate(startTime);
        LocalDate endLocalDate = getLocalDate(endTime);

        Period period = Period.between(startLocalDate, localDate);
        Period allPeriod = Period.between(startLocalDate, endLocalDate);

        if (dateTime.compareTo(startTime) <= 0) {
            price = startPriceRate;
        } else if (startPriceRate.compareTo(endPriceRate) == 0) {
            //前后价格相等
            price = endPriceRate;
        } else if (startLocalDate.compareTo(endLocalDate) == 0) {
            //前后时间相等
            price = endPriceRate;
        } else {
            //通过比率来计算当前价格
            BigDecimal rate = new BigDecimal(period.getDays()).divide(new BigDecimal(allPeriod.getDays()),
                    10, BigDecimal.ROUND_HALF_UP);
            price = startPriceRate.subtract(startPriceRate.subtract(endPriceRate).multiply(rate));
        }
        return price;
    }

    /**
     * 获取projectGid的价格
     */
    @Override
    public ProjectDailyInfoBean get(String projectGid, Timestamp dateTime) {
        if (StringUtils.isEmpty(projectGid)) {
            return null;
        }
        if (dateTime == null) {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.MILLISECOND, -1);
            dateTime = new Timestamp(calendar.getTimeInMillis());
        }
        //取最新的一条价格数据
        ProjectDailyInfoExample projectDailyInfoExample = new ProjectDailyInfoExample();
        projectDailyInfoExample.or().andCreateTimeLessThanOrEqualTo(dateTime);
        projectDailyInfoExample.setOrderByClause("current_day desc");
        List<ProjectDailyInfo> projectDailyInfos = projectDailyInfoMapper.selectByExample(projectDailyInfoExample);
        if (CollectionUtils.isNotEmpty(projectDailyInfos)) {
            ProjectDailyInfo projectDailyInfo = projectDailyInfos.get(0);
            ProjectDailyInfoBean projectDailyInfoBean = ProjectDailyInfoBean.newInstance();
            BeanUtils.copyProperties(projectDailyInfo, projectDailyInfoBean);
            return projectDailyInfoBean;
        }
        return null;
    }

    @Override
    public BigDecimal getPrice(String projectGid, Timestamp timestamp) {
        ProjectDailyInfoBean projectDailyInfoBean = this.get(projectGid, timestamp);
        if (projectDailyInfoBean == null) {
            this.saveOrUpdate(projectGid, timestamp);
            return this.get(projectGid, null).getPriceRate();
        }
        return projectDailyInfoBean.getPriceRate();
    }

    /**
     * 保存projectGid的价格
     */
    @Override
    public int saveOrUpdate(String projectGid, Timestamp dateTime) {
        if (StringUtils.isEmpty(projectGid) || dateTime == null) {
            return 0;
        }
        // 获取起止价格
        SysProjectBean sysProjectBean = sysProjectService.selectByProjectGid(projectGid);
        if (sysProjectBean == null) {
            return 0;
        }
        // 计算今日价格
        Timestamp startTime = sysProjectBean.getStartTime();
        Timestamp endTime = sysProjectBean.getEndTime();
        BigDecimal price =
                getPrice(sysProjectBean.getStartPriceRate(),
                        sysProjectBean.getEndPriceRate(),
                        startTime,
                        endTime,
                        dateTime);
        // 存表
        Timestamp current = new Timestamp(System.currentTimeMillis());
        ProjectDailyInfoBean projectDailyInfoBeanDb = this.get(projectGid, current);

        Timestamp currentDay = dateTime.compareTo(startTime) <= 0 ? startTime : (dateTime.compareTo(endTime) >= 0 ? endTime : dateTime);
        ProjectDailyInfoBean projectDailyInfoBean = ProjectDailyInfoBean.newInstance()
                .setProjectGid(projectGid)
                .setProjectToken(sysProjectBean.getProjectToken())
                .setPriceRate(price)
                .setCreateTime(current)
                .setUpdateTime(current)
                .setCurrentDay(currentDay);
        ProjectDailyInfo projectDailyInfo = new ProjectDailyInfo();
        //保存或更新
        if (projectDailyInfoBeanDb == null) {
            BeanUtils.copyProperties(projectDailyInfoBean, projectDailyInfo);
            return projectDailyInfoMapper.insertSelective(projectDailyInfo);
        } else {
            projectDailyInfoBeanDb.setPriceRate(price).setUpdateTime(current);
            BeanUtils.copyProperties(projectDailyInfoBeanDb, projectDailyInfo);
            return projectDailyInfoMapper.updateByPrimaryKeySelective(projectDailyInfo);
        }
    }

    @Test
    public void test1() {
        BigDecimal price;
        price = getPrice(BigDecimal.ONE, new BigDecimal(1),
                new Timestamp(2018, 6, 15, 0, 0, 0, 0),
                new Timestamp(2018, 6, 15, 0, 0, 0, 0),
                new Timestamp(2018, 6, 15, 0, 0, 0, 0));
        System.out.println(price);
        price = getPrice(new BigDecimal(21), BigDecimal.ONE,
                new Timestamp(2018, 6, 15, 0, 0, 0, 0),
                new Timestamp(2018, 6, 19, 0, 0, 0, 0),
                new Timestamp(2018, 6, 18, 0, 0, 0, 0));
        System.out.println(price);
        price = getPrice(BigDecimal.ONE, new BigDecimal(21),
                new Timestamp(2018, 6, 15, 0, 0, 0, 0),
                new Timestamp(2018, 6, 19, 0, 0, 0, 0),
                new Timestamp(2018, 6, 15, 0, 0, 0, 0));
        System.out.println(price);
        price = getPrice(BigDecimal.ONE, new BigDecimal(21),
                new Timestamp(2018, 6, 15, 0, 0, 0, 0),
                new Timestamp(2018, 6, 19, 0, 0, 0, 0),
                new Timestamp(2018, 6, 14, 0, 0, 0, 0));
        System.out.println(price);
    }

    @Test
    public void test() {
        LocalDateTime startDate = new Timestamp(System.currentTimeMillis()).toLocalDateTime();
        System.out.println("开始时间  : " + startDate);

        LocalDateTime endDate = new Timestamp(System.currentTimeMillis() + 1000).toLocalDateTime();
        System.out.println("结束时间 : " + endDate);

        long daysDiff = ChronoUnit.SECONDS.between(startDate, endDate);
        System.out.println("两天之间的差在天数   : " + daysDiff);

    }


}
