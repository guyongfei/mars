package com.witshare.mars.pojo.vo;

import com.witshare.mars.pojo.dto.ProjectDescriptionBean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

public class SysProjectBeanFrontListVo {


    private String projectGid;
    private String projectToken;
    private String tokenAddress;
    private String platformAddress;
    private String projectAddress;
    private String projectLogoLink;
    private BigDecimal softCap;
    private BigDecimal hardCap;
    private BigDecimal minPurchaseAmount;
    private Timestamp startTime;
    private Timestamp endTime;
    private BigDecimal startPrice;
    private BigDecimal endPrice;
    private int projectStatus;
    private String projectInstruction;
    private String whitePaperLink;
    private String projectContent;

    private Timestamp createTime;
    private Timestamp updateTime;

    private Map<String, ProjectDescriptionBean> descriptions = new LinkedHashMap<>();
    private Map<String, String> websites = new LinkedHashMap<>();

    public String getProjectGid() {
        return projectGid;
    }

    public SysProjectBeanFrontListVo setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public String getProjectToken() {
        return projectToken;
    }

    public SysProjectBeanFrontListVo setProjectToken(String projectToken) {
        this.projectToken = projectToken;
        return this;
    }

    public String getTokenAddress() {
        return tokenAddress;
    }

    public SysProjectBeanFrontListVo setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
        return this;
    }

    public String getPlatformAddress() {
        return platformAddress;
    }

    public SysProjectBeanFrontListVo setPlatformAddress(String platformAddress) {
        this.platformAddress = platformAddress;
        return this;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public SysProjectBeanFrontListVo setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
        return this;
    }

    public String getProjectLogoLink() {
        return projectLogoLink;
    }

    public SysProjectBeanFrontListVo setProjectLogoLink(String projectLogoLink) {
        this.projectLogoLink = projectLogoLink;
        return this;
    }



    public BigDecimal getSoftCap() {
        return softCap;
    }

    public SysProjectBeanFrontListVo setSoftCap(BigDecimal softCap) {
        this.softCap = softCap;
        return this;
    }

    public BigDecimal getHardCap() {
        return hardCap;
    }

    public SysProjectBeanFrontListVo setHardCap(BigDecimal hardCap) {
        this.hardCap = hardCap;
        return this;
    }

    public BigDecimal getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public SysProjectBeanFrontListVo setMinPurchaseAmount(BigDecimal minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
        return this;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public SysProjectBeanFrontListVo setStartTime(Timestamp startTime) {
        this.startTime = startTime;
        return this;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public SysProjectBeanFrontListVo setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public SysProjectBeanFrontListVo setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
        return this;
    }

    public BigDecimal getEndPrice() {
        return endPrice;
    }

    public SysProjectBeanFrontListVo setEndPrice(BigDecimal endPrice) {
        this.endPrice = endPrice;
        return this;
    }

    public int getProjectStatus() {
        return projectStatus;
    }

    public SysProjectBeanFrontListVo setProjectStatus(int projectStatus) {
        this.projectStatus = projectStatus;
        return this;
    }


    public Timestamp getCreateTime() {
        return createTime;
    }

    public SysProjectBeanFrontListVo setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public SysProjectBeanFrontListVo setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Map<String, ProjectDescriptionBean> getDescriptions() {
        return descriptions;
    }

    public SysProjectBeanFrontListVo setDescriptions(Map<String, ProjectDescriptionBean> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    public Map<String, String> getWebsites() {
        return websites;
    }

    public SysProjectBeanFrontListVo setWebsites(Map<String, String> websites) {
        this.websites = websites;
        return this;
    }

    public String getProjectInstruction() {
        return projectInstruction;
    }

    public SysProjectBeanFrontListVo setProjectInstruction(String projectInstruction) {
        this.projectInstruction = projectInstruction;
        return this;
    }

    public String getWhitePaperLink() {
        return whitePaperLink;
    }

    public SysProjectBeanFrontListVo setWhitePaperLink(String whitePaperLink) {
        this.whitePaperLink = whitePaperLink;
        return this;
    }

    public String getProjectContent() {
        return projectContent;
    }

    public SysProjectBeanFrontListVo setProjectContent(String projectContent) {
        this.projectContent = projectContent;
        return this;
    }
}