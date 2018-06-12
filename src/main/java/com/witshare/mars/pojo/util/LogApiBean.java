package com.witshare.mars.pojo.util;

import java.util.Date;


public class LogApiBean {

    private String projectName;
    private Date startTime;
    private Date endTime;
    private String methodType;
    private Object request;
    private Object response;
    private int responseStatus;

    public LogApiBean() {
    }


    public static LogApiBean newInstance() {
        return new LogApiBean();
    }


    public String getProjectName() {
        return projectName;
    }

    public LogApiBean setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public LogApiBean setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public LogApiBean setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getMethodType() {
        return methodType;
    }

    public LogApiBean setMethodType(String methodType) {
        this.methodType = methodType;
        return this;
    }

    public Object getRequest() {
        return request;
    }

    public LogApiBean setRequest(Object request) {
        this.request = request;
        return this;
    }

    public Object getResponse() {
        return response;
    }

    public LogApiBean setResponse(Object response) {
        this.response = response;
        return this;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public LogApiBean setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
        return this;
    }
}
