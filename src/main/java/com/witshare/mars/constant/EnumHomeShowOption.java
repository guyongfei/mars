package com.witshare.mars.constant;

/**
 * 首页展示信息枚举
 */
public enum EnumHomeShowOption {

    BILLBOARD(1, "billBoardIds", "billBoardNum"),
    PROJECT(2, "projectIds", "projectNum"),
    USER(3, "userIds", "projectNum");

    private Integer type;
    private String idKey;
    private String numberKey;


    EnumHomeShowOption(Integer type, String idKey, String numberKey) {
        this.type = type;
        this.idKey = idKey;
        this.numberKey = numberKey;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIdKey() {
        return idKey;
    }

    public void setIdKey(String idKey) {
        this.idKey = idKey;
    }

    public String getNumberKey() {
        return numberKey;
    }

    public void setNumberKey(String numberKey) {
        this.numberKey = numberKey;
    }
}
