package com.witshare.mars.pojo.vo;

public class ExchangeWebsiteDetailVo {


    private Long id;
    private String linkType;
    private String linkUrl;


    public ExchangeWebsiteDetailVo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}