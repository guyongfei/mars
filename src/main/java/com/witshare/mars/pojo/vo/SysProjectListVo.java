package com.witshare.mars.pojo.vo;

public class SysProjectListVo {


    private String projectNameZh;
    private String projectNameEn;
    private Boolean projectStatus;

    private Long id;
    private String token;

    public String getProjectNameZh() {
        return projectNameZh;
    }

    public void setProjectNameZh(String projectNameZh) {
        this.projectNameZh = projectNameZh;
    }

    public String getProjectNameEn() {
        return projectNameEn;
    }

    public void setProjectNameEn(String projectNameEn) {
        this.projectNameEn = projectNameEn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Boolean projectStatus) {
        this.projectStatus = projectStatus;
    }
}