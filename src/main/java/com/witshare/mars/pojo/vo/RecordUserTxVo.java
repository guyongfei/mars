package com.witshare.mars.pojo.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RecordUserTxVo {


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

    private String distributionBatchId;
    private RecordUserTxVo() {

    }

    public static RecordUserTxVo newInstance() {
        return new RecordUserTxVo();
    }

    public String getUserGid() {
        return userGid;
    }

    public RecordUserTxVo setUserGid(String userGid) {
        this.userGid = userGid;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public RecordUserTxVo setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public RecordUserTxVo setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public String getProjectToken() {
        return projectToken;
    }

    public RecordUserTxVo setProjectToken(String projectToken) {
        this.projectToken = projectToken;
        return this;
    }

    public Integer getPayCoinType() {
        return payCoinType;
    }

    public RecordUserTxVo setPayCoinType(Integer payCoinType) {
        this.payCoinType = payCoinType;
        return this;
    }

    public String getPayTx() {
        return payTx;
    }

    public RecordUserTxVo setPayTx(String payTx) {
        this.payTx = payTx;
        return this;
    }

    public Long getPayTxId() {
        return payTxId;
    }

    public RecordUserTxVo setPayTxId(Long payTxId) {
        this.payTxId = payTxId;
        return this;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public RecordUserTxVo setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
        return this;
    }

    public BigDecimal getPriceRate() {
        return priceRate;
    }

    public RecordUserTxVo setPriceRate(BigDecimal priceRate) {
        this.priceRate = priceRate;
        return this;
    }

    public BigDecimal getHopeGetAmount() {
        return hopeGetAmount;
    }

    public RecordUserTxVo setHopeGetAmount(BigDecimal hopeGetAmount) {
        this.hopeGetAmount = hopeGetAmount;
        return this;
    }

    public BigDecimal getShouldGetAmount() {
        return shouldGetAmount;
    }

    public RecordUserTxVo setShouldGetAmount(BigDecimal shouldGetAmount) {
        this.shouldGetAmount = shouldGetAmount;
        return this;
    }

    public BigDecimal getActualPayAmount() {
        return actualPayAmount;
    }

    public RecordUserTxVo setActualPayAmount(BigDecimal actualPayAmount) {
        this.actualPayAmount = actualPayAmount;
        return this;
    }

    public BigDecimal getActualGetAmount() {
        return actualGetAmount;
    }

    public RecordUserTxVo setActualGetAmount(BigDecimal actualGetAmount) {
        this.actualGetAmount = actualGetAmount;
        return this;
    }

    public Integer getUserTxStatus() {
        return userTxStatus;
    }

    public RecordUserTxVo setUserTxStatus(Integer userTxStatus) {
        this.userTxStatus = userTxStatus;
        return this;
    }

    public String getPlatformTx() {
        return platformTx;
    }

    public RecordUserTxVo setPlatformTx(String platformTx) {
        this.platformTx = platformTx;
        return this;
    }

    public BigDecimal getEthFee() {
        return ethFee;
    }

    public RecordUserTxVo setEthFee(BigDecimal ethFee) {
        this.ethFee = ethFee;
        return this;
    }

    public Integer getPlatformTxStatus() {
        return platformTxStatus;
    }

    public RecordUserTxVo setPlatformTxStatus(Integer platformTxStatus) {
        this.platformTxStatus = platformTxStatus;
        return this;
    }

    public Timestamp getDistributionTime() {
        return distributionTime;
    }

    public RecordUserTxVo setDistributionTime(Timestamp distributionTime) {
        this.distributionTime = distributionTime;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public RecordUserTxVo setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getDistributionBatchId() {
        return distributionBatchId;
    }

    public RecordUserTxVo setDistributionBatchId(String distributionBatchId) {
        this.distributionBatchId = distributionBatchId;
        return this;
    }
}