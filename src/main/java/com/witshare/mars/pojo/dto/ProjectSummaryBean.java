package com.witshare.mars.pojo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProjectSummaryBean {
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

    private Timestamp createTime;

    private Timestamp updateTime;

    public ProjectSummaryBean() {
    }

    public static ProjectSummaryBean newInstance() {
        return new ProjectSummaryBean();
    }

    public Long getId() {
        return id;
    }

    public ProjectSummaryBean setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public ProjectSummaryBean setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public String getProjectToken() {
        return projectToken;
    }

    public ProjectSummaryBean setProjectToken(String projectToken) {
        this.projectToken = projectToken;
        return this;
    }

    public BigDecimal getGetEthAmount() {
        return getEthAmount;
    }

    public ProjectSummaryBean setGetEthAmount(BigDecimal getEthAmount) {
        this.getEthAmount = getEthAmount;
        return this;
    }

    public BigDecimal getActualGetEthAmount() {
        return actualGetEthAmount;
    }

    public ProjectSummaryBean setActualGetEthAmount(BigDecimal actualGetEthAmount) {
        this.actualGetEthAmount = actualGetEthAmount;
        return this;
    }

    public BigDecimal getPayTokenAmount() {
        return payTokenAmount;
    }

    public ProjectSummaryBean setPayTokenAmount(BigDecimal payTokenAmount) {
        this.payTokenAmount = payTokenAmount;
        return this;
    }

    public BigDecimal getActualPayTokenAmount() {
        return actualPayTokenAmount;
    }

    public ProjectSummaryBean setActualPayTokenAmount(BigDecimal actualPayTokenAmount) {
        this.actualPayTokenAmount = actualPayTokenAmount;
        return this;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public ProjectSummaryBean setUserCount(Integer userCount) {
        this.userCount = userCount;
        return this;
    }

    public Integer getActualUserCount() {
        return actualUserCount;
    }

    public ProjectSummaryBean setActualUserCount(Integer actualUserCount) {
        this.actualUserCount = actualUserCount;
        return this;
    }

    public Integer getTxCount() {
        return txCount;
    }

    public ProjectSummaryBean setTxCount(Integer txCount) {
        this.txCount = txCount;
        return this;
    }

    public Integer getActualTxCount() {
        return actualTxCount;
    }

    public ProjectSummaryBean setActualTxCount(Integer actualTxCount) {
        this.actualTxCount = actualTxCount;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public ProjectSummaryBean setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public ProjectSummaryBean setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}