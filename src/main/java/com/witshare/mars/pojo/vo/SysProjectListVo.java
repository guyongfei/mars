package com.witshare.mars.pojo.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class SysProjectListVo {

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

    private Timestamp startTime;

    private Timestamp endTime;

    private BigDecimal priceRate;

    private Integer projectStatus;

    private Integer isAvailable;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Integer tokenDecimal;

    public SysProjectListVo() {
    }

    public SysProjectListVo newInstance() {
        return new SysProjectListVo();
    }

    public Long getId() {
        return id;
    }

    public SysProjectListVo setId(Long id) {
        this.id = id;
        return this;
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

    public String getTokenAddress() {
        return tokenAddress;
    }

    public SysProjectListVo setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
        return this;
    }

    public String getPlatformAddress() {
        return platformAddress;
    }

    public SysProjectListVo setPlatformAddress(String platformAddress) {
        this.platformAddress = platformAddress;
        return this;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public SysProjectListVo setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
        return this;
    }

    public String getProjectLogoLink() {
        return projectLogoLink;
    }

    public SysProjectListVo setProjectLogoLink(String projectLogoLink) {
        this.projectLogoLink = projectLogoLink;
        return this;
    }

    public String getProjectImgLink() {
        return projectImgLink;
    }

    public SysProjectListVo setProjectImgLink(String projectImgLink) {
        this.projectImgLink = projectImgLink;
        return this;
    }

    public BigDecimal getSoftCap() {
        return softCap;
    }

    public SysProjectListVo setSoftCap(BigDecimal softCap) {
        this.softCap = softCap;
        return this;
    }

    public BigDecimal getHardCap() {
        return hardCap;
    }

    public SysProjectListVo setHardCap(BigDecimal hardCap) {
        this.hardCap = hardCap;
        return this;
    }

    public BigDecimal getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public SysProjectListVo setMinPurchaseAmount(BigDecimal minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
        return this;
    }

    public BigDecimal getPriceRate() {
        return priceRate;
    }

    public SysProjectListVo setPriceRate(BigDecimal priceRate) {
        this.priceRate = priceRate;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public SysProjectListVo setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public SysProjectListVo setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getTokenDecimal() {
        return tokenDecimal;
    }

    public SysProjectListVo setTokenDecimal(Integer tokenDecimal) {
        this.tokenDecimal = tokenDecimal;
        return this;
    }
}