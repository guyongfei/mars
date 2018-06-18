package com.witshare.mars.pojo.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

public class SysProjectBeanFrontInfoVo {


    private String projectGid;
    private String ProjectName;
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
    private String projectInstruction;
    private String whitePaperLink;
    private String projectContent;

    private BigDecimal price;
    private BigDecimal soldAmount;
    private Long nextPriceInterval;


    private Map<String, String> websites = new LinkedHashMap<>();

    public SysProjectBeanFrontInfoVo() {
    }

    public static SysProjectBeanFrontInfoVo newInstance() {
        return new SysProjectBeanFrontInfoVo();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public SysProjectBeanFrontInfoVo setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getSoldAmount() {
        return soldAmount;
    }

    public SysProjectBeanFrontInfoVo setSoldAmount(BigDecimal soldAmount) {
        this.soldAmount = soldAmount;
        return this;
    }

    public Long getNextPriceInterval() {
        return nextPriceInterval;
    }

    public SysProjectBeanFrontInfoVo setNextPriceInterval(Long nextPriceInterval) {
        this.nextPriceInterval = nextPriceInterval;
        return this;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public SysProjectBeanFrontInfoVo setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public SysProjectBeanFrontInfoVo setProjectName(String projectName) {
        ProjectName = projectName;
        return this;
    }

    public String getProjectToken() {
        return projectToken;
    }

    public SysProjectBeanFrontInfoVo setProjectToken(String projectToken) {
        this.projectToken = projectToken;
        return this;
    }

    public String getTokenAddress() {
        return tokenAddress;
    }

    public SysProjectBeanFrontInfoVo setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
        return this;
    }

    public String getPlatformAddress() {
        return platformAddress;
    }

    public SysProjectBeanFrontInfoVo setPlatformAddress(String platformAddress) {
        this.platformAddress = platformAddress;
        return this;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public SysProjectBeanFrontInfoVo setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
        return this;
    }

    public String getProjectLogoLink() {
        return projectLogoLink;
    }

    public SysProjectBeanFrontInfoVo setProjectLogoLink(String projectLogoLink) {
        this.projectLogoLink = projectLogoLink;
        return this;
    }

    public String getProjectImgLink() {
        return projectImgLink;
    }

    public SysProjectBeanFrontInfoVo setProjectImgLink(String projectImgLink) {
        this.projectImgLink = projectImgLink;
        return this;
    }

    public BigDecimal getSoftCap() {
        return softCap;
    }

    public SysProjectBeanFrontInfoVo setSoftCap(BigDecimal softCap) {
        this.softCap = softCap;
        return this;
    }

    public BigDecimal getHardCap() {
        return hardCap;
    }

    public SysProjectBeanFrontInfoVo setHardCap(BigDecimal hardCap) {
        this.hardCap = hardCap;
        return this;
    }

    public BigDecimal getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public SysProjectBeanFrontInfoVo setMinPurchaseAmount(BigDecimal minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
        return this;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public SysProjectBeanFrontInfoVo setStartTime(Timestamp startTime) {
        this.startTime = startTime;
        return this;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public SysProjectBeanFrontInfoVo setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public SysProjectBeanFrontInfoVo setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
        return this;
    }

    public BigDecimal getEndPrice() {
        return endPrice;
    }

    public SysProjectBeanFrontInfoVo setEndPrice(BigDecimal endPrice) {
        this.endPrice = endPrice;
        return this;
    }

    public int getProjectStatus() {
        return projectStatus;
    }

    public SysProjectBeanFrontInfoVo setProjectStatus(int projectStatus) {
        this.projectStatus = projectStatus;
        return this;
    }

    public Map<String, String> getWebsites() {
        return websites;
    }

    public SysProjectBeanFrontInfoVo setWebsites(Map<String, String> websites) {
        this.websites = websites;
        return this;
    }

    public String getProjectInstruction() {
        return projectInstruction;
    }

    public SysProjectBeanFrontInfoVo setProjectInstruction(String projectInstruction) {
        this.projectInstruction = projectInstruction;
        return this;
    }

    public String getWhitePaperLink() {
        return whitePaperLink;
    }

    public SysProjectBeanFrontInfoVo setWhitePaperLink(String whitePaperLink) {
        this.whitePaperLink = whitePaperLink;
        return this;
    }

    public String getProjectContent() {
        return projectContent;
    }

    public SysProjectBeanFrontInfoVo setProjectContent(String projectContent) {
        this.projectContent = projectContent;
        return this;
    }
}