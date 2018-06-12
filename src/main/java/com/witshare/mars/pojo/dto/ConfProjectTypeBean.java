package com.witshare.mars.pojo.dto;

import java.util.List;

public class ConfProjectTypeBean {

    public final static String ID = "id";
    public final static String PID = "pId";
    public final static String PROJECT_TYPE_ZH = "projectTypeZh";
    public final static String PROJECT_TYPE_EN = "projectTypeEn";


    private Long id;
    private Long pId;
    private String projectTypeZh;
    private String projectTypeEn;
    private List<ConfProjectTypeBean> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getProjectTypeZh() {
        return projectTypeZh;
    }

    public void setProjectTypeZh(String projectTypeZh) {
        this.projectTypeZh = projectTypeZh;
    }

    public String getProjectTypeEn() {
        return projectTypeEn;
    }

    public void setProjectTypeEn(String projectTypeEn) {
        this.projectTypeEn = projectTypeEn;
    }

    public List<ConfProjectTypeBean> getChildren() {
        return children;
    }

    public void setChildren(List<ConfProjectTypeBean> children) {
        this.children = children;
    }
}