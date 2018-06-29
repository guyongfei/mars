package com.witshare.mars.job;

import com.witshare.mars.config.DistributedLocker;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.service.EmailService;
import com.witshare.mars.service.ProjectDailyInfoService;
import com.witshare.mars.service.TransactionService;
import com.witshare.mars.util.RedisKeyUtil;
import com.witshare.mars.util.WitshareUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.witshare.mars.constant.CacheConsts.PHONE_NO_VERIFY_CODE_EXPIRE_MINUTE;

/**
 * 定时任务类
 */
@Component
public class Task {
    public final static int GAS_PRICE_REDIS_LOCK = 60;
    public final static int PROJECT_DAILY_INFO_REDIS_LOCK = 60;
    private final static String GAS_PRICE_LOCK = "gasPrice";
    private final static String PROJECT_DAILY_INFO_LOCK = "projectDailyInfo";
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private EmailService emailService;
    @Autowired
    private DistributedLocker distributedLocker;
    @Autowired
    private RedisCommonDao redisCommonDao;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ProjectDailyInfoService projectDailyInfoService;

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
     * 同步Moon价格
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void syncGasPrice() {
        String lockId = distributedLocker.lock(GAS_PRICE_LOCK, GAS_PRICE_REDIS_LOCK);
        if (lockId == null) {
            LOGGER.info("syncGasPrice pushTask-other_is_execute");
            return;
        }
        transactionService.syncGasPrice();
    }

    /**
     * 同步项目统计数据
     */
    @Scheduled(cron = "0 2/30 * * * ?")
    public void syncProjectDailyInfo() {
        String lockId = distributedLocker.lock(PROJECT_DAILY_INFO_LOCK, PROJECT_DAILY_INFO_REDIS_LOCK);
        if (lockId == null) {
            LOGGER.info("syncProjectDailyInfo pushTask-other_is_execute");
            return;
        }
        projectDailyInfoService.syncDailyInfo();
    }

}
