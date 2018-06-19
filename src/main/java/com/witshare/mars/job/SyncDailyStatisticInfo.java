package com.witshare.mars.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SyncDailyStatisticInfo implements BaseJob {

    private static Logger logger = LoggerFactory.getLogger(SyncDailyStatisticInfo.class);

    public SyncDailyStatisticInfo() {

    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        try {
            this.syncStatisticInfo();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("SyncDailyStatisticInfo: ", e);
        }
    }

    /**
     * 同步统计数据
     */
    private void syncStatisticInfo() {


    }

}  
