package com.witshare.mars.pojo.util;


public class SmsRequest {

    private String appId;
    private String phoneNo;
    private String body;
    private String msgType;
    private String batchId;
    private String extra;

    public SmsRequest() {
    }

    public SmsRequest(String appId, String phoneNo, String body, String msgType, String batchId, String extra) {
        this.appId = appId;
        this.phoneNo = phoneNo;
        this.body = body;
        this.msgType = msgType;
        this.batchId = batchId;
        this.extra = extra;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}

