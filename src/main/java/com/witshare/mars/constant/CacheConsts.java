package com.witshare.mars.constant;


public class CacheConsts {
    public static final int RETRY_TIMES = 3;
    public static final int SHIRO_SESSION_EXPIRE_TIME = -1;
    public static final int PHONE_NO_VERIFY_CODE_EXPIRE_MINUTE = 15;
    //tx_pay_id为主键id+10000
    public static final int TX_ID_INCREMENT = 10000;
    public static final String PROJECT_NAME = "mars";
    public static final String KEY_DATA = "data";

    public static final String COOKIE_USER_TOKEN = "witshare.user.token";
    public static final String COOKIE_I18N_LANGUAGE = "witshare.i18n.language";
    public static final String LANGUAGE_CN = "cn";
    public static final String LANGUAGE_EN = "en";
    public static final String LANGUAGE_KO = "ko";
    public static final String LANGUAGE_JA = "ja";

    public static final String PAGE_NUM = "pageNum";
    public static final String PAGE_SIZE = "pageSize";
    public static final String WHITE_PAPER_LINK = "whitePaperLink";
    public static final String DEFAULT_PAGE_NUM_STR = "1";
    public static final String DEFAULT_PAGE_SIZE_STR = "10";


    public static final String PARAM_PASSWORD_ENCRYPTION_ALGORITHM = "md5";
    public static final int PARAM_PASSWORD_ENCRYPTION_TIMES = 3;
    private final static String REDIS_PREFIX = "ibs:" + PROJECT_NAME + ":";
    public static final String REDIS_KEY_PHONE_NO_VERIFY_CODE_LIST_REGISTER = REDIS_PREFIX + "cache:register-verify-code-list";
    public static final String REDIS_KEY_PHONE_NO_VERIFY_CODE_LIST = REDIS_PREFIX + "cache:verify-code-list";
    public static final String REDIS_KEY_USER_VERIFY_CODE_REGISTER = REDIS_PREFIX + "cache:register-email:";
    public static final String REDIS_KEY_USER_VERIFY_CODE = REDIS_PREFIX + "cache:email:";
    public static final String REDIS_KEY_USER_INFO_STATISTIC = REDIS_PREFIX + "cache:user-info-statistic";

    public static final String REDIS_KEY_CALL_API_INFO = REDIS_PREFIX + "db:call-api-info:";

    public static final String REDIS_KEY_EMAIL_TOKEN = REDIS_PREFIX + "db:email-token";
    public static final String REDIS_KEY_TOKEN_EMAIL = REDIS_PREFIX + "db:token-email";

    public static final String REDIS_KEY_INDEX_USER = REDIS_PREFIX + "cache:index-user";
    public static final String REDIS_KEY_INDEX_USER_SET = REDIS_PREFIX + "cache:index-user_set";

    public static final String DISTRI_LOCK_KEY_PREFIX = REDIS_PREFIX + "cache:dislock";

    public static final String REDIS_KEY_PROJECT_FRONT = REDIS_PREFIX + "cache:project-info-front";
    public static final String REDIS_KEY_PROJECT = REDIS_PREFIX + "cache:project";
    public static final String REDIS_KEY_PROJECT_STATISTIC = REDIS_PREFIX + "cache:project-statistic";

    public static final String REDIS_KEY_GAS_PRICE = REDIS_PREFIX + "db:gas-price";
    public static final String REDIS_KEY_platform_address = REDIS_PREFIX + "db:platform-address";

    public static final String REDIS_KEY_WALLET_V3JSON = REDIS_PREFIX + "db:walletV3Json";

}
