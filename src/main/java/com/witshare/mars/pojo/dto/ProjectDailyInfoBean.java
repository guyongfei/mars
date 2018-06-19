package com.witshare.mars.pojo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class ProjectDailyInfoBean {

    private Long id;


    private String projectGid;


    private String projectToken;


    private BigDecimal priceRate;

    private BigDecimal subscriptionAmount;

    private BigDecimal actualSubscriptionAmount;

    private BigDecimal distributeAmount;

    private BigDecimal actualDistributeAmount;

    private BigDecimal txUserAmount;


    private BigDecimal actualTxUserAmount;

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

    public BigDecimal getPriceRate() {
        return priceRate;
    }

    public ProjectDailyInfoBean setPriceRate(BigDecimal priceRate) {
        this.priceRate = priceRate;
        return this;
    }

    public BigDecimal getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public ProjectDailyInfoBean setSubscriptionAmount(BigDecimal subscriptionAmount) {
        this.subscriptionAmount = subscriptionAmount;
        return this;
    }

    public BigDecimal getActualSubscriptionAmount() {
        return actualSubscriptionAmount;
    }

    public ProjectDailyInfoBean setActualSubscriptionAmount(BigDecimal actualSubscriptionAmount) {
        this.actualSubscriptionAmount = actualSubscriptionAmount;
        return this;
    }

    public BigDecimal getDistributeAmount() {
        return distributeAmount;
    }

    public ProjectDailyInfoBean setDistributeAmount(BigDecimal distributeAmount) {
        this.distributeAmount = distributeAmount;
        return this;
    }

    public BigDecimal getActualDistributeAmount() {
        return actualDistributeAmount;
    }

    public ProjectDailyInfoBean setActualDistributeAmount(BigDecimal actualDistributeAmount) {
        this.actualDistributeAmount = actualDistributeAmount;
        return this;
    }

    public BigDecimal getTxUserAmount() {
        return txUserAmount;
    }

    public ProjectDailyInfoBean setTxUserAmount(BigDecimal txUserAmount) {
        this.txUserAmount = txUserAmount;
        return this;
    }

    public BigDecimal getActualTxUserAmount() {
        return actualTxUserAmount;
    }

    public ProjectDailyInfoBean setActualTxUserAmount(BigDecimal actualTxUserAmount) {
        this.actualTxUserAmount = actualTxUserAmount;
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