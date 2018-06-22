package com.witshare.mars.job;

import com.witshare.mars.config.DistributedLocker;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.service.EmailService;
import com.witshare.mars.util.RedisKeyUtil;
import com.witshare.mars.util.WitshareUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.witshare.mars.config.ConfigTaskPool.TASK_EXECUTOR;
import static com.witshare.mars.constant.CacheConsts.PHONE_NO_VERIFY_CODE_EXPIRE_MINUTE;

/**
 * 定时任务类
 */
@Component
public class Task {
    public final static int redis_lock = 10 * 60;
    private final static String EXCHANGE_SPIDER_LOCK = "exchangeLock";
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private EmailService emailService;
    @Autowired
    private DistributedLocker distributedLocker;
    @Autowired
    private RedisCommonDao redisCommonDao;
    @Autowired
    private StatisticTxJob statisticTxJob;

//    @Async(TASK_EXECUTOR)
    public void sendEmailVerifyCode(String email, boolean register) {
        if (StringUtils.isEmpty(email)) {
            throw new WitshareException(EnumResponseText.ErrorEmail);
        }
        String verifyCode = WitshareUtils.generateRandomNums(6);
        try {
            String redisKey = register ? RedisKeyUtil.getRegisterEmailVerifyCodeKey(email, verifyCode) : RedisKeyUtil.getUserEmailVerifyCodeKey(email, verifyCode);
            //todo 为了解决谷歌云平台不能发送smtp的问题，在在正式环境注销掉吧
//            emailService.sendVerifyCode(email, verifyCode);
            emailService.service(email, verifyCode);
            redisCommonDao.put(redisKey, verifyCode, PHONE_NO_VERIFY_CODE_EXPIRE_MINUTE, TimeUnit.MINUTES);
        } catch (Exception e) {
            LOGGER.error("sendEmailVerifyCode fail.email:{},verifyCode:{}", email, verifyCode, e);
        }
        LOGGER.info("sendEmailVerifyCode success.email:{},verifyCode:{}.", email, verifyCode);
    }

    /**
     * 执行获取交易所价格的任务
     */
    @Scheduled(cron = "0 0 0/30 * * ?")
    public void statisticTx() {
        String lockId = distributedLocker.lock(EXCHANGE_SPIDER_LOCK, redis_lock);
        if (lockId == null) {
            LOGGER.info("statisticTokenTx pushTask-other_is_excuting");
            return;
        }
//        exchangeSpiderJob.getCoinDataJob();
    }

}
