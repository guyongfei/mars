package com.witshare.mars.pojo.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class ProjectDailyInfo {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public void setProjectGid(String projectGid) {
        this.projectGid = projectGid == null ? null : projectGid.trim();
    }

    public String getProjectToken() {
        return projectToken;
    }

    public void setProjectToken(String projectToken) {
        this.projectToken = projectToken == null ? null : projectToken.trim();
    }

    public BigDecimal getGetEthAmount() {
        return getEthAmount;
    }

    public void setGetEthAmount(BigDecimal getEthAmount) {
        this.getEthAmount = getEthAmount;
    }

    public BigDecimal getActualGetEthAmount() {
        return actualGetEthAmount;
    }

    public void setActualGetEthAmount(BigDecimal actualGetEthAmount) {
        this.actualGetEthAmount = actualGetEthAmount;
    }

    public BigDecimal getPayTokenAmount() {
        return payTokenAmount;
    }

    public void setPayTokenAmount(BigDecimal payTokenAmount) {
        this.payTokenAmount = payTokenAmount;
    }

    public BigDecimal getActualPayTokenAmount() {
        return actualPayTokenAmount;
    }

    public void setActualPayTokenAmount(BigDecimal actualPayTokenAmount) {
        this.actualPayTokenAmount = actualPayTokenAmount;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getActualUserCount() {
        return actualUserCount;
    }

    public void setActualUserCount(Integer actualUserCount) {
        this.actualUserCount = actualUserCount;
    }

    public Integer getTxCount() {
        return txCount;
    }

    public void setTxCount(Integer txCount) {
        this.txCount = txCount;
    }

    public Integer getActualTxCount() {
        return actualTxCount;
    }

    public void setActualTxCount(Integer actualTxCount) {
        this.actualTxCount = actualTxCount;
    }

    public Date getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(Date currentDay) {
        this.currentDay = currentDay;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}