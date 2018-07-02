package com.witshare.mars.constant;

import java.util.LinkedList;
import java.util.List;

/**
 * 用户认购交易状态
 */
public enum EnumUserTxStatus {


    Status0(0, "初始状态"),
    Status1(1, "交易还未被打包"),
    Status2(2, "验证成功"),
    Status21(21, "验证失败（to不是平台地址)"),
    Status22(22, "验证失败（from不匹配）"),
    Status23(23, "验证失败（金额不匹配）"),
    Status3(3, "交易失败"),
    Status4(4, "交易不存在"),;

    private Integer status;
    private String des;

    EnumUserTxStatus(Integer status, String des) {
        this.status = status;
        this.des = des;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDes() {
        return des;
    }

    public static List<Integer> getStatisticStatuses() {
        LinkedList<Integer> status = new LinkedList<>();
        status.add(Status1.getStatus());
        status.add(Status2.getStatus());
        status.add(Status3.getStatus());
        status.add(Status4.getStatus());
        return status;
    }

    public static EnumUserTxStatus get(Integer status) {
        for (EnumUserTxStatus Status : EnumUserTxStatus.values()) {
            if (Status.status.equals(status)) {
                return Status;
            }
        }
        return null;
    }

}
