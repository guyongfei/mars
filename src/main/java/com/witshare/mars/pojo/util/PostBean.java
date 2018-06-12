package com.witshare.mars.pojo.util;


public class PostBean {
    private String appKey;
    private String url;
    private String token;
    private String bodyJson;
    private Object requestBody;
    private String callBackUrl;

    public PostBean() {
    }

    public PostBean(String appKey, String url, String token, String userGid, String bodyJson) {
        this.appKey = appKey;
        this.url = url;
        this.token = token;
        this.bodyJson = bodyJson;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBodyJson() {
        return bodyJson;
    }

    public void setBodyJson(String bodyJson) {
        this.bodyJson = bodyJson;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }
}
