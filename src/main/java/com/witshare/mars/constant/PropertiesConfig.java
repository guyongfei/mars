package com.witshare.mars.constant;


import com.witshare.mars.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 配置文件类
 */
@Component
@Order(value = 1)
public class PropertiesConfig extends RuntimeException implements CommandLineRunner {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${project.name}")
    public String projectName;
    @Value("${log.api.write}")
    public String writeApiLog;


    //对象存储
    @Value("${qingyun.bucket}")
    public String qingyunBucket;
    @Value("${qingyun.s3AK}")
    public String qingyunS3AK;
    @Value("${qingyun.s3SK}")
    public String qingyunS3SK;
    @Value("${qingyun.zoneKey}")
    public String qingyunZoneKey;
    @Value("${qingyun.host}")
    public String qingyunHost;
    @Value("${qingyun.httpUrl}")
    public String qingyunHttpUrl;
    @Value("${qingyun.objectName}")
    public String qingyunObjectName;

    @Value("${mail.subjectname}")
    public String mailSubjectName;
    @Value("${mail.account.office365}")
    public String mailAccountOffice365;
    @Value("${mail.account.hotmail}")
    public String mailAccountHotmail;

    @Value("${project.auth.free}")
    public String authFree;
    @Value("${project.auth.admin.path}")
    public String authAdminPath;
    @Value("${project.auth.admin.user}")
    public String authAdminUser;

    @Value("${project.contextPath}")
    public String contextPath;

    @Value("${project.frontPath}")
    public String frontPath;
    @Value("${project.default.avatar}")
    public String defaultAvatar;

    @Override
    public void run(String... args) {
        LOGGER.info("PropertiesConfig:{}", JsonUtils.toGsonPretty(this));
    }


}
