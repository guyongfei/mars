package com.witshare.mars.constant;

import com.google.gson.Gson;
import com.witshare.mars.job.SyncDailyStatisticInfo;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 默认需要开启的定时任务
 */
@Component
@Order(value = 5)
public class StartupRunnerJobScheduler implements CommandLineRunner {

    private static Logger Logger = LoggerFactory.getLogger(StartupRunnerJobScheduler.class);
    @Resource
    private PropertiesConfig propertiesConfig;
//    @Autowired
//    @Qualifier("marsScheduler")
//    private SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public void run(String... args) throws Exception {

        //同步每日统计
//        createJob(schedulerFactoryBean, "syncStatisticInfo", "system", "0 5 0 * * ?", "同步前一天的统计", SyncDailyStatisticInfo.class);
    }


    private void createJob(SchedulerFactoryBean schedulerFactoryBean, String name, String group, String cron, String description, Class clazz) {
        try {

            Scheduler scheduler = schedulerFactoryBean.getScheduler();


            //设置trigger
            CronTriggerImpl trigger = new CronTriggerImpl();
            trigger.setCronExpression(cron);
            trigger.setName(name);
            trigger.setGroup(group);

            //检验 triggerKey 是否存在
            TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
            boolean exists = scheduler.checkExists(triggerKey);
            if (exists) {
                Logger.info("createJob error,triggerKey:{} has bean created", new Gson().toJson(triggerKey));
                return;
            }

            //设置 jobDetail
            Class<? extends Job> jobClass = null;
            jobClass = (Class<? extends Job>) Class.forName(clazz.getName());
            JobKey jobKey = new JobKey(name, group);
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).withDescription(description).build();

            //向schedule注册
            scheduler.scheduleJob(jobDetail, trigger);

            Logger.info("createJob success,description:{}", jobDetail.getDescription());
        } catch (Exception e) {
            Logger.info("createJob fail,error", e);
        }

    }


}
