package com.witshare.mars.pojo.dto;

import java.sql.Timestamp;

/**
 * Created by user on 2018/4/25.
 */
public class SysCommentReplyBean {

    private String projectLike;
    private String userLike;
    private String contentLike;
    private Integer pageSize;
    private Integer pageNum;


    private Long id;
    private Integer commentLevel;

    private Long projectId;

    private String content;

    private String token;

    private Timestamp updateTime;

    public String getProjectLike() {
        return projectLike;
    }

    public void setProjectLike(String projectLike) {
        this.projectLike = projectLike;
    }

    public String getUserLike() {
        return userLike;
    }

    public void setUserLike(String userLike) {
        this.userLike = userLike;
    }

    public String getContentLike() {
        return contentLike;
    }

    public void setContentLike(String contentLike) {
        this.contentLike = contentLike;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
