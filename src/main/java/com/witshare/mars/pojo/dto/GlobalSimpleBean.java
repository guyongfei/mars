package com.witshare.mars.pojo.dto;


public class GlobalSimpleBean {

    public static final String idListKey = "showList";
    public static final String typeKey = "showType";
    public static final String maxNum = "maxNum";

    private Long id;

    private String title;

    private Integer indexShow;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIndexShow() {
        return indexShow;
    }

    public void setIndexShow(Integer indexShow) {
        this.indexShow = indexShow;
    }
}
