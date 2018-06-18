package com.witshare.mars.constant;

/**
 * 项目状态
 */
public enum EnumProjectStatus {

    Status0(0, "未开始"),
    Status1(1, "未到软顶"),
    Status2(2, "未到硬顶"),
    Status3(3, "认筹完成且成功"),
    Status4(4, "认筹完成但失败"),;

    private Integer status;
    private String des;

    EnumProjectStatus(Integer status, String des) {
        this.status = status;
        this.des = des;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDes() {
        return des;
    }
}
