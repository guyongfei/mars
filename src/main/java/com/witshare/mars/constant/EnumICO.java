package com.witshare.mars.constant;

/**
 * 项目上市状态枚举
 */
public enum EnumICO {

    Listed(1, "已上市"),
    Listing(2, "认筹中"),
    Unlisted(0, "未上市");

    private int value;
    private String name;

    EnumICO() {
    }

    EnumICO(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static EnumICO getByValue(int value) {
        for (EnumICO Status : EnumICO.values()) {
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
