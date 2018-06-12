package com.witshare.mars.controller;


import com.witshare.mars.constant.PropertiesConfig;
import com.witshare.mars.constant.QingObjStoreAWS3;
import com.witshare.mars.job.coindata.ExchangeSpiderJob;
import com.witshare.mars.pojo.util.ResponseBean;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
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
    private ExchangeSpiderJob exchangeSpiderJob;
    @Autowired
    private QingObjStoreAWS3 qingObjStoreAWS3;
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
//        exchangeSpiderJob.getCoinDataJob();
        return appver;
    }


/*    @ResponseBody
    @RequestMapping(value = "/test-qingyun-upload", method = RequestMethod.POST)
    public ResponseBean testQingyunUpload(@RequestBody Map<String, String> requestBody) {
        ResponseBean responseBean;
        try {
            String data = requestBody.get("data");
            String pdfUrl = qingyunStorageService.uploadToQingyun(data, "test", EnumStorage.Txt);
            responseBean = new ResponseBean(Boolean.TRUE, pdfUrl);
        } catch (Exception e) {
            LOGGER.error("testQingyunUpload fail,", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        }
        return responseBean;
    }*/

    @ResponseBody
    @RequestMapping(value = "/test-qingyun-download", method = RequestMethod.GET)
    public void testQingyunDownload(HttpServletResponse response) throws Exception {
        ResponseBean responseBean;
        InputStream inputStream = qingObjStoreAWS3.getObjects(propertiesConfig.qingyunBucket, "test/pdfZh/LET____20180327.pdf");
        OutputStream out = response.getOutputStream();
        byte[] b = new byte[1024];
        while ((inputStream.read(b)) != -1) {
            out.write(b);
        }
        out.flush();
        inputStream.close();
        out.close();
    }

    @ResponseBody
    @RequestMapping(value = "/test-qingyun-list", method = RequestMethod.GET)
    public ResponseBean testQingyunListObjects() {
        Object objs = qingObjStoreAWS3.listObjects(propertiesConfig.qingyunBucket, "test");
        return new ResponseBean(Boolean.TRUE, "", JSONObject.valueToString(objs));
    }

}
