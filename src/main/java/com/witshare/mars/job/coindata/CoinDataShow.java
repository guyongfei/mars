package com.witshare.mars.job.coindata;

/**
 * Token 过程类
 */
public class CoinDataShow {

    public static final String KEY = "key";
    public static final String MARKET_CAP_EN = "marketCapEn";
    public static final String MARKET_CAP_ZH = "marketCapZh";
    public static final String PRICE_EN = "priceEn";
    public static final String PRICE_ZH = "priceZh";
    public static final String MARKET_NUM_EN = "marketNumEn";
    public static final String MARKET_NUM_ZH = "marketNumZh";
    public static final String VOLUME_EN = "volumeEn";
    public static final String VOLUME_ZH = "volumeZh";
    public static final String CHANGE = "change";

    private String name;
    private String otherName;
    //流通市值
    //单位 M
    private String marketCapEn;
    //单位 亿
    private String marketCapZh;
    //价格
    private String priceEn;
    private String priceZh;
    //流通数量
    //单位 K
    private String marketNumEn;
    //单位 万
    private String marketNumZh;
    //成交额
    //单位 K
    private String volumeEn;
    //单位 万
    private String volumeZh;
    //涨幅
    private String change;


    public CoinDataShow() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getMarketCapEn() {
        return marketCapEn;
    }

    public void setMarketCapEn(String marketCapEn) {
        this.marketCapEn = marketCapEn;
    }

    public String getMarketCapZh() {
        return marketCapZh;
    }

    public void setMarketCapZh(String marketCapZh) {
        this.marketCapZh = marketCapZh;
    }

    public String getPriceEn() {
        return priceEn;
    }

    public void setPriceEn(String priceEn) {
        this.priceEn = priceEn;
    }

    public String getPriceZh() {
        return priceZh;
    }

    public void setPriceZh(String priceZh) {
        this.priceZh = priceZh;
    }

    public String getMarketNumEn() {
        return marketNumEn;
    }

    public void setMarketNumEn(String marketNumEn) {
        this.marketNumEn = marketNumEn;
    }

    public String getMarketNumZh() {
        return marketNumZh;
    }

    public void setMarketNumZh(String marketNumZh) {
        this.marketNumZh = marketNumZh;
    }

    public String getVolumeEn() {
        return volumeEn;
    }

    public void setVolumeEn(String volumeEn) {
        this.volumeEn = volumeEn;
    }

    public String getVolumeZh() {
        return volumeZh;
    }

    public void setVolumeZh(String volumeZh) {
        this.volumeZh = volumeZh;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }
}
