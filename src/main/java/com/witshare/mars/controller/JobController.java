package com.witshare.mars.controller;


import com.github.pagehelper.PageInfo;
import com.witshare.mars.job.BaseJob;
import com.witshare.mars.pojo.dto.JobAndTrigger;
import com.witshare.mars.service.JobAndTriggerService;
import com.witshare.mars.util.JobDynamicScheduler;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/job")
public class JobController {
    private static Logger log = LoggerFactory.getLogger(JobController.class);
    @Autowired
    private JobAndTriggerService jobAndTriggerService;
    @Autowired
    @Qualifier("marsScheduler")
    private SchedulerFactoryBean schedulerFactoryBean;

    public static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }

    /**
     * 新增任务
     *
     * @throws Exception
     */
    @RequestMapping(value = "/addjob", method = RequestMethod.POST)
    public void addJob(JobAndTrigger jobAndTrigger) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDynamicScheduler.addJob(scheduler, jobAndTrigger);
    }

    /**
     * 暂停任务
     *
     * @throws Exception
     */
    @RequestMapping(value = "/pausejob", method = RequestMethod.POST)
    public void pausejob(JobAndTrigger jobAndTrigger) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDynamicScheduler.pauseJob(scheduler, jobAndTrigger);
    }

    /**
     * 恢复任务
     *
     * @throws Exception
     */
    @RequestMapping(value = "/resumejob", method = RequestMethod.POST)
    public void resumejob(JobAndTrigger jobAndTrigger) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDynamicScheduler.resumeJob(scheduler, jobAndTrigger);
    }

    /**
     * 执行一次任务
     *
     * @throws Exception
     */
    @RequestMapping(value = "/executeonce", method = RequestMethod.POST)
    public void executeOnce(JobAndTrigger jobAndTrigger) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDynamicScheduler.executeOnce(scheduler, jobAndTrigger);
    }


    /**
     * 删除任务
     *
     * @param jobAndTrigger
     * @throws Exception
     */
    @RequestMapping(value = "/deletejob", method = RequestMethod.POST)
    public void deleteJob(JobAndTrigger jobAndTrigger) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDynamicScheduler.removeJob(scheduler, jobAndTrigger);
    }


    /**
     * 查询所有任务
     */
    @RequestMapping(value = "/queryjob")
    public Map<String, Object> queryjob(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<JobAndTrigger> jobAndTrigger = jobAndTriggerService.getJobAndTriggerDetails(pageNum, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("JobAndTrigger", jobAndTrigger);
        map.put("number", jobAndTrigger.getTotal());
        return map;
    }


}

