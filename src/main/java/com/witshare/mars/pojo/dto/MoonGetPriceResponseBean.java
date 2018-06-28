package com.witshare.mars.pojo.dto;

import java.math.BigDecimal;

/**
 * Created by user on 2018/6/27.
 */
public class MoonGetPriceResponseBean extends MoonResponseBean {

    private Result result;

    public static class Result {
        private BigDecimal gasPrice;
        private String gasPriceGWei;
        private BigDecimal ethGasLimit;

        public BigDecimal getGasPrice() {
            return gasPrice;
        }

        public Result setGasPrice(BigDecimal gasPrice) {
            this.gasPrice = gasPrice;
            return this;
        }

        public String getGasPriceGWei() {
            return gasPriceGWei;
        }

        public Result setGasPriceGWei(String gasPriceGWei) {
            this.gasPriceGWei = gasPriceGWei;
            return this;
        }

        public BigDecimal getEthGasLimit() {
            return ethGasLimit;
        }

        public Result setEthGasLimit(BigDecimal ethGasLimit) {
            this.ethGasLimit = ethGasLimit;
            return this;
        }
    }

    public Result getResult() {
        return result;
    }

    public MoonGetPriceResponseBean setResult(Result result) {
        this.result = result;
        return this;
    }
}
