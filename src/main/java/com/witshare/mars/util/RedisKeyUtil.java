package com.witshare.mars.util;

import static com.witshare.mars.constant.CacheConsts.*;

/**
 * redisKey工具类
 */
public final class RedisKeyUtil {

    public static String getUserEmailVerifyCodeKey(String email, String verifyCode) {
        StringBuilder redisKey = new StringBuilder(REDIS_KEY_USER_VERIFY_CODE).append(email);
        return redisKey.toString();
    }

    public static String getRegisterEmailVerifyCodeKey(String email, String verifyCode) {
        StringBuilder redisKey = new StringBuilder(REDIS_KEY_USER_VERIFY_CODE_REGISTER).append(email);
        return redisKey.toString();
    }


    public static String getCallApiInfo(String email) {
        StringBuilder redisKey = new StringBuilder(REDIS_KEY_CALL_API_INFO).append(email);
        return redisKey.toString();
    }

    public static String getRedisLockPrefixKey() {
        StringBuilder redisKey = new StringBuilder(DISTRI_LOCK_KEY_PREFIX);
        return redisKey.toString();
    }

    public static String getCoinDataOriginKey() {
        StringBuilder redisKey = new StringBuilder(REDIS_KEY_COIN_DATA_ORIGIN);
        return redisKey.toString();
    }

    public static String getCoinDataFinalKey() {
        StringBuilder redisKey = new StringBuilder(REDIS_KEY_COIN_DATA_FINAL);
        return redisKey.toString();
    }

    public static String getUserStatisticKey(Long userId) {
        return String.format("%s:%s", REDIS_KEY_USER_INFO_STATISTIC, userId);
    }

    public static String getProjectStatisticKey(String projectGid) {
        return String.format("%s:%s", REDIS_KEY_PROJECT_INFO_STATISTIC, projectGid);
    }

    public static String getIndexBaseInfoKey() {
        StringBuilder redisKey = new StringBuilder(REDIS_KEY_INDEX_BASE_INFO);
        return redisKey.toString();
    }

    public static String getIndexProjectKey() {
        StringBuilder redisKey = new StringBuilder(REDIS_KEY_INDEX_PROJECT);
        return redisKey.toString();
    }

    public static String getIndexProjectSetKey() {
        StringBuilder redisKey = new StringBuilder(REDIS_KEY_INDEX_PROJECT_SET);
        return redisKey.toString();
    }

    public static String getIndexUserKey() {
        StringBuilder redisKey = new StringBuilder(REDIS_KEY_INDEX_USER);
        return redisKey.toString();
    }

    public static String getIndexUserSetKey() {
        StringBuilder redisKey = new StringBuilder(REDIS_KEY_INDEX_USER_SET);
        return redisKey.toString();
    }

    public static String getIndexBillBoardKey() {
        StringBuilder redisKey = new StringBuilder(REDIS_KEY_INDEX_BILLBOARD);
        return redisKey.toString();
    }

    public static String getIndexBillBoardSetKey() {
        StringBuilder redisKey = new StringBuilder(REDIS_KEY_INDEX_BILLBOARD_SET);
        return redisKey.toString();
    }

    public static String getIndexPartnerKey() {
        StringBuilder redisKey = new StringBuilder(REDIS_KEY_INDEX_PARTNER);
        return redisKey.toString();
    }

    public static String getSensitiveStatusKey() {
        StringBuilder redisKey = new StringBuilder(REDIS_KEY_SENSITIVE_STATUS);
        return redisKey.toString();
    }


    public static String getEmailTokenKey() {
        StringBuilder redisKey = new StringBuilder(REDIS_KEY_EMAIL_TOKEN);
        return redisKey.toString();
    }

    public static String getTokenEmailKey() {
        StringBuilder redisKey = new StringBuilder(REDIS_KEY_TOKEN_EMAIL);
        return redisKey.toString();
    }


}
