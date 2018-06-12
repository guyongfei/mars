package com.witshare.mars.pojo.util;

import java.io.Serializable;

import static com.witshare.mars.constant.EnumWitshare.SYS_ERROR;


public class ResponseBean implements Serializable {


    private Boolean success;
    private String message;
    private Object data;


    public ResponseBean() {
    }


    public ResponseBean(Boolean success) {
        this.success = success;
        this.message = "";
    }

    public ResponseBean(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseBean(Boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ResponseBean(Object data) {
        this.success = Boolean.TRUE;
        this.message = "success";
        this.data = data;
    }

    public static ResponseBean newInstanceSuccess() {
        return new ResponseBean(true);
    }

    public static ResponseBean newInstanceSuccess(Object data) {
        return newInstanceError(SYS_ERROR.value()).setData(data);
    }

    public static ResponseBean newInstanceError(String errorInfo) {
        return new ResponseBean(false, errorInfo);
    }

    public Boolean getSuccess() {
        return success;
    }

    public ResponseBean setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseBean setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseBean setData(Object data) {
        this.data = data;
        return this;
    }
}
