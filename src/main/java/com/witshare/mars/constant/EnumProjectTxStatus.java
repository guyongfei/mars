package com.witshare.mars.constant;

import java.util.LinkedList;
import java.util.List;

/**
 * 项目交易状态
 */
public enum EnumProjectTxStatus {

    Status0(0, "初始态"),
    Status1(1, "项目-->平台"),
    Status2(2, "平台-->项目"),
    Status3(3, "其他"),;

    private Integer status;
    private String des;

    EnumProjectTxStatus(Integer status, String des) {
        this.status = status;
        this.des = des;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDes() {
        return des;
    }


    public static EnumProjectTxStatus get(Integer status) {
        for (EnumProjectTxStatus Status : EnumProjectTxStatus.values()) {
            if (Status.status.equals(status)) {
                return Status;
            }
        }
        return null;
    }

}
