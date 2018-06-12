package com.witshare.mars.constant;

/**
 * Created by user on 2018/3/24.
 */
public enum EnumProjectWebSite {

    Social(0, "社交网站"),
    Exchange(1, "交易所网站");

    private int value;
    private String name;

    EnumProjectWebSite() {
    }

    EnumProjectWebSite(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static EnumProjectWebSite getByValue(int value) {
        for (EnumProjectWebSite Status : EnumProjectWebSite.values()) {
            if (Status.value == value) {
                return Status;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
