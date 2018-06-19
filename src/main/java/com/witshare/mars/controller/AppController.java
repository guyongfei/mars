package com.witshare.mars.controller;


import com.witshare.mars.constant.PropertiesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Properties;

/**
 * 项目发版信息控制类
 */
@RequestMapping("/")
@RestController
public class AppController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);
    private static String hostname = "unknown";

    static {
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private Properties buildInfo = null;
    @Autowired
    private PropertiesConfig propertiesConfig;

    public AppController() throws Exception {
        buildInfo = PropertiesLoaderUtils.loadAllProperties("properties/buildinfo.properties");
    }

    /**
     * 获取发版信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/appver")
    public HashMap<String, String> appVersion(HttpServletRequest request) {
        HashMap<String, String> appver = new HashMap<String, String>(10);
        if (buildInfo != null) {
            for (Object key : buildInfo.keySet()) {
                appver.put(key.toString(), buildInfo.getProperty(key.toString()));
            }
        }
        appver.put("run.host", hostname);
        if (request.getHeader("X-Real-IP") != null) {
            appver.put("run.remote", request.getHeader("X-Real-IP"));
        } else {
            appver.put("run.remote", request.getRemoteHost());
        }
        return appver;
    }


}
