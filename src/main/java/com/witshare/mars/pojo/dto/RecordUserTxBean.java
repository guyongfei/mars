package com.witshare.mars.pojo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class RecordUserTxBean extends BasePageBean {

    public static final String PROJECT_GID = "projectGid";
    public static final String PRICE_RATE = "priceRate";
    public static final String PAY_AMOUNT = "payAmount";
    public static final String PAY_TX = "payTx";
    public static final String HOPE_GET_AMOUNT = "hopeGetAmount";
    public static final String PAY_COIN_TYPE = "payCoinType";


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
    private LocalDate localDate;






    private RecordUserTxBean() {
    }

    public static RecordUserTxBean newInstance() {
        return new RecordUserTxBean();
    }

    public RecordUserTxBean setTime(Timestamp time) {
        this.updateTime = time;
        this.createTime = time;
        this.distributionTime = time;
        return this;
    }

    public Long getId() {
        return id;
    }

    public RecordUserTxBean setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserGid() {
        return userGid;
    }

    public RecordUserTxBean setUserGid(String userGid) {
        this.userGid = userGid;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public RecordUserTxBean setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public RecordUserTxBean setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public String getProjectToken() {
        return projectToken;
    }

    public RecordUserTxBean setProjectToken(String projectToken) {
        this.projectToken = projectToken;
        return this;
    }

    public int getPayCoinType() {
        return payCoinType;
    }

    public RecordUserTxBean setPayCoinType(int payCoinType) {
        this.payCoinType = payCoinType;
        return this;
    }

    public String getPayTx() {
        return payTx;
    }

    public RecordUserTxBean setPayTx(String payTx) {
        this.payTx = payTx;
        return this;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public RecordUserTxBean setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
        return this;
    }

    public BigDecimal getPriceRate() {
        return priceRate;
    }

    public RecordUserTxBean setPriceRate(BigDecimal priceRate) {
        this.priceRate = priceRate;
        return this;
    }

    public BigDecimal getHopeGetAmount() {
        return hopeGetAmount;
    }

    public RecordUserTxBean setHopeGetAmount(BigDecimal hopeGetAmount) {
        this.hopeGetAmount = hopeGetAmount;
        return this;
    }

    public BigDecimal getActualPayAmount() {
        return actualPayAmount;
    }

    public RecordUserTxBean setActualPayAmount(BigDecimal actualPayAmount) {
        this.actualPayAmount = actualPayAmount;
        return this;
    }

    public BigDecimal getActualGetAmount() {
        return actualGetAmount;
    }

    public RecordUserTxBean setActualGetAmount(BigDecimal actualGetAmount) {
        this.actualGetAmount = actualGetAmount;
        return this;
    }

    public int getUserTxStatus() {
        return userTxStatus;
    }

    public RecordUserTxBean setUserTxStatus(int userTxStatus) {
        this.userTxStatus = userTxStatus;
        return this;
    }

    public String getPlatformTx() {
        return platformTx;
    }

    public RecordUserTxBean setPlatformTx(String platformTx) {
        this.platformTx = platformTx;
        return this;
    }

    public BigDecimal getEthFee() {
        return ethFee;
    }

    public RecordUserTxBean setEthFee(BigDecimal ethFee) {
        this.ethFee = ethFee;
        return this;
    }

    public int getPlatformTxStatus() {
        return platformTxStatus;
    }

    public RecordUserTxBean setPlatformTxStatus(int platformTxStatus) {
        this.platformTxStatus = platformTxStatus;
        return this;
    }

    public Timestamp getDistributionTime() {
        return distributionTime;
    }

    public RecordUserTxBean setDistributionTime(Timestamp distributionTime) {
        this.distributionTime = distributionTime;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public RecordUserTxBean setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        this.localDate = createTime.toLocalDateTime().toLocalDate();
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public RecordUserTxBean setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public BigDecimal getShouldGetAmount() {
        return shouldGetAmount;
    }

    public RecordUserTxBean setShouldGetAmount(BigDecimal shouldGetAmount) {
        this.shouldGetAmount = shouldGetAmount;
        return this;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public RecordUserTxBean setPayCoinType(Integer payCoinType) {
        this.payCoinType = payCoinType;
        return this;
    }

    public Long getPayTxId() {
        return payTxId;
    }

    public RecordUserTxBean setPayTxId(Long payTxId) {
        this.payTxId = payTxId;
        return this;
    }

    public RecordUserTxBean setUserTxStatus(Integer userTxStatus) {
        this.userTxStatus = userTxStatus;
        return this;
    }

    public RecordUserTxBean setPlatformTxStatus(Integer platformTxStatus) {
        this.platformTxStatus = platformTxStatus;
        return this;
    }

    public RecordUserTxBean setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
        return this;
    }
}