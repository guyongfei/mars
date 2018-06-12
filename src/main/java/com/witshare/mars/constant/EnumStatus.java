package com.witshare.mars.constant;

/**
 * 状态枚举
 */
public enum EnumStatus {

    InValid(0, "无效的"),
    Valid(1, "有效的");

    private int value;
    private String name;

    EnumStatus() {
    }

    EnumStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static EnumStatus getByValue(int value) {
        for (EnumStatus Status : EnumStatus.values()) {
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
