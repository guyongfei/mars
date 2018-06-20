package com.witshare.mars.pojo.vo;

import java.sql.Timestamp;

public class SysUserAddressVo {


    private String userGid;

    private String projectGid;

    private String payEthAddress;

    private String getTokenAddress;

    private SysUserAddressVo() {
    }

    public static SysUserAddressVo newInstance() {
        return new SysUserAddressVo();
    }

    public String getUserGid() {
        return userGid;
    }

    public SysUserAddressVo setUserGid(String userGid) {
        this.userGid = userGid;
        return this;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public SysUserAddressVo setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public String getPayEthAddress() {
        return payEthAddress;
    }

    public SysUserAddressVo setPayEthAddress(String payEthAddress) {
        this.payEthAddress = payEthAddress;
        return this;
    }

    public String getGetTokenAddress() {
        return getTokenAddress;
    }

    public SysUserAddressVo setGetTokenAddress(String getTokenAddress) {
        this.getTokenAddress = getTokenAddress;
        return this;
    }
}