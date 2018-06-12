package com.witshare.mars.job;

import com.witshare.mars.config.DistributedLocker;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.util.RedisKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.witshare.mars.job.Task.redis_lock;

/**
 * 跑批统计数据
 **/
@Component
public class StatisticDataJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticDataJob.class);

    private final static String CLEAN_INDEX_INFO_LOCK = "cleanIndexInfoLock";

    @Autowired
    private DistributedLocker distributedLocker;

    @Autowired
    private RedisCommonDao redisCommonDao;


    /**
     * 定时删除缓存数据
     */
    @Scheduled(cron = "0 0/20 * * * ?")
    public void cleanIndexInfoLock() {
        String lockId = distributedLocker.lock(CLEAN_INDEX_INFO_LOCK, redis_lock);
        if (lockId == null) {
            LOGGER.info("cleanIndexInfoLock pushTask-other_is_excuting");
            return;
        }
        redisCommonDao.delRedisKey(RedisKeyUtil.getIndexProjectKey());
        redisCommonDao.delRedisKey(RedisKeyUtil.getIndexUserKey());
    }


}
