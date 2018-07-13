package com.witshare.mars.pojo.vo;

import com.witshare.mars.pojo.dto.MoonGetPriceResponseBean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserTxInfoVo {


    private String userGid;

    private String projectGid;

    private String payEthAddress;

    private String getTokenAddress;

    private Integer txCount;

    private Boolean txCountLimit;

    private Integer projectStatus;

    private BigDecimal priceRate;

    private String projectToken;

    private String platformAddress;

    private BigDecimal minPurchaseAmount;

    private BigDecimal maxPurchaseAmount;

    private Timestamp endTime;

    private MoonGetPriceResponseBean.Result gasPrice;

    private UserTxInfoVo() {
    }

    public static UserTxInfoVo newInstance() {
        return new UserTxInfoVo();
    }

    public String getUserGid() {
        return userGid;
    }

    public UserTxInfoVo setUserGid(String userGid) {
        this.userGid = userGid;
        return this;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public UserTxInfoVo setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public String getPayEthAddress() {
        return payEthAddress;
    }

    public UserTxInfoVo setPayEthAddress(String payEthAddress) {
        this.payEthAddress = payEthAddress;
        return this;
    }

    public String getGetTokenAddress() {
        return getTokenAddress;
    }

    public UserTxInfoVo setGetTokenAddress(String getTokenAddress) {
        this.getTokenAddress = getTokenAddress;
        return this;
    }

    public Boolean getTxCountLimit() {
        return txCountLimit;
    }

    public UserTxInfoVo setTxCountLimit(Boolean txCountLimit) {
        this.txCountLimit = txCountLimit;
        return this;
    }

    public BigDecimal getPriceRate() {
        return priceRate;
    }

    public UserTxInfoVo setPriceRate(BigDecimal priceRate) {
        this.priceRate = priceRate;
        return this;
    }

    public String getProjectToken() {
        return projectToken;
    }

    public UserTxInfoVo setProjectToken(String projectToken) {
        this.projectToken = projectToken;
        return this;
    }

    public String getPlatformAddress() {
        return platformAddress;
    }

    public UserTxInfoVo setPlatformAddress(String platformAddress) {
        this.platformAddress = platformAddress;
        return this;
    }

    public BigDecimal getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public UserTxInfoVo setMinPurchaseAmount(BigDecimal minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
        return this;
    }

    public MoonGetPriceResponseBean.Result getGasPrice() {
        return gasPrice;
    }

    public UserTxInfoVo setGasPrice(MoonGetPriceResponseBean.Result gasPrice) {
        this.gasPrice = gasPrice;
        return this;
    }

    public Integer getTxCount() {
        return txCount;
    }

    public UserTxInfoVo setTxCount(Integer txCount) {
        this.txCount = txCount;
        return this;
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public UserTxInfoVo setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
        return this;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public UserTxInfoVo setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }

    public BigDecimal getMaxPurchaseAmount() {
        return maxPurchaseAmount;
    }

    public UserTxInfoVo setMaxPurchaseAmount(BigDecimal maxPurchaseAmount) {
        this.maxPurchaseAmount = maxPurchaseAmount;
        return this;
    }
}