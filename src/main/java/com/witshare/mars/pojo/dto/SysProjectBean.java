package com.witshare.mars.pojo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class SysProjectBean {

    public static String ID = "id";
    public static String PROJECT_GID = "projectGid";
    public static String PROJECT_NAME_ZH = "projectNameZh";
    public static String PROJECT_NAME_EN = "projectNameEn";
    public static String PROJECT_TYPE = "projectType";
    public static String TOKEN = "token";
    public static String ICO = "ico";
    public static String LOG = "log";
    public static String LOG_STR = "logStr";
    public static String GRADE = "grade";
    public static String GRADE_STR = "gradeStr";
    public static String PDF_ZH = "pdfZh";
    public static String PDF_ZH_NAME = "pdfZhName";
    public static String PDF_EN = "pdfEn";
    public static String PDF_EN_NAME = "pdfEnName";
    public static String VIEW = "view";
    public static String INSTRUCTION_ZH = "instructionZh";
    public static String INSTRUCTION_EN = "instructionEn";
    public static String CONTENT_ZH = "contentZh";
    public static String CONTENT_EN = "contentEn";
    public static String OFFICIAL_LINK = "officialLink";
    public static String WHITE_PAPER_LINK_ZH = "whitePaperLinkZh";
    public static String WHITE_PAPER_LINK_EN = "whitePaperLinkEn";
    public static String LOCATION_ZH = "locationZh";
    public static String LOCATION_EN = "locationEn";
    public static String ACCEPTING = "accepting";

    public static String EXCHANGE_LIST = "exchangeList";
    public static String SOCIAL_LIST = "socialList";

    public static String TEAM_SCORE = "teamScore";
    public static String PRODUCT_SCORE = "productScore";
    public static String SCHEDULE_SCORE = "scheduleScore";
    public static String COMMERCIAL_SUBSTANCE_SCORE = "commercialSubstanceScore";
    public static String TOKENS_OPERATION_SCORE = "tokensOperationScore";


    public static String PICTURE_URL = "pictureUrl";

    private Integer scheduleScore;
    private String projectNameZh;
    private String projectNameEn;
    private Integer ico;
    private Integer icoDb;
    private String log;
    private BigDecimal grade;
    private String gradeStr;
    private String pdfZh;
    private String pdfZhName;
    private String pdfEn;
    private String pdfEnName;
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
    private String gitHub;
    private String twitter;
    private String facebook;
    private String weibo;
    private String bitCoin;
    private String Binance;
    private String projectName;
    private String objectName;
    private String token;
    private String whitePaperLink;
    private Integer projectType;
    private Integer projectAnonymous;
    private String projectContent;
    private Long id;
    private String projectGid;
    private Long idDb;
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

    private String queryStr;
    private String img;
    private Integer pageSize;
    private Integer pageNum;
    private Integer typeId;
    private List<WebSiteManagementBean> socialList = new LinkedList<>();
    private List<WebSiteManagementBean> websiteList = new LinkedList<>();

    public static String getICO() {
        return ICO;
    }

    public static void setICO(String ICO) {
        SysProjectBean.ICO = ICO;
    }

    public static String getGRADE() {
        return GRADE;
    }

    public static void setGRADE(String GRADE) {
        SysProjectBean.GRADE = GRADE;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public SysProjectBean setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public String getPdfZhName() {
        return pdfZhName;
    }

    public void setPdfZhName(String pdfZhName) {
        this.pdfZhName = pdfZhName;
    }

    public String getPdfEnName() {
        return pdfEnName;
    }

    public void setPdfEnName(String pdfEnName) {
        this.pdfEnName = pdfEnName;
    }

    public Integer getIcoDb() {
        return icoDb;
    }

    public void setIcoDb(Integer icoDb) {
        this.icoDb = icoDb;
    }

    public Long getIdDb() {
        return idDb;
    }

    public void setIdDb(Long idDb) {
        this.idDb = idDb;
    }

    public String getGradeStr() {
        return gradeStr;
    }

    public void setGradeStr(String gradeStr) {
        this.gradeStr = gradeStr;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }


    public Integer getScheduleScore() {
        return scheduleScore;
    }

    public void setScheduleScore(Integer scheduleScore) {
        this.scheduleScore = scheduleScore;
    }

    public String getProjectNameZh() {
        return projectNameZh;
    }

    public void setProjectNameZh(String projectNameZh) {
        this.projectNameZh = projectNameZh;
    }

    public String getProjectNameEn() {
        return projectNameEn;
    }

    public void setProjectNameEn(String projectNameEn) {
        this.projectNameEn = projectNameEn;
    }

    public Integer getIco() {
        return ico;
    }

    public void setIco(Integer ico) {
        this.ico = ico;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    public String getPdfZh() {
        return pdfZh;
    }

    public void setPdfZh(String pdfZh) {
        this.pdfZh = pdfZh;
    }

    public String getPdfEn() {
        return pdfEn;
    }

    public void setPdfEn(String pdfEn) {
        this.pdfEn = pdfEn;
    }

    public String getInstructionZh() {
        return instructionZh;
    }

    public void setInstructionZh(String instructionZh) {
        this.instructionZh = instructionZh;
    }

    public String getInstructionEn() {
        return instructionEn;
    }

    public void setInstructionEn(String instructionEn) {
        this.instructionEn = instructionEn;
    }

    public String getContentZh() {
        return contentZh;
    }

    public void setContentZh(String contentZh) {
        this.contentZh = contentZh;
    }

    public String getContentEn() {
        return contentEn;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn;
    }

    public String getWhitePaperLinkZh() {
        return whitePaperLinkZh;
    }

    public void setWhitePaperLinkZh(String whitePaperLinkZh) {
        this.whitePaperLinkZh = whitePaperLinkZh;
    }

    public String getWhitePaperLinkEn() {
        return whitePaperLinkEn;
    }

    public void setWhitePaperLinkEn(String whitePaperLinkEn) {
        this.whitePaperLinkEn = whitePaperLinkEn;
    }

    public String getLocationZh() {
        return locationZh;
    }

    public void setLocationZh(String locationZh) {
        this.locationZh = locationZh;
    }

    public String getLocationEn() {
        return locationEn;
    }

    public void setLocationEn(String locationEn) {
        this.locationEn = locationEn;
    }

    public String getAccepting() {
        return accepting;
    }

    public void setAccepting(String accepting) {
        this.accepting = accepting;
    }

    public String getGitHub() {
        return gitHub;
    }

    public void setGitHub(String gitHub) {
        this.gitHub = gitHub;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getBitCoin() {
        return bitCoin;
    }

    public void setBitCoin(String bitCoin) {
        this.bitCoin = bitCoin;
    }

    public String getBinance() {
        return Binance;
    }

    public void setBinance(String binance) {
        Binance = binance;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getWhitePaperLink() {
        return whitePaperLink;
    }

    public void setWhitePaperLink(String whitePaperLink) {
        this.whitePaperLink = whitePaperLink;
    }

    public Integer getProjectType() {
        return projectType;
    }

    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }

    public Integer getProjectAnonymous() {
        return projectAnonymous;
    }

    public void setProjectAnonymous(Integer projectAnonymous) {
        this.projectAnonymous = projectAnonymous;
    }

    public String getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOfficialLink() {
        return officialLink;
    }

    public void setOfficialLink(String officialLink) {
        this.officialLink = officialLink;
    }

    public String getProjectAccepting() {
        return projectAccepting;
    }

    public void setProjectAccepting(String projectAccepting) {
        this.projectAccepting = projectAccepting;
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

    public Integer getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(Integer teamScore) {
        this.teamScore = teamScore;
    }

    public Integer getProductScore() {
        return productScore;
    }

    public void setProductScore(Integer productScore) {
        this.productScore = productScore;
    }

    public Integer getCommercialSubstanceScore() {
        return commercialSubstanceScore;
    }

    public void setCommercialSubstanceScore(Integer commercialSubstanceScore) {
        this.commercialSubstanceScore = commercialSubstanceScore;
    }

    public Integer getTokensOperationScore() {
        return tokensOperationScore;
    }

    public void setTokensOperationScore(Integer tokensOperationScore) {
        this.tokensOperationScore = tokensOperationScore;
    }

    public BigDecimal getProjectGrade() {
        return projectGrade;
    }

    public void setProjectGrade(BigDecimal projectGrade) {
        this.projectGrade = projectGrade;
    }

    public Boolean getStarProject() {
        return starProject;
    }

    public void setStarProject(Boolean starProject) {
        this.starProject = starProject;
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

    public String getQueryStr() {
        return queryStr;
    }

    public void setQueryStr(String queryStr) {
        this.queryStr = queryStr;
    }

    public List<WebSiteManagementBean> getSocialList() {
        return socialList;
    }

    public SysProjectBean setSocialList(List<WebSiteManagementBean> socialList) {
        this.socialList = socialList;
        return this;
    }

    public List<WebSiteManagementBean> getWebsiteList() {
        return websiteList;
    }

    public SysProjectBean setWebsiteList(List<WebSiteManagementBean> websiteList) {
        this.websiteList = websiteList;
        return this;
    }

    public class ProjectWebSiteBean {
        private Long typeId;
        private String linkUrl;

        public Long getTypeId() {
            return typeId;
        }

        public void setTypeId(Long typeId) {
            this.typeId = typeId;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }
    }


}