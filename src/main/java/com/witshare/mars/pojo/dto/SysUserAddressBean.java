package com.witshare.mars.pojo.dto;

import java.sql.Timestamp;

public class SysUserAddressBean {

    private Long id;


    private String userGid;

    private String email;


    private String projectGid;

    private String projectToken;

    private String payEthAddress;

    private String getTokenAddress;

    private Timestamp createTime;

    private Timestamp updateTime;

    private SysUserAddressBean() {
    }

    public static SysUserAddressBean newInstance() {
        return new SysUserAddressBean();
    }

    public Long getId() {
        return id;
    }

    public SysUserAddressBean setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserGid() {
        return userGid;
    }

    public SysUserAddressBean setUserGid(String userGid) {
        this.userGid = userGid;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SysUserAddressBean setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public SysUserAddressBean setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public String getProjectToken() {
        return projectToken;
    }

    public SysUserAddressBean setProjectToken(String projectToken) {
        this.projectToken = projectToken;
        return this;
    }

    public String getPayEthAddress() {
        return payEthAddress;
    }

    public SysUserAddressBean setPayEthAddress(String payEthAddress) {
        this.payEthAddress = payEthAddress;
        return this;
    }

    public String getGetTokenAddress() {
        return getTokenAddress;
    }

    public SysUserAddressBean setGetTokenAddress(String getTokenAddress) {
        this.getTokenAddress = getTokenAddress;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public SysUserAddressBean setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public SysUserAddressBean setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}