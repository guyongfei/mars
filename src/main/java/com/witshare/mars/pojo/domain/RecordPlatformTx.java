package com.witshare.mars.pojo.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RecordPlatformTx {
    private Long id;

    private String projectGid;

    private String projectToken;

    private String txHash;

    private Integer txType;

    private String fromName;

    private String fromAddress;

    private String toName;

    private String toAddress;

    private String txTokenType;

    private BigDecimal txAmount;

    private BigDecimal ethFee;

    private Integer txStatus;

    private Timestamp txVerificationTime;

    private Timestamp txTime;

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

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash == null ? null : txHash.trim();
    }

    public Integer getTxType() {
        return txType;
    }

    public void setTxType(Integer txType) {
        this.txType = txType;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName == null ? null : fromName.trim();
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress == null ? null : fromAddress.trim();
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName == null ? null : toName.trim();
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress == null ? null : toAddress.trim();
    }

    public String getTxTokenType() {
        return txTokenType;
    }

    public void setTxTokenType(String txTokenType) {
        this.txTokenType = txTokenType == null ? null : txTokenType.trim();
    }

    public BigDecimal getTxAmount() {
        return txAmount;
    }

    public void setTxAmount(BigDecimal txAmount) {
        this.txAmount = txAmount;
    }

    public BigDecimal getEthFee() {
        return ethFee;
    }

    public void setEthFee(BigDecimal ethFee) {
        this.ethFee = ethFee;
    }

    public Integer getTxStatus() {
        return txStatus;
    }

    public void setTxStatus(Integer txStatus) {
        this.txStatus = txStatus;
    }

    public Timestamp getTxVerificationTime() {
        return txVerificationTime;
    }

    public void setTxVerificationTime(Timestamp txVerificationTime) {
        this.txVerificationTime = txVerificationTime;
    }

    public Timestamp getTxTime() {
        return txTime;
    }

    public void setTxTime(Timestamp txTime) {
        this.txTime = txTime;
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