package com.witshare.mars.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池类
 */
@EnableAsync
@Configuration
public class ConfigTaskPool {
    public final static String TASK_EXECUTOR = "taskExecutor";
    public final static String TRANS_EXECUTOR = "transExecutor";

    @Bean(name = TASK_EXECUTOR)
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix(TASK_EXECUTOR + "-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }


    @Bean(name = TRANS_EXECUTOR)
    public ExecutorService msgExecutor() {
        return Executors.newScheduledThreadPool(10);
    }
}
