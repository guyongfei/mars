package com.witshare.mars.job.coindata;

/**
 * token信息类
 */
public class CoinData {
    private String id;
    private String name;
    private String otherName;
    //流通市值
    private String marketCapU;
    private String marketCapC;
    private String marketCapT;
    //价格
    private String priceU;
    private String priceC;
    private String priceT;
    //流通数量
    private String marketNum;
    //成交额
    private String volumeU;
    private String volumeC;
    private String volumeT;
    //涨幅
    private String change;

    private String content;

    public CoinData() {
    }

    public CoinData(String id, String name, String otherName, String marketCapC, String priceU, String priceC, String priceT, String marketNum, String volumeU, String volumeC, String volumeT, String change, String content) {
        this.id = id;
        this.name = name;
        this.otherName = otherName;
        this.marketCapC = marketCapC;
        this.priceU = priceU;
        this.priceC = priceC;
        this.priceT = priceT;
        this.marketNum = marketNum;
        this.volumeU = volumeU;
        this.volumeC = volumeC;
        this.volumeT = volumeT;
        this.change = change;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMarketCapU() {
        return marketCapU;
    }

    public void setMarketCapU(String marketCapU) {
        this.marketCapU = marketCapU;
    }

    public String getMarketCapC() {
        return marketCapC;
    }

    public void setMarketCapC(String marketCapC) {
        this.marketCapC = marketCapC;
    }

    public String getMarketCapT() {
        return marketCapT;
    }

    public void setMarketCapT(String marketCapT) {
        this.marketCapT = marketCapT;
    }

    public String getPriceU() {
        return priceU;
    }

    public void setPriceU(String priceU) {
        this.priceU = priceU;
    }

    public String getPriceC() {
        return priceC;
    }

    public void setPriceC(String priceC) {
        this.priceC = priceC;
    }

    public String getPriceT() {
        return priceT;
    }

    public void setPriceT(String priceT) {
        this.priceT = priceT;
    }

    public String getMarketNum() {
        return marketNum;
    }

    public void setMarketNum(String marketNum) {
        this.marketNum = marketNum;
    }

    public String getVolumeU() {
        return volumeU;
    }

    public void setVolumeU(String volumeU) {
        this.volumeU = volumeU;
    }

    public String getVolumeC() {
        return volumeC;
    }

    public void setVolumeC(String volumeC) {
        this.volumeC = volumeC;
    }

    public String getVolumeT() {
        return volumeT;
    }

    public void setVolumeT(String volumeT) {
        this.volumeT = volumeT;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
