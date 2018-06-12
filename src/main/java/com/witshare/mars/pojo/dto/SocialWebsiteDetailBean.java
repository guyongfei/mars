package com.witshare.mars.pojo.dto;

public class SocialWebsiteDetailBean {


    public static final String PICTURE = "picture";
    public static final String LINK_TYPE = "linkType";
    public static final String ID = "id";

    private Long id;
    private String linkType;
    private String pictureUrl;
    private String picture;

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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}