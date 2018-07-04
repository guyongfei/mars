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
    private BigDecimal softCap;
    private BigDecimal hardCap;
    private BigDecimal minPurchaseAmount;
    private Timestamp startTime;
    private Timestamp endTime;
    private int projectStatus;

    private Integer defaultProject;
    private String projectInstruction;
    private String projectContent;

    private BigDecimal priceRate;
    private BigDecimal soldAmount;
    private Long nextPriceInterval;


    private Map<String, String> websites = new LinkedHashMap<>();

    public SysProjectBeanFrontInfoVo() {
    }

    public static SysProjectBeanFrontInfoVo newInstance() {
        return new SysProjectBeanFrontInfoVo();
    }

    public BigDecimal getPriceRate() {
        return priceRate;
    }

    public SysProjectBeanFrontInfoVo setPriceRate(BigDecimal priceRate) {
        this.priceRate = priceRate;
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

    public String getProjectContent() {
        return projectContent;
    }

    public SysProjectBeanFrontInfoVo setProjectContent(String projectContent) {
        this.projectContent = projectContent;
        return this;
    }

    public Integer getDefaultProject() {
        return defaultProject;
    }

    public SysProjectBeanFrontInfoVo setDefaultProject(Integer defaultProject) {
        this.defaultProject = defaultProject;
        return this;
    }
}