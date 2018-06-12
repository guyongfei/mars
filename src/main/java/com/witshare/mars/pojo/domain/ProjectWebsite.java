package com.witshare.mars.pojo.domain;

import java.sql.Timestamp;

public class ProjectWebsite {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_website.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_website.project_gid
     *
     * @mbggenerated
     */
    private String projectGid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_website.website_type
     *
     * @mbggenerated
     */
    private String websiteType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_website.link_url
     *
     * @mbggenerated
     */
    private String linkUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_website.create_time
     *
     * @mbggenerated
     */
    private Timestamp createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_website.update_time
     *
     * @mbggenerated
     */
    private Timestamp updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_website.id
     *
     * @return the value of project_website.id
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_website.id
     *
     * @param id the value for project_website.id
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_website.project_gid
     *
     * @return the value of project_website.project_gid
     * @mbggenerated
     */
    public String getProjectGid() {
        return projectGid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_website.project_gid
     *
     * @param projectGid the value for project_website.project_gid
     * @mbggenerated
     */
    public void setProjectGid(String projectGid) {
        this.projectGid = projectGid == null ? null : projectGid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_website.website_type
     *
     * @return the value of project_website.website_type
     * @mbggenerated
     */
    public String getWebsiteType() {
        return websiteType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_website.website_type
     *
     * @param websiteType the value for project_website.website_type
     * @mbggenerated
     */
    public void setWebsiteType(String websiteType) {
        this.websiteType = websiteType == null ? null : websiteType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_website.link_url
     *
     * @return the value of project_website.link_url
     * @mbggenerated
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_website.link_url
     *
     * @param linkUrl the value for project_website.link_url
     * @mbggenerated
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_website.create_time
     *
     * @return the value of project_website.create_time
     * @mbggenerated
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_website.create_time
     *
     * @param createTime the value for project_website.create_time
     * @mbggenerated
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_website.update_time
     *
     * @return the value of project_website.update_time
     * @mbggenerated
     */
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_website.update_time
     *
     * @param updateTime the value for project_website.update_time
     * @mbggenerated
     */
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}