package com.witshare.mars.pojo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


public class ProjectRespBean {


    private Long id;

    private String projectName;

    private String projectToken;

    private String projectInstruction;

    private String projectLogoLink;

    private String projectImgLink;

    private String projectContent;

    private Integer projectIco;

    private String officialLink;

    private String whitePaperLink;

    private String officialLogoLink;

    private String whitePaperLogoLink;

    private Integer commentCount;

    private Integer followerCount;

    private Boolean followOrNot;

    private String projectType;

    private String subProjectType;

    private BigDecimal teamScore;

    private BigDecimal productScore;

    private BigDecimal scheduleScore;

    private BigDecimal commercialSubstanceScore;

    private BigDecimal tokensOperationScore;

    private String projectGrade;

    private String gradeReportLink;

    private Integer projectStatus;

    private Timestamp createTime;

    private List<Map<String, Object>> socialList;

    private List<Map<String, Object>> exchangeList;


    public List<Map<String, Object>> getSocialList() {
        return socialList;
    }

    public void setSocialList(List<Map<String, Object>> socialList) {
        this.socialList = socialList;
    }

    public List<Map<String, Object>> getExchangeList() {
        return exchangeList;
    }

    public void setExchangeList(List<Map<String, Object>> exchangeList) {
        this.exchangeList = exchangeList;
    }

    public String getProjectInstruction() {
        return projectInstruction;
    }

    public void setProjectInstruction(String projectInstruction) {
        this.projectInstruction = projectInstruction;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFollowOrNot() {
        return followOrNot;
    }

    public void setFollowOrNot(Boolean followOrNot) {
        this.followOrNot = followOrNot;
    }

    public String getProjectName() {
        return projectName;
    }


    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectToken() {
        return projectToken;
    }


    public void setProjectToken(String projectToken) {
        this.projectToken = projectToken == null ? null : projectToken.trim();
    }


    public Integer getProjectIco() {
        return projectIco;
    }


    public void setProjectIco(Integer projectIco) {
        this.projectIco = projectIco;
    }


    public String getOfficialLink() {
        return officialLink;
    }


    public void setOfficialLink(String officialLink) {
        this.officialLink = officialLink == null ? null : officialLink.trim();
    }

    public String getWhitePaperLink() {
        return whitePaperLink;
    }


    public void setWhitePaperLink(String whitePaperLink) {
        this.whitePaperLink = whitePaperLink == null ? null : whitePaperLink.trim();
    }


    public Integer getCommentCount() {
        return commentCount;
    }


    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }


    public Integer getFollowerCount() {
        return followerCount;
    }


    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }


    public BigDecimal getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(BigDecimal teamScore) {
        this.teamScore = teamScore;
    }

    public BigDecimal getProductScore() {
        return productScore;
    }

    public void setProductScore(BigDecimal productScore) {
        this.productScore = productScore;
    }

    public BigDecimal getScheduleScore() {
        return scheduleScore;
    }

    public void setScheduleScore(BigDecimal scheduleScore) {
        this.scheduleScore = scheduleScore;
    }

    public BigDecimal getCommercialSubstanceScore() {
        return commercialSubstanceScore;
    }

    public void setCommercialSubstanceScore(BigDecimal commercialSubstanceScore) {
        this.commercialSubstanceScore = commercialSubstanceScore;
    }

    public BigDecimal getTokensOperationScore() {
        return tokensOperationScore;
    }

    public void setTokensOperationScore(BigDecimal tokensOperationScore) {
        this.tokensOperationScore = tokensOperationScore;
    }

    public String getProjectGrade() {
        return projectGrade;
    }


    public void setProjectGrade(String projectGrade) {
        this.projectGrade = projectGrade == null ? null : projectGrade.trim();
    }


    public String getGradeReportLink() {
        return gradeReportLink;
    }


    public void setGradeReportLink(String gradeReportLink) {
        this.gradeReportLink = gradeReportLink == null ? null : gradeReportLink.trim();
    }


    public Integer getProjectStatus() {
        return projectStatus;
    }


    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
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


    public String getProjectContent() {
        return projectContent;
    }


    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent == null ? null : projectContent.trim();
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getSubProjectType() {
        return subProjectType;
    }

    public void setSubProjectType(String subProjectType) {
        this.subProjectType = subProjectType;
    }

    public String getOfficialLogoLink() {
        return officialLogoLink;
    }

    public void setOfficialLogoLink(String officialLogoLink) {
        this.officialLogoLink = officialLogoLink;
    }

    public String getWhitePaperLogoLink() {
        return whitePaperLogoLink;
    }

    public void setWhitePaperLogoLink(String whitePaperLogoLink) {
        this.whitePaperLogoLink = whitePaperLogoLink;
    }
}
