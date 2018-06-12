package com.witshare.mars.pojo.dto;

import java.math.BigDecimal;
import java.util.List;


public class ProjectReqBean {

    private Integer pageNum;

    private Integer pageSize;

    private Boolean starProject;

    private String projectName;

    private Integer projectStatus;

    private String projectToken;

    private Integer projectIco;

    private BigDecimal projectGradeScore;

    private Integer projectTypeId;

    private String orderCondition;

    private Integer ascOrdesc;

    private String tableName;

    private List<Long> projectTypeList;


    public Integer getAscOrdesc() {
        return ascOrdesc;
    }

    public void setAscOrdesc(Integer ascOrdesc) {
        this.ascOrdesc = ascOrdesc;
    }

    public String getOrderCondition() {
        return orderCondition;
    }

    public void setOrderCondition(String orderCondition) {
        this.orderCondition = orderCondition;
    }

    public Boolean getStarProject() {
        return starProject;
    }

    public void setStarProject(Boolean starProject) {
        this.starProject = starProject;
    }

    public BigDecimal getProjectGradeScore() {
        return projectGradeScore;
    }

    public void setProjectGradeScore(BigDecimal projectGradeScore) {
        this.projectGradeScore = projectGradeScore;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectToken() {
        return projectToken;
    }

    public void setProjectToken(String projectToken) {
        this.projectToken = projectToken;
    }

    public Integer getProjectIco() {
        return projectIco;
    }

    public void setProjectIco(Integer projectIco) {
        this.projectIco = projectIco;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public List<Long> getProjectTypeList() {
        return projectTypeList;
    }

    public void setProjectTypeList(List<Long> projectTypeList) {
        this.projectTypeList = projectTypeList;
    }
}
