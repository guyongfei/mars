package com.witshare.mars.pojo.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RecordUserTx {
    private Long id;

    private String userGid;

    private String userEmail;

    private String projectGid;

    private String projectToken;

    private String channel;

    private Integer payCoinType;

    private String payTx;

    private BigDecimal payAmount;

    private BigDecimal priceRate;

    private BigDecimal hopeGetAmount;

    private BigDecimal shouldGetAmount;

    private BigDecimal actualPayAmount;

    private BigDecimal actualGetAmount;

    private String actualSendingAddress;

    private String actualReceivingAddress;

    private Timestamp actualTxTime;

    private Timestamp txVerificationTime;

    private Integer userTxStatus;

    private String platformTx;

    private BigDecimal ethFee;

    private Integer platformTxStatus;

    private Timestamp distributionTime;

    private Timestamp createTime;

    private Timestamp updateTime;

    private String distributionBatchId;

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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
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

    public String getActualSendingAddress() {
        return actualSendingAddress;
    }

    public void setActualSendingAddress(String actualSendingAddress) {
        this.actualSendingAddress = actualSendingAddress == null ? null : actualSendingAddress.trim();
    }

    public String getActualReceivingAddress() {
        return actualReceivingAddress;
    }

    public void setActualReceivingAddress(String actualReceivingAddress) {
        this.actualReceivingAddress = actualReceivingAddress == null ? null : actualReceivingAddress.trim();
    }

    public Timestamp getActualTxTime() {
        return actualTxTime;
    }

    public void setActualTxTime(Timestamp actualTxTime) {
        this.actualTxTime = actualTxTime;
    }

    public Timestamp getTxVerificationTime() {
        return txVerificationTime;
    }

    public void setTxVerificationTime(Timestamp txVerificationTime) {
        this.txVerificationTime = txVerificationTime;
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

    public String getDistributionBatchId() {
        return distributionBatchId;
    }

    public void setDistributionBatchId(String distributionBatchId) {
        this.distributionBatchId = distributionBatchId == null ? null : distributionBatchId.trim();
    }
}