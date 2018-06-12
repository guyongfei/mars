package com.witshare.mars.exception;

import com.witshare.mars.config.CurrentThreadContext;
import com.witshare.mars.constant.EnumI18NProject;
import com.witshare.mars.constant.EnumResponseText;

/**
 * 项目异常处理类
 */
public class WitshareException extends RuntimeException {

    private String code;
    private String message;


    public WitshareException(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public WitshareException(String message) {
        this.message = message;
    }

    public WitshareException(EnumResponseText enumResponseText) {
        String i18N = CurrentThreadContext.getI18N();
        if (EnumI18NProject.PROJECT_DESCRIPTION_EN.getRequestLanguage().equals(i18N)) {
            this.setMessage(enumResponseText.getEn());
        } else {
            this.setMessage(enumResponseText.getZh());
        }
    }


    public WitshareException() {
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
