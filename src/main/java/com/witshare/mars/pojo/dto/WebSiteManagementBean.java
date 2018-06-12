package com.witshare.mars.pojo.dto;


public class WebSiteManagementBean {
    private String websiteType;
    private String linkUrl;

    public WebSiteManagementBean() {
    }

    public static WebSiteManagementBean newInstance() {
        return new WebSiteManagementBean();
    }

    public String getWebsiteType() {
        return websiteType;
    }

    public WebSiteManagementBean setWebsiteType(String websiteType) {
        this.websiteType = websiteType;
        return this;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public WebSiteManagementBean setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }
}
