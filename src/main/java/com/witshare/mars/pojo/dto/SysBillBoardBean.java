package com.witshare.mars.pojo.dto;

import java.util.Collection;

public class SysBillBoardBean {

    public static String ID = "id";
    public static String TITLE_ZH = "titleZh";
    public static String TITLE_EN = "titleEn";
    public static String CONTENT_ZH = "contentZh";
    public static String CONTENT_EN = "contentEn";
    public static String PICTURE_URL = "pictureUrl";
    public static String PICTURE = "picture";
    public static String LINK_URL = "linkUrl";

    public static String BILL_BOARD_IDS = "billBoardIds";
    public static String BILL_BOARD_NUM = "billBoardNum";

    private Integer billBoardNum;
    private Collection<Long> ids;
    private Integer pageNum;
    private Integer pageSize;
    private Long id;

    private String titleZh;

    private String titleEn;

    private String contentZh;

    private String contentEn;

    private String pictureUrl;

    private String linkUrl;

    public SysBillBoardBean() {
    }

    public SysBillBoardBean(String titleZh, String titleEn, String contentZh, String contentEn, String pictureUrl, String linkUrl) {
        this.titleZh = titleZh;
        this.titleEn = titleEn;
        this.contentZh = contentZh;
        this.contentEn = contentEn;
        this.pictureUrl = pictureUrl;
        this.linkUrl = linkUrl;
    }

    public Integer getBillBoardNum() {
        return billBoardNum;
    }

    public void setBillBoardNum(Integer billBoardNum) {
        this.billBoardNum = billBoardNum;
    }

    public Collection<Long> getIds() {
        return ids;
    }

    public void setIds(Collection<Long> ids) {
        this.ids = ids;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleZh() {
        return titleZh;
    }

    public void setTitleZh(String titleZh) {
        this.titleZh = titleZh;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getContentZh() {
        return contentZh;
    }

    public void setContentZh(String contentZh) {
        this.contentZh = contentZh;
    }

    public String getContentEn() {
        return contentEn;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}