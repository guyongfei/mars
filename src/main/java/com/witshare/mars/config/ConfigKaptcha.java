package com.witshare.mars.config;

import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class ConfigKaptcha {

    @Autowired
    private MyJobFactory myJobFactory;

//    @Bean(name = "marsScheduler")
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("quartzDatasource") DataSource dataSource) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        //延时启动
        factory.setStartupDelay(2);
        //加载配置文件
        factory.setQuartzProperties(quartzProperties());
        //加载数据源
        factory.setDataSource(dataSource);
        // 自定义Job Factory，用于Spring注入
        factory.setJobFactory(myJobFactory);
        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("properties/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }

}
