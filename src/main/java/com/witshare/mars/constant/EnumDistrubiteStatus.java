package com.witshare.mars.constant;

import java.util.LinkedList;
import java.util.List;

/**
 * 打币状态
 */
public enum EnumDistrubiteStatus {

        
    Status0(0, "未打币"),
    Status1(1, "打币中"),
    Status2(2, "成功"),
    Status3(3, "失败"),;

    private Integer status;
    private String des;

    EnumDistrubiteStatus(Integer status, String des) {
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
        status.add(Status0.getStatus());
        status.add(Status1.getStatus());
        status.add(Status2.getStatus());
        status.add(Status3.getStatus());
        return status;
    }

    public static EnumDistrubiteStatus get(Integer status) {
        for (EnumDistrubiteStatus Status : EnumDistrubiteStatus.values()) {
            if (Status.status.equals(status)) {
                return Status;
            }
        }
        return null;
    }

}
