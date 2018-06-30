package com.witshare.mars.pojo.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RecordUserTxListVo {


    private String userGid;

    private String projectGid;

    private String projectToken;

    private int payCoinType;

    private String payTx;

    private Long payTxId;

    private BigDecimal payAmount;

    private BigDecimal priceRate;

    private BigDecimal hopeGetAmount;

    private BigDecimal shouldGetAmount;

    private BigDecimal actualPayAmount;

    private BigDecimal actualGetAmount;

    private Integer userTxStatus;

    private Timestamp createTime;

    private String distributionBatchId;


    private RecordUserTxListVo() {

    }

    public static RecordUserTxListVo newInstance() {
        return new RecordUserTxListVo();
    }

    public String getUserGid() {
        return userGid;
    }

    public RecordUserTxListVo setUserGid(String userGid) {
        this.userGid = userGid;
        return this;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public RecordUserTxListVo setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public String getProjectToken() {
        return projectToken;
    }

    public RecordUserTxListVo setProjectToken(String projectToken) {
        this.projectToken = projectToken;
        return this;
    }

    public int getPayCoinType() {
        return payCoinType;
    }

    public RecordUserTxListVo setPayCoinType(int payCoinType) {
        this.payCoinType = payCoinType;
        return this;
    }

    public String getPayTx() {
        return payTx;
    }

    public RecordUserTxListVo setPayTx(String payTx) {
        this.payTx = payTx;
        return this;
    }

    public Long getPayTxId() {
        return payTxId;
    }

    public RecordUserTxListVo setPayTxId(Long payTxId) {
        this.payTxId = payTxId;
        return this;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public RecordUserTxListVo setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
        return this;
    }

    public BigDecimal getPriceRate() {
        return priceRate;
    }

    public RecordUserTxListVo setPriceRate(BigDecimal priceRate) {
        this.priceRate = priceRate;
        return this;
    }

    public BigDecimal getHopeGetAmount() {
        return hopeGetAmount;
    }

    public RecordUserTxListVo setHopeGetAmount(BigDecimal hopeGetAmount) {
        this.hopeGetAmount = hopeGetAmount;
        return this;
    }

    public BigDecimal getShouldGetAmount() {
        return shouldGetAmount;
    }

    public RecordUserTxListVo setShouldGetAmount(BigDecimal shouldGetAmount) {
        this.shouldGetAmount = shouldGetAmount;
        return this;
    }

    public BigDecimal getActualPayAmount() {
        return actualPayAmount;
    }

    public RecordUserTxListVo setActualPayAmount(BigDecimal actualPayAmount) {
        this.actualPayAmount = actualPayAmount;
        return this;
    }

    public BigDecimal getActualGetAmount() {
        return actualGetAmount;
    }

    public RecordUserTxListVo setActualGetAmount(BigDecimal actualGetAmount) {
        this.actualGetAmount = actualGetAmount;
        return this;
    }

    public Integer getUserTxStatus() {
        return userTxStatus;
    }

    public RecordUserTxListVo setUserTxStatus(Integer userTxStatus) {
        this.userTxStatus = userTxStatus;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public RecordUserTxListVo setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getDistributionBatchId() {
        return distributionBatchId;
    }

    public RecordUserTxListVo setDistributionBatchId(String distributionBatchId) {
        this.distributionBatchId = distributionBatchId;
        return this;
    }
}