package com.witshare.mars.pojo.vo;

import java.sql.Timestamp;

public class SysProjectListVo {


    private String projectToken;
    private String projectGid;
    private Integer projectStatus;
    private Integer isAvailable;
    private Timestamp startTime;
    private Timestamp endTime;

    public SysProjectListVo() {
    }

    public SysProjectListVo newInstance() {
        return new SysProjectListVo();
    }


    public String getProjectToken() {
        return projectToken;
    }

    public SysProjectListVo setProjectToken(String projectToken) {
        this.projectToken = projectToken;
        return this;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public SysProjectListVo setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public SysProjectListVo setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
        return this;
    }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public SysProjectListVo setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
        return this;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public SysProjectListVo setStartTime(Timestamp startTime) {
        this.startTime = startTime;
        return this;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public SysProjectListVo setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }
}