package com.witshare.mars.pojo.vo;

import com.witshare.mars.pojo.dto.MoonGetPriceResponseBean;

import java.math.BigDecimal;

public class UserTxInfoVo {


    private String userGid;

    private String projectGid;

    private String payEthAddress;

    private String getTokenAddress;

    private Boolean txCountLimit;

    private BigDecimal priceRate;

    private String projectToken;

    private String platformAddress;

    private BigDecimal minPurchaseAmount;

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
}