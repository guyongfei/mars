package com.witshare.mars.pojo.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class SysProject {
    private Long id;

    private String projectGid;

    private String projectToken;

    private String tokenAddress;

    private String platformAddress;

    private String projectAddress;

    private String projectLogoLink;

    private String projectImgLink;

    private BigDecimal softCap;

    private BigDecimal hardCap;

    private BigDecimal minPurchaseAmount;

    private BigDecimal maxPurchaseAmount;

    private Timestamp startTime;

    private Timestamp endTime;

    private BigDecimal priceRate;

    private Integer projectStatus;

    private Integer isAvailable;

    private Integer defaultProject;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Integer tokenDecimal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public void setProjectGid(String projectGid) {
        this.projectGid = projectGid == null ? null : projectGid.trim();
    }

    public String getProjectToken() {
        return projectToken;
    }

    public void setProjectToken(String projectToken) {
        this.projectToken = projectToken == null ? null : projectToken.trim();
    }

    public String getTokenAddress() {
        return tokenAddress;
    }

    public void setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress == null ? null : tokenAddress.trim();
    }

    public String getPlatformAddress() {
        return platformAddress;
    }

    public void setPlatformAddress(String platformAddress) {
        this.platformAddress = platformAddress == null ? null : platformAddress.trim();
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress == null ? null : projectAddress.trim();
    }

    public String getProjectLogoLink() {
        return projectLogoLink;
    }

    public void setProjectLogoLink(String projectLogoLink) {
        this.projectLogoLink = projectLogoLink == null ? null : projectLogoLink.trim();
    }

    public String getProjectImgLink() {
        return projectImgLink;
    }

    public void setProjectImgLink(String projectImgLink) {
        this.projectImgLink = projectImgLink == null ? null : projectImgLink.trim();
    }

    public BigDecimal getSoftCap() {
        return softCap;
    }

    public void setSoftCap(BigDecimal softCap) {
        this.softCap = softCap;
    }

    public BigDecimal getHardCap() {
        return hardCap;
    }

    public void setHardCap(BigDecimal hardCap) {
        this.hardCap = hardCap;
    }

    public BigDecimal getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public void setMinPurchaseAmount(BigDecimal minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
    }

    public BigDecimal getMaxPurchaseAmount() {
        return maxPurchaseAmount;
    }

    public void setMaxPurchaseAmount(BigDecimal maxPurchaseAmount) {
        this.maxPurchaseAmount = maxPurchaseAmount;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(BigDecimal priceRate) {
        this.priceRate = priceRate;
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Integer getDefaultProject() {
        return defaultProject;
    }

    public void setDefaultProject(Integer defaultProject) {
        this.defaultProject = defaultProject;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getTokenDecimal() {
        return tokenDecimal;
    }

    public void setTokenDecimal(Integer tokenDecimal) {
        this.tokenDecimal = tokenDecimal;
    }
}