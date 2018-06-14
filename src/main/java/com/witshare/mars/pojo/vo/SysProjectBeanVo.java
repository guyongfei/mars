package com.witshare.mars.pojo.vo;

import com.witshare.mars.pojo.dto.ProjectDescriptionBean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

public class SysProjectBeanVo {


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
    private Timestamp startTime;
    private Timestamp endTime;
    private BigDecimal startPrice;
    private BigDecimal endPrice;
    private int projectStatus;
    private int isAvailable;

    private Timestamp createTime;
    private Timestamp updateTime;

    private Map<String, ProjectDescriptionBean> descriptions = new LinkedHashMap<>();
    private Map<String, String> websites = new LinkedHashMap<>();

    public String getProjectGid() {
        return projectGid;
    }

    public SysProjectBeanVo setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public String getProjectToken() {
        return projectToken;
    }

    public SysProjectBeanVo setProjectToken(String projectToken) {
        this.projectToken = projectToken;
        return this;
    }

    public String getTokenAddress() {
        return tokenAddress;
    }

    public SysProjectBeanVo setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
        return this;
    }

    public String getPlatformAddress() {
        return platformAddress;
    }

    public SysProjectBeanVo setPlatformAddress(String platformAddress) {
        this.platformAddress = platformAddress;
        return this;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public SysProjectBeanVo setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
        return this;
    }

    public String getProjectLogoLink() {
        return projectLogoLink;
    }

    public SysProjectBeanVo setProjectLogoLink(String projectLogoLink) {
        this.projectLogoLink = projectLogoLink;
        return this;
    }

    public String getProjectImgLink() {
        return projectImgLink;
    }

    public SysProjectBeanVo setProjectImgLink(String projectImgLink) {
        this.projectImgLink = projectImgLink;
        return this;
    }

    public BigDecimal getSoftCap() {
        return softCap;
    }

    public SysProjectBeanVo setSoftCap(BigDecimal softCap) {
        this.softCap = softCap;
        return this;
    }

    public BigDecimal getHardCap() {
        return hardCap;
    }

    public SysProjectBeanVo setHardCap(BigDecimal hardCap) {
        this.hardCap = hardCap;
        return this;
    }

    public BigDecimal getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public SysProjectBeanVo setMinPurchaseAmount(BigDecimal minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
        return this;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public SysProjectBeanVo setStartTime(Timestamp startTime) {
        this.startTime = startTime;
        return this;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public SysProjectBeanVo setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public SysProjectBeanVo setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
        return this;
    }

    public BigDecimal getEndPrice() {
        return endPrice;
    }

    public SysProjectBeanVo setEndPrice(BigDecimal endPrice) {
        this.endPrice = endPrice;
        return this;
    }

    public int getProjectStatus() {
        return projectStatus;
    }

    public SysProjectBeanVo setProjectStatus(int projectStatus) {
        this.projectStatus = projectStatus;
        return this;
    }

    public int getIsAvailable() {
        return isAvailable;
    }

    public SysProjectBeanVo setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public SysProjectBeanVo setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public SysProjectBeanVo setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Map<String, ProjectDescriptionBean> getDescriptions() {
        return descriptions;
    }

    public SysProjectBeanVo setDescriptions(Map<String, ProjectDescriptionBean> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    public Map<String, String> getWebsites() {
        return websites;
    }

    public SysProjectBeanVo setWebsites(Map<String, String> websites) {
        this.websites = websites;
        return this;
    }

}