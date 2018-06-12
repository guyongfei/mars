package com.witshare.mars.pojo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class ProjectPageBean {

    private Long id;

    private String projectName;

    private String projectToken;

    private String projectInstruction;

    private String projectLogoLink;

    private String projectImgLink;

    private Long projectTypeId;

    private String projectGrade;

    private String projectType;

    private String subProjectType;

    private BigDecimal projectGradeScore;

    private Integer projectIco;

    private Boolean followOrNot;

    private Timestamp createTime;

    public Boolean getFollowOrNot() {
        return followOrNot;
    }

    public void setFollowOrNot(Boolean followOrNot) {
        this.followOrNot = followOrNot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getProjectInstruction() {
        return projectInstruction;
    }

    public void setProjectInstruction(String projectInstruction) {
        this.projectInstruction = projectInstruction;
    }

    public String getProjectLogoLink() {
        return projectLogoLink;
    }

    public void setProjectLogoLink(String projectLogoLink) {
        this.projectLogoLink = projectLogoLink;
    }

    public String getProjectImgLink() {
        return projectImgLink;
    }

    public void setProjectImgLink(String projectImgLink) {
        this.projectImgLink = projectImgLink;
    }

    public String getProjectGrade() {
        return projectGrade;
    }

    public void setProjectGrade(String projectGrade) {
        this.projectGrade = projectGrade;
    }


    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getProjectIco() {
        return projectIco;
    }

    public void setProjectIco(Integer projectIco) {
        this.projectIco = projectIco;
    }

    public BigDecimal getProjectGradeScore() {
        return projectGradeScore;
    }

    public void setProjectGradeScore(BigDecimal projectGradeScore) {
        this.projectGradeScore = projectGradeScore;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getSubProjectType() {
        return subProjectType;
    }

    public void setSubProjectType(String subProjectType) {
        this.subProjectType = subProjectType;
    }

    public Long getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Long projectTypeId) {
        this.projectTypeId = projectTypeId;
    }
}
