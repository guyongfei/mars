package com.witshare.mars.job;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;


public class JobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext = null;


    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {

        Object jobInstance = super.createJobInstance(bundle);

        /** 把Job交给Spring来管理 */
        applicationContext.getAutowireCapableBeanFactory().autowireBean(jobInstance);

        return jobInstance;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
