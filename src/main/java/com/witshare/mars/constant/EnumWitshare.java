package com.witshare.mars.constant;

/**
 * 异常枚举
 */
public enum EnumWitshare {

    SYS_ERROR("0000", "系统异常");

    private String key;
    private String value;

    EnumWitshare(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String value() {
        return value;
    }

    public String key() {
        return key;
    }

}
