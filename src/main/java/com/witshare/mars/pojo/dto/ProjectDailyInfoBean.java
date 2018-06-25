package com.witshare.mars.pojo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class ProjectDailyInfoBean {

    private Long id;

    private String projectGid;

    private String projectToken;

    private BigDecimal getEthAmount;

    private BigDecimal actualGetEthAmount;

    private BigDecimal payTokenAmount;

    private BigDecimal actualPayTokenAmount;

    private Integer userCount;

    private Integer actualUserCount;

    private Integer txCount;

    private Integer actualTxCount;

    private Date currentDay;

    private Timestamp createTime;

    private Timestamp updateTime;

    private ProjectDailyInfoBean() {
    }

    public static ProjectDailyInfoBean newInstance() {
        return new ProjectDailyInfoBean();
    }

    public Long getId() {
        return id;
    }

    public ProjectDailyInfoBean setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public ProjectDailyInfoBean setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public String getProjectToken() {
        return projectToken;
    }

    public ProjectDailyInfoBean setProjectToken(String projectToken) {
        this.projectToken = projectToken;
        return this;
    }


    public BigDecimal getGetEthAmount() {
        return getEthAmount;
    }

    public ProjectDailyInfoBean setGetEthAmount(BigDecimal getEthAmount) {
        this.getEthAmount = getEthAmount;
        return this;
    }

    public BigDecimal getActualGetEthAmount() {
        return actualGetEthAmount;
    }

    public ProjectDailyInfoBean setActualGetEthAmount(BigDecimal actualGetEthAmount) {
        this.actualGetEthAmount = actualGetEthAmount;
        return this;
    }

    public BigDecimal getPayTokenAmount() {
        return payTokenAmount;
    }

    public ProjectDailyInfoBean setPayTokenAmount(BigDecimal payTokenAmount) {
        this.payTokenAmount = payTokenAmount;
        return this;
    }

    public BigDecimal getActualPayTokenAmount() {
        return actualPayTokenAmount;
    }

    public ProjectDailyInfoBean setActualPayTokenAmount(BigDecimal actualPayTokenAmount) {
        this.actualPayTokenAmount = actualPayTokenAmount;
        return this;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public ProjectDailyInfoBean setUserCount(Integer userCount) {
        this.userCount = userCount;
        return this;
    }

    public Integer getActualUserCount() {
        return actualUserCount;
    }

    public ProjectDailyInfoBean setActualUserCount(Integer actualUserCount) {
        this.actualUserCount = actualUserCount;
        return this;
    }

    public Integer getTxCount() {
        return txCount;
    }

    public ProjectDailyInfoBean setTxCount(Integer txCount) {
        this.txCount = txCount;
        return this;
    }

    public Integer getActualTxCount() {
        return actualTxCount;
    }

    public ProjectDailyInfoBean setActualTxCount(Integer actualTxCount) {
        this.actualTxCount = actualTxCount;
        return this;
    }

    public Date getCurrentDay() {
        return currentDay;
    }

    public ProjectDailyInfoBean setCurrentDay(Date currentDay) {
        this.currentDay = currentDay;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public ProjectDailyInfoBean setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public ProjectDailyInfoBean setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}