package com.witshare.mars.pojo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RecordPlatformTxBean extends BasePageBean {
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


    public RecordPlatformTxBean() {
    }

    public static RecordPlatformTxBean newInstance() {
        return new RecordPlatformTxBean();
    }

    public Long getId() {
        return id;
    }

    public RecordPlatformTxBean setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public RecordPlatformTxBean setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public String getTxHash() {
        return txHash;
    }

    public RecordPlatformTxBean setTxHash(String txHash) {
        this.txHash = txHash;
        return this;
    }

    public Integer getTxType() {
        return txType;
    }

    public RecordPlatformTxBean setTxType(Integer txType) {
        this.txType = txType;
        return this;
    }

    public String getFromName() {
        return fromName;
    }

    public RecordPlatformTxBean setFromName(String fromName) {
        this.fromName = fromName;
        return this;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public RecordPlatformTxBean setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
        return this;
    }

    public String getToName() {
        return toName;
    }

    public RecordPlatformTxBean setToName(String toName) {
        this.toName = toName;
        return this;
    }

    public String getToAddress() {
        return toAddress;
    }

    public RecordPlatformTxBean setToAddress(String toAddress) {
        this.toAddress = toAddress;
        return this;
    }

    public String getTxTokenType() {
        return txTokenType;
    }

    public RecordPlatformTxBean setTxTokenType(String txTokenType) {
        this.txTokenType = txTokenType;
        return this;
    }

    public BigDecimal getTxAmount() {
        return txAmount;
    }

    public RecordPlatformTxBean setTxAmount(BigDecimal txAmount) {
        this.txAmount = txAmount;
        return this;
    }

    public BigDecimal getEthFee() {
        return ethFee;
    }

    public RecordPlatformTxBean setEthFee(BigDecimal ethFee) {
        this.ethFee = ethFee;
        return this;
    }

    public Integer getTxStatus() {
        return txStatus;
    }

    public RecordPlatformTxBean setTxStatus(Integer txStatus) {
        this.txStatus = txStatus;
        return this;
    }

    public Timestamp getTxVerificationTime() {
        return txVerificationTime;
    }

    public RecordPlatformTxBean setTxVerificationTime(Timestamp txVerificationTime) {
        this.txVerificationTime = txVerificationTime;
        return this;
    }

    public Timestamp getTxTime() {
        return txTime;
    }

    public RecordPlatformTxBean setTxTime(Timestamp txTime) {
        this.txTime = txTime;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public RecordPlatformTxBean setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public RecordPlatformTxBean setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getProjectToken() {
        return projectToken;
    }

    public RecordPlatformTxBean setProjectToken(String projectToken) {
        this.projectToken = projectToken;
        return this;
    }
}