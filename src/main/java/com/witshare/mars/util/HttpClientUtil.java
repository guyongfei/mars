package com.witshare.mars.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * HTTP链接工具类
 */
public class HttpClientUtil {
    static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String doPost(String url, String bodyJson, String token, String tokenHeaderName) {
        HttpPost httpPost = null;
        String result = null;
        try {
            //创建httpclient对象 
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //创建post方式请求对象
            httpPost = new HttpPost();
            httpPost.setURI(new URI(url));

            if (bodyJson != null && bodyJson != "") {
                //设置body参数  
                StringEntity entity = new StringEntity(bodyJson, "UTF-8");
//                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            if (StringUtils.isNotBlank(token)) {
                httpPost.setHeader(StringUtils.isBlank(tokenHeaderName) ? "X-IbeeAuth-Token" : tokenHeaderName, token);
            }

            //执行请求操作，并拿到结果（同步阻塞） 
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    //按指定编码转换结果实体为String类型  
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(" doPost method fail!" + ex);
//            logger.debug("discover service: {} => {}", serviceName, serviceAddress);
        }
        return result;
    }

    public static String doPost(String url, String bodyJson, String token) {
        return doPost(url, bodyJson, token, null);
    }

    public static String doGet(String url, String token) {
        return doGet(url, token, null);
    }

    public static String doGet(String url, String token, String tokenHeaderName) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            //设置header信息
            if (StringUtils.isNotBlank(token)) {
                httpGet.setHeader(StringUtils.isBlank(tokenHeaderName) ? "X-IbeeAuth-Token" : tokenHeaderName, token);
            }
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }
}
