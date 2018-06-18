package com.witshare.mars.constant;

import com.google.gson.Gson;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 启动后运行类
 */
@Component
@Order(value = 1)
public class StartupRunnerDefault implements CommandLineRunner {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final Gson GSON = new Gson();
    @Resource
    private PropertiesConfig propertiesConfig;
    @Resource
    private QingObjStoreAWS3 qingObjStoreAWS3;

    private Set<String> freeAuthSet = ConcurrentHashMap.<String>newKeySet();
    private Set<String> adminPathSet = ConcurrentHashMap.<String>newKeySet();
    private Set<String> adminUserSet = ConcurrentHashMap.<String>newKeySet();

    /**
     * 配置项加载
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) {

        //加载权限信息至内存
        splitToSet(propertiesConfig.authFree, freeAuthSet);
        splitToSet(propertiesConfig.authAdminPath, adminPathSet);
        splitToSet(propertiesConfig.authAdminUser, adminUserSet);
        LOGGER.info("authInfo, freeAuthSet:{}, adminPathSet:{}, adminUserSet:{}", GSON.toJson(freeAuthSet), GSON.toJson(adminPathSet), GSON.toJson(adminUserSet));
    }

    private void splitToSet(String string, Collection collection) {
        if (StringUtils.isEmpty(string)) {
            return;
        }
        collection.clear();
        String[] split = string.split(",");
        List<String> strings = Arrays.asList(split);
        collection.addAll(strings);
    }

    public Set<String> getFreeAuthSet() {
        if (CollectionUtils.isEmpty(freeAuthSet)) {
            this.run();
        }
        return freeAuthSet;
    }

    public Set<String> getAdminPathSet() {
        if (CollectionUtils.isEmpty(adminPathSet)) {
            this.run();
        }
        return adminPathSet;
    }


    public Set<String> getAdminUserSet() {
        if (CollectionUtils.isEmpty(adminUserSet)) {
            this.run();
        }
        return adminUserSet;
    }

}
