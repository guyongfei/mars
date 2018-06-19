package com.witshare.mars.util;

import com.google.gson.Gson;
import com.witshare.mars.pojo.dto.JobAndTrigger;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;


public final class JobDynamicScheduler {

    private static final Logger logger = LoggerFactory.getLogger(JobDynamicScheduler.class);

    private final static Gson GSON = new Gson();

    /**
     * 根据任务的id和组查询当前任务在quartz中是否存在
     *
     * @param scheduler
     * @param triggerName
     * @param triggerGroup
     * @return
     * @throws SchedulerException
     */
    public static boolean checkExists(Scheduler scheduler, String triggerName, String triggerGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
        return scheduler.checkExists(triggerKey);
    }

    public static String getJobName(String jobClassName) {
        if (StringUtils.isEmpty(jobClassName)) {
            return jobClassName;
        }
        return jobClassName.substring(jobClassName.lastIndexOf(".") + 1, jobClassName.length());
    }

    /**
     * 向quartz中添加任务
     *
     * @param scheduler
     * @return
     * @throws SchedulerException
     */
    public static boolean addJob(Scheduler scheduler, JobAndTrigger jobAndTrigger) throws SchedulerException {
        if (jobAndTrigger == null) {
            logger.info("addJob error,jobAndTrigger:{}", GSON.toJson(jobAndTrigger));
            return false;
        }
        String jobClassName = jobAndTrigger.getJobClassName();
        String jobGroup = jobAndTrigger.getJobGroup();
        String cronExpression = jobAndTrigger.getCronExpression();
        String jobData = (String) jobAndTrigger.getJobData();
        String description = jobAndTrigger.getDescription();
        if (StringUtils.isEmpty(jobClassName)
                || StringUtils.isEmpty(jobGroup)
                || StringUtils.isEmpty(cronExpression)
                || StringUtils.isEmpty(jobData)
                || StringUtils.isEmpty(description)) {
            logger.info("addJob error,jobAndTrigger:{} ", jobAndTrigger);
            return false;
        }
        String jobName = getJobName(jobClassName);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        JobKey jobKey = new JobKey(jobName, jobGroup);

        if (checkExists(scheduler, jobName, jobGroup)) {
            logger.info("addJob error, job exist, jobGroup:{}, jobName:{}", jobGroup, jobName);
            return false;
        }
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
        Class<? extends Job> jobClass = null;
        try {
            jobClass = (Class<? extends Job>) Class.forName(jobClassName);
        } catch (ClassNotFoundException e) {
            logger.error("addJob error, class not found , jobClass:{}, jobName:{},jobGroup:{}", jobClassName, jobName, jobGroup);
            return false;
        }
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(GSON.fromJson(jobData, Map.class));
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).setJobData(jobDataMap).withDescription(description).build();
        Date date = scheduler.scheduleJob(jobDetail, cronTrigger);
        logger.info("addJob success, jobDetail:{}, cronTrigger:{}, date:{}", GSON.toJson(jobDetail), GSON.toJson(cronTrigger), date);
        return true;
    }


    /**
     * 从quartz中删除任务
     *
     * @param scheduler
     * @return
     * @throws SchedulerException
     */
    public static boolean removeJob(Scheduler scheduler, JobAndTrigger jobAndTrigger) throws SchedulerException {
        boolean result = false;
        String triggerName = jobAndTrigger.getTriggerName();
        String triggerGroup = jobAndTrigger.getTriggerGroup();
        if (StringUtils.isEmpty(triggerGroup) || StringUtils.isEmpty(triggerName)) {
            logger.error("removeJob error, jobAndTrigger:{}", GSON.toJson(jobAndTrigger));
            return result;
        }
        if (checkExists(scheduler, triggerName, triggerGroup)) {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
            result = scheduler.unscheduleJob(triggerKey);
            logger.info("removeJob success, triggerKey:{}, result:{}", triggerKey, result);
        }
        return result;
    }

    /**
     * 执行一次任务
     *
     * @param scheduler
     * @param jobAndTrigger
     * @return
     * @throws SchedulerException
     */
    public static boolean executeOnce(Scheduler scheduler, JobAndTrigger jobAndTrigger) throws SchedulerException {
        if (jobAndTrigger == null) {
            logger.error("executeOnce error, jobAndTrigger:{}", GSON.toJson(jobAndTrigger));
            return false;
        }
        String triggerName = jobAndTrigger.getTriggerName();
        String triggerGroup = jobAndTrigger.getTriggerGroup();
        String jobName = jobAndTrigger.getJobName();
        String jobGroup = jobAndTrigger.getJobGroup();
        if (StringUtils.isEmpty(triggerName)
                || StringUtils.isEmpty(triggerGroup)
                || StringUtils.isEmpty(jobName)
                || StringUtils.isEmpty(jobGroup)) {
            logger.error("executeOnce error, jobAndTrigger:{}", GSON.toJson(jobAndTrigger));
            return false;
        }
        JobKey jobKey = new JobKey(jobName, jobGroup);
        boolean result = false;
        if (checkExists(scheduler, triggerName, triggerGroup)) {
            scheduler.triggerJob(jobKey);
            result = true;
            logger.info("executeOnce success, jobKey:{}", jobKey);
        } else {
            logger.error("executeOnce error, jobAndTrigger:{}", GSON.toJson(jobAndTrigger));
        }
        return result;
    }

    /**
     * 暂停任务
     *
     * @param scheduler
     * @return
     * @throws SchedulerException
     */
    public static boolean pauseJob(Scheduler scheduler, JobAndTrigger jobAndTrigger) throws SchedulerException {
        if (jobAndTrigger == null) {
            logger.error("pauseJob error, jobAndTrigger:{}", GSON.toJson(jobAndTrigger));
            return false;
        }
        String triggerName = jobAndTrigger.getTriggerName();
        String triggerGroup = jobAndTrigger.getTriggerGroup();
        if (StringUtils.isEmpty(triggerName)
                || StringUtils.isEmpty(triggerGroup)) {
            logger.error("pauseJob error, jobAndTrigger:{}", GSON.toJson(jobAndTrigger));
            return false;
        }
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
        boolean result = false;
        if (checkExists(scheduler, triggerName, triggerGroup)) {
            scheduler.pauseTrigger(triggerKey);
            result = true;
            logger.info("pauseJob success, triggerKey:{}", triggerKey);
        } else {
            logger.error("pauseJob error, jobAndTrigger:{}", GSON.toJson(jobAndTrigger));
        }
        return result;
    }

    /**
     * 恢复任务
     *
     * @param scheduler
     * @return
     * @throws SchedulerException
     */
    public static boolean resumeJob(Scheduler scheduler, JobAndTrigger jobAndTrigger) throws SchedulerException {
        if (jobAndTrigger == null) {
            logger.error("resumeJob error, jobAndTrigger:{}", GSON.toJson(jobAndTrigger));
            return false;
        }
        String triggerName = jobAndTrigger.getTriggerName();
        String triggerGroup = jobAndTrigger.getTriggerGroup();
        if (StringUtils.isEmpty(triggerName)
                || StringUtils.isEmpty(triggerGroup)) {
            logger.error("resumeJob error, jobAndTrigger:{}", GSON.toJson(jobAndTrigger));
            return false;
        }
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);

        boolean result = false;
        if (checkExists(scheduler, triggerName, triggerGroup)) {
            scheduler.resumeTrigger(triggerKey);
            result = true;
            logger.info("resumeJob success, triggerKey:{}", triggerKey);
        } else {
            logger.error("resumeJob error, jobAndTrigger:{}", GSON.toJson(jobAndTrigger));
        }
        return result;
    }


}