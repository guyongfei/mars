package com.witshare.mars.pojo.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RecordUserTx {
    private Long id;

    private String userGid;

    private String userEmail;

    private String projectGid;

    private String projectToken;

    private Integer payCoinType;

    private String payTx;

    private Long payTxId;

    private BigDecimal payAmount;

    private BigDecimal priceRate;

    private BigDecimal hopeGetAmount;

    private BigDecimal shouldGetAmount;

    private BigDecimal actualPayAmount;

    private BigDecimal actualGetAmount;

    private Integer userTxStatus;

    private String platformTx;

    private BigDecimal ethFee;

    private Integer platformTxStatus;

    private Timestamp distributionTime;

    private Timestamp createTime;

    private Timestamp updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserGid() {
        return userGid;
    }

    public void setUserGid(String userGid) {
        this.userGid = userGid == null ? null : userGid.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
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

    public Integer getPayCoinType() {
        return payCoinType;
    }

    public void setPayCoinType(Integer payCoinType) {
        this.payCoinType = payCoinType;
    }

    public String getPayTx() {
        return payTx;
    }

    public void setPayTx(String payTx) {
        this.payTx = payTx == null ? null : payTx.trim();
    }

    public Long getPayTxId() {
        return payTxId;
    }

    public void setPayTxId(Long payTxId) {
        this.payTxId = payTxId;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(BigDecimal priceRate) {
        this.priceRate = priceRate;
    }

    public BigDecimal getHopeGetAmount() {
        return hopeGetAmount;
    }

    public void setHopeGetAmount(BigDecimal hopeGetAmount) {
        this.hopeGetAmount = hopeGetAmount;
    }

    public BigDecimal getShouldGetAmount() {
        return shouldGetAmount;
    }

    public void setShouldGetAmount(BigDecimal shouldGetAmount) {
        this.shouldGetAmount = shouldGetAmount;
    }

    public BigDecimal getActualPayAmount() {
        return actualPayAmount;
    }

    public void setActualPayAmount(BigDecimal actualPayAmount) {
        this.actualPayAmount = actualPayAmount;
    }

    public BigDecimal getActualGetAmount() {
        return actualGetAmount;
    }

    public void setActualGetAmount(BigDecimal actualGetAmount) {
        this.actualGetAmount = actualGetAmount;
    }

    public Integer getUserTxStatus() {
        return userTxStatus;
    }

    public void setUserTxStatus(Integer userTxStatus) {
        this.userTxStatus = userTxStatus;
    }

    public String getPlatformTx() {
        return platformTx;
    }

    public void setPlatformTx(String platformTx) {
        this.platformTx = platformTx == null ? null : platformTx.trim();
    }

    public BigDecimal getEthFee() {
        return ethFee;
    }

    public void setEthFee(BigDecimal ethFee) {
        this.ethFee = ethFee;
    }

    public Integer getPlatformTxStatus() {
        return platformTxStatus;
    }

    public void setPlatformTxStatus(Integer platformTxStatus) {
        this.platformTxStatus = platformTxStatus;
    }

    public Timestamp getDistributionTime() {
        return distributionTime;
    }

    public void setDistributionTime(Timestamp distributionTime) {
        this.distributionTime = distributionTime;
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