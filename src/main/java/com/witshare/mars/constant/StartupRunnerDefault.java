package com.witshare.mars.constant;

import com.google.gson.Gson;
import com.witshare.mars.job.Task;
import com.witshare.mars.util.WitshareUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
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
    @Autowired
    private QingObjStoreAWS3 qingObjStoreAWS3;
    @Autowired
    private Task task;

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
        //本地第一次加载不容易成功，所以把第一次给系统吧
        InputStream objects = qingObjStoreAWS3.getObjects(propertiesConfig.qingyunBucket, "test/pdfEn/TOPC_1522663766728.pdf");
        LOGGER.info("uploadToQingyun, objects is null {}", WitshareUtils.isObjectEmpty(objects));
        //加载权限信息至内存
        splitToSet(propertiesConfig.authFree, freeAuthSet);
        splitToSet(propertiesConfig.authAdminPath, adminPathSet);
        splitToSet(propertiesConfig.authAdminUser, adminUserSet);
        LOGGER.info("authInfo, freeAuthSet:{}, adminPathSet:{}, adminUserSet:{}", GSON.toJson(freeAuthSet), GSON.toJson(adminPathSet), GSON.toJson(adminUserSet));
        //同步统计数据
//        task.syncGasPrice();
//        task.syncChannelRegisterCount();
//        task.syncProjectDailyInfo();
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
