package com.witshare.mars.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.witshare.mars.dao.mysql.JobAndTriggerMapper;
import com.witshare.mars.pojo.dto.JobAndTrigger;
import com.witshare.mars.service.JobAndTriggerService;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JobAndTriggerImpl implements JobAndTriggerService {

    private final static Gson GSON = new Gson();
    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;
    @Autowired
    @Qualifier("marsScheduler")
    private SchedulerFactoryBean schedulerFactoryBean;


    public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails();
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        for (JobAndTrigger jt : list) {
            JobKey jobKey = new JobKey(jt.getJobName(), jt.getJobGroup());
            try {
                JobDataMap jobDataMap = null;
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                if (jobDetail != null) {
                    jobDataMap = jobDetail.getJobDataMap();
                }
                if (jobDataMap != null) {
                    jt.setJobData(GSON.toJson(jobDataMap));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        PageInfo<JobAndTrigger> page = new PageInfo<JobAndTrigger>(list);
        return page;
    }
}