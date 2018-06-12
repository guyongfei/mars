package com.witshare.mars.pojo.dto;

import java.sql.Timestamp;

public class SysCommentBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_comment.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_comment.comment_author_id
     *
     * @mbggenerated
     */
    private Long commentAuthorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_comment.comment_on_article_id
     *
     * @mbggenerated
     */
    private Long commentOnArticleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_comment.comment_original_comment_id
     *
     * @mbggenerated
     */
    private Long commentOriginalCommentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_comment.comment_status
     *
     * @mbggenerated
     */
    private Integer commentStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_comment.comment_ip
     *
     * @mbggenerated
     */
    private String commentIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_comment.create_time
     *
     * @mbggenerated
     */
    private Timestamp createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_comment.update_time
     *
     * @mbggenerated
     */
    private Timestamp updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_comment.comment_content
     *
     * @mbggenerated
     */
    private String commentContent;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_comment.id
     *
     * @return the value of sys_comment.id
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_comment.id
     *
     * @param id the value for sys_comment.id
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_comment.comment_author_id
     *
     * @return the value of sys_comment.comment_author_id
     * @mbggenerated
     */
    public Long getCommentAuthorId() {
        return commentAuthorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_comment.comment_author_id
     *
     * @param commentAuthorId the value for sys_comment.comment_author_id
     * @mbggenerated
     */
    public void setCommentAuthorId(Long commentAuthorId) {
        this.commentAuthorId = commentAuthorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_comment.comment_on_article_id
     *
     * @return the value of sys_comment.comment_on_article_id
     * @mbggenerated
     */
    public Long getCommentOnArticleId() {
        return commentOnArticleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_comment.comment_on_article_id
     *
     * @param commentOnArticleId the value for sys_comment.comment_on_article_id
     * @mbggenerated
     */
    public void setCommentOnArticleId(Long commentOnArticleId) {
        this.commentOnArticleId = commentOnArticleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_comment.comment_original_comment_id
     *
     * @return the value of sys_comment.comment_original_comment_id
     * @mbggenerated
     */
    public Long getCommentOriginalCommentId() {
        return commentOriginalCommentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_comment.comment_original_comment_id
     *
     * @param commentOriginalCommentId the value for sys_comment.comment_original_comment_id
     * @mbggenerated
     */
    public void setCommentOriginalCommentId(Long commentOriginalCommentId) {
        this.commentOriginalCommentId = commentOriginalCommentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_comment.comment_status
     *
     * @return the value of sys_comment.comment_status
     * @mbggenerated
     */
    public Integer getCommentStatus() {
        return commentStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_comment.comment_status
     *
     * @param commentStatus the value for sys_comment.comment_status
     * @mbggenerated
     */
    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_comment.comment_ip
     *
     * @return the value of sys_comment.comment_ip
     * @mbggenerated
     */
    public String getCommentIp() {
        return commentIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_comment.comment_ip
     *
     * @param commentIp the value for sys_comment.comment_ip
     * @mbggenerated
     */
    public void setCommentIp(String commentIp) {
        this.commentIp = commentIp == null ? null : commentIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_comment.create_time
     *
     * @return the value of sys_comment.create_time
     * @mbggenerated
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_comment.create_time
     *
     * @param createTime the value for sys_comment.create_time
     * @mbggenerated
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_comment.update_time
     *
     * @return the value of sys_comment.update_time
     * @mbggenerated
     */
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_comment.update_time
     *
     * @param updateTime the value for sys_comment.update_time
     * @mbggenerated
     */
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_comment.comment_content
     *
     * @return the value of sys_comment.comment_content
     * @mbggenerated
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_comment.comment_content
     *
     * @param commentContent the value for sys_comment.comment_content
     * @mbggenerated
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }
}