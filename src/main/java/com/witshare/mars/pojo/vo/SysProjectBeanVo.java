package com.witshare.mars.pojo.vo;

import com.witshare.mars.pojo.dto.WebSiteManagementBean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class SysProjectBeanVo {


    private Integer scheduleScore;
    private String projectNameZh;
    private String projectNameEn;
    private Integer ico;
    private String log;
    private BigDecimal grade;
    private String gradeStr;
    private String pdfZh;
    private String pdfEn;
    private String view;
    private String instructionZh;
    private String instructionEn;
    private String contentZh;
    private String contentEn;
    private String whitePaperLinkZh;
    private String whitePaperLinkEn;
    private String locationZh;
    private String locationEn;
    private String accepting;
    private String projectName;
    private String objectName;
    private String token;
    private String whitePaperLink;
    private Integer projectType;
    private Integer projectAnonymous;
    private String projectContent;
    private Long id;
    private String officialLink;
    private String projectAccepting;
    private Integer commentCount;
    private Integer followerCount;
    private Integer teamScore;
    private Integer productScore;
    private Integer commercialSubstanceScore;
    private Integer tokensOperationScore;
    private BigDecimal projectGrade;
    private Boolean starProject;
    private Timestamp createTime;
    private Timestamp updateTime;

    private List<WebSiteManagementBean> websiteList = new LinkedList<>();

    private String projectGid;
    private String queryStr;
    private String img;
    private Integer pageSize;
    private Integer pageNum;
    private Integer typeId;
    private Integer typePId;

    public Integer getScheduleScore() {
        return scheduleScore;
    }

    public SysProjectBeanVo setScheduleScore(Integer scheduleScore) {
        this.scheduleScore = scheduleScore;
        return this;
    }

    public String getProjectNameZh() {
        return projectNameZh;
    }

    public SysProjectBeanVo setProjectNameZh(String projectNameZh) {
        this.projectNameZh = projectNameZh;
        return this;
    }

    public String getProjectNameEn() {
        return projectNameEn;
    }

    public SysProjectBeanVo setProjectNameEn(String projectNameEn) {
        this.projectNameEn = projectNameEn;
        return this;
    }

    public Integer getIco() {
        return ico;
    }

    public SysProjectBeanVo setIco(Integer ico) {
        this.ico = ico;
        return this;
    }

    public String getLog() {
        return log;
    }

    public SysProjectBeanVo setLog(String log) {
        this.log = log;
        return this;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public SysProjectBeanVo setGrade(BigDecimal grade) {
        this.grade = grade;
        return this;
    }

    public String getGradeStr() {
        return gradeStr;
    }

    public SysProjectBeanVo setGradeStr(String gradeStr) {
        this.gradeStr = gradeStr;
        return this;
    }

    public String getPdfZh() {
        return pdfZh;
    }

    public SysProjectBeanVo setPdfZh(String pdfZh) {
        this.pdfZh = pdfZh;
        return this;
    }

    public String getPdfEn() {
        return pdfEn;
    }

    public SysProjectBeanVo setPdfEn(String pdfEn) {
        this.pdfEn = pdfEn;
        return this;
    }

    public String getView() {
        return view;
    }

    public SysProjectBeanVo setView(String view) {
        this.view = view;
        return this;
    }

    public String getInstructionZh() {
        return instructionZh;
    }

    public SysProjectBeanVo setInstructionZh(String instructionZh) {
        this.instructionZh = instructionZh;
        return this;
    }

    public String getInstructionEn() {
        return instructionEn;
    }

    public SysProjectBeanVo setInstructionEn(String instructionEn) {
        this.instructionEn = instructionEn;
        return this;
    }

    public String getContentZh() {
        return contentZh;
    }

    public SysProjectBeanVo setContentZh(String contentZh) {
        this.contentZh = contentZh;
        return this;
    }

    public String getContentEn() {
        return contentEn;
    }

    public SysProjectBeanVo setContentEn(String contentEn) {
        this.contentEn = contentEn;
        return this;
    }

    public String getWhitePaperLinkZh() {
        return whitePaperLinkZh;
    }

    public SysProjectBeanVo setWhitePaperLinkZh(String whitePaperLinkZh) {
        this.whitePaperLinkZh = whitePaperLinkZh;
        return this;
    }

    public String getWhitePaperLinkEn() {
        return whitePaperLinkEn;
    }

    public SysProjectBeanVo setWhitePaperLinkEn(String whitePaperLinkEn) {
        this.whitePaperLinkEn = whitePaperLinkEn;
        return this;
    }

    public String getLocationZh() {
        return locationZh;
    }

    public SysProjectBeanVo setLocationZh(String locationZh) {
        this.locationZh = locationZh;
        return this;
    }

    public String getLocationEn() {
        return locationEn;
    }

    public SysProjectBeanVo setLocationEn(String locationEn) {
        this.locationEn = locationEn;
        return this;
    }

    public String getAccepting() {
        return accepting;
    }

    public SysProjectBeanVo setAccepting(String accepting) {
        this.accepting = accepting;
        return this;
    }

    public String getProjectName() {
        return projectName;
    }

    public SysProjectBeanVo setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public String getObjectName() {
        return objectName;
    }

    public SysProjectBeanVo setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }

    public String getToken() {
        return token;
    }

    public SysProjectBeanVo setToken(String token) {
        this.token = token;
        return this;
    }

    public String getWhitePaperLink() {
        return whitePaperLink;
    }

    public SysProjectBeanVo setWhitePaperLink(String whitePaperLink) {
        this.whitePaperLink = whitePaperLink;
        return this;
    }

    public Integer getProjectType() {
        return projectType;
    }

    public SysProjectBeanVo setProjectType(Integer projectType) {
        this.projectType = projectType;
        return this;
    }

    public Integer getProjectAnonymous() {
        return projectAnonymous;
    }

    public SysProjectBeanVo setProjectAnonymous(Integer projectAnonymous) {
        this.projectAnonymous = projectAnonymous;
        return this;
    }

    public String getProjectContent() {
        return projectContent;
    }

    public SysProjectBeanVo setProjectContent(String projectContent) {
        this.projectContent = projectContent;
        return this;
    }

    public Long getId() {
        return id;
    }

    public SysProjectBeanVo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getOfficialLink() {
        return officialLink;
    }

    public SysProjectBeanVo setOfficialLink(String officialLink) {
        this.officialLink = officialLink;
        return this;
    }

    public String getProjectAccepting() {
        return projectAccepting;
    }

    public SysProjectBeanVo setProjectAccepting(String projectAccepting) {
        this.projectAccepting = projectAccepting;
        return this;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public SysProjectBeanVo setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public SysProjectBeanVo setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
        return this;
    }

    public Integer getTeamScore() {
        return teamScore;
    }

    public SysProjectBeanVo setTeamScore(Integer teamScore) {
        this.teamScore = teamScore;
        return this;
    }

    public Integer getProductScore() {
        return productScore;
    }

    public SysProjectBeanVo setProductScore(Integer productScore) {
        this.productScore = productScore;
        return this;
    }

    public Integer getCommercialSubstanceScore() {
        return commercialSubstanceScore;
    }

    public SysProjectBeanVo setCommercialSubstanceScore(Integer commercialSubstanceScore) {
        this.commercialSubstanceScore = commercialSubstanceScore;
        return this;
    }

    public Integer getTokensOperationScore() {
        return tokensOperationScore;
    }

    public SysProjectBeanVo setTokensOperationScore(Integer tokensOperationScore) {
        this.tokensOperationScore = tokensOperationScore;
        return this;
    }

    public BigDecimal getProjectGrade() {
        return projectGrade;
    }

    public SysProjectBeanVo setProjectGrade(BigDecimal projectGrade) {
        this.projectGrade = projectGrade;
        return this;
    }

    public Boolean getStarProject() {
        return starProject;
    }

    public SysProjectBeanVo setStarProject(Boolean starProject) {
        this.starProject = starProject;
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

    public List<WebSiteManagementBean> getWebsiteList() {
        return websiteList;
    }

    public SysProjectBeanVo setWebsiteList(List<WebSiteManagementBean> websiteList) {
        this.websiteList = websiteList;
        return this;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public SysProjectBeanVo setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public String getQueryStr() {
        return queryStr;
    }

    public SysProjectBeanVo setQueryStr(String queryStr) {
        this.queryStr = queryStr;
        return this;
    }

    public String getImg() {
        return img;
    }

    public SysProjectBeanVo setImg(String img) {
        this.img = img;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public SysProjectBeanVo setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public SysProjectBeanVo setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public SysProjectBeanVo setTypeId(Integer typeId) {
        this.typeId = typeId;
        return this;
    }

    public Integer getTypePId() {
        return typePId;
    }

    public SysProjectBeanVo setTypePId(Integer typePId) {
        this.typePId = typePId;
        return this;
    }
}