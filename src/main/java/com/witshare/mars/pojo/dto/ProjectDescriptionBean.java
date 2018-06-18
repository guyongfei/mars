package com.witshare.mars.pojo.dto;

import java.sql.Timestamp;

public class ProjectDescriptionBean {

    private Long id;


    private String projectGid;

    private String projectName;

    private String projectInstruction;

    private String whitePaperLink;

    private Timestamp createTime;

    private Timestamp updateTime;

    private String projectContent;

    public Long getId() {
        return id;
    }

    public ProjectDescriptionBean setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public ProjectDescriptionBean setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public String getProjectName() {
        return projectName;
    }

    public ProjectDescriptionBean setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public String getProjectInstruction() {
        return projectInstruction;
    }

    public ProjectDescriptionBean setProjectInstruction(String projectInstruction) {
        this.projectInstruction = projectInstruction;
        return this;
    }

    public String getWhitePaperLink() {
        return whitePaperLink;
    }

    public ProjectDescriptionBean setWhitePaperLink(String whitePaperLink) {
        this.whitePaperLink = whitePaperLink;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public ProjectDescriptionBean setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public ProjectDescriptionBean setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getProjectContent() {
        return projectContent;
    }

    public ProjectDescriptionBean setProjectContent(String projectContent) {
        this.projectContent = projectContent;
        return this;
    }
}