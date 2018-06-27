package com.witshare.mars.pojo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class SysProjectBean  extends BasePageBean{

    public static String ID = "id";
    public static String PROJECT_GID = "projectGid";
    public static String TOKEN = "token";

    public static String PROJECT_NAME_CN = "projectNameCN";
    public static String PROJECT_NAME_EN = "projectNameEn";
    public static String PROJECT_NAME_KO = "projectNameKo";
    public static String PROJECT_NAME_JA = "projectNameJa";

    public static String INSTRUCTION_CN = "instructionCn";
    public static String INSTRUCTION_EN = "instructionEn";
    public static String INSTRUCTION_KO = "instructionEn";
    public static String INSTRUCTION_JA = "instructionEn";

    public static String CONTENT_CN = "contentCn";
    public static String CONTENT_EN = "contentEn";
    public static String CONTENT_KO = "contentKo";
    public static String CONTENT_JA = "contentJa";

    public static String PDF_EN = "pdfEn";
    public static String PDF_EN_NAME = "pdfEnName";
    public static String PDF_CN = "pdfCn";
    public static String PDF_CN_NAME = "pdfCnName";
    public static String PDF_KO = "pdfKo";
    public static String PDF_KO_NAME = "pdfKoName";
    public static String PDF_JA = "pdfJa";
    public static String PDF_JA_NAME = "pdfJaName";

    public static String WHITE_PAPER_LINK_CN = "whitePaperLinkCn";
    public static String WHITE_PAPER_LINK_EN = "whitePaperLinkEn";
    public static String WHITE_PAPER_LINK_JA = "whitePaperLinkJa";
    public static String WHITE_PAPER_LINK_KO = "whitePaperLinkko";

    public static String OFFICIAL_LINK = "officialLink";
    public static String TWITTER = "twitter";
    public static String FACEBOOK = "facebook";
    public static String TELEGRAM = "telegram";
    public static String REDDIT = "reddit";
    public static String BI_YONG = "biYong";
    public static String GIT_HUB = "gitHub";


    public static String TOKEN_ADDRESS = "tokenAddress";
    public static String PROJECT_ADDRESS = "projectAddress";
    public static String START_TIME = "startTime";
    public static String END_TIME = "endTime";
    public static String START_PRICE = "startPrice";
    public static String END_PRICE = "endPrice";
    public static String MIN_PURCHASE_AMOUNT = "minPurchaseAmount";
    public static String HARD_CAP = "hardCap";
    public static String SOFT_CAP = "softCap";

    public static String LOG = "log";

    public static String LOG_STR = "logStr";
    public static String VIEW = "view";

    public static String PLATFORM_ADDRESS = "platformAddress";
    public static String PICTURE_URL = "pictureUrl";

    private Long startTimeLong;
    private Long endTimeLong;


    private String projectNameEn;
    private String projectNameCn;
    private String projectNameKo;
    private String projectNameJa;


    private String log;
    private String logStr;

    private String pdfCn;
    private String pdfCnName;
    private String pdfEn;
    private String pdfEnName;
    private String pdfKo;
    private String pdfKoName;
    private String pdfJa;
    private String pdfJaName;

    private String view;

    private String instructionCn;
    private String instructionEn;
    private String instructionJa;
    private String instructionKo;

    private String contentCn;
    private String contentEn;
    private String contentKo;
    private String contentJa;

    private String whitePaperLinkCn;
    private String whitePaperLinkEn;
    private String whitePaperLinkKo;
    private String whitePaperLinkJa;

    private String gitHub;
    private String twitter;
    private String facebook;
    private String telegram;
    private String reddit;
    private String officialLink;
    private String biYong;

    private String projectName;
    private String objectName;
    private String token;
    private String whitePaperLink;
    private String projectContent;
    private Long idDb;


    private String queryStr;
    private String img;
    private Integer typeId;



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

    private List<WebSiteManagementBean> websiteList = new LinkedList<>();

    public SysProjectBean() {
    }

    public static SysProjectBean newInstance() {
        return new SysProjectBean();
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public SysProjectBean setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getProjectNameEn() {
        return projectNameEn;
    }

    public SysProjectBean setProjectNameEn(String projectNameEn) {
        this.projectNameEn = projectNameEn;
        return this;
    }

    public String getProjectNameCn() {
        return projectNameCn;
    }

    public SysProjectBean setProjectNameCn(String projectNameCn) {
        this.projectNameCn = projectNameCn;
        return this;
    }

    public String getProjectNameKo() {
        return projectNameKo;
    }

    public SysProjectBean setProjectNameKo(String projectNameKo) {
        this.projectNameKo = projectNameKo;
        return this;
    }

    public String getProjectNameJa() {
        return projectNameJa;
    }

    public SysProjectBean setProjectNameJa(String projectNameJa) {
        this.projectNameJa = projectNameJa;
        return this;
    }

    public String getLog() {
        return log;
    }

    public SysProjectBean setLog(String log) {
        this.log = log;
        return this;
    }

    public String getPdfCn() {
        return pdfCn;
    }

    public SysProjectBean setPdfCn(String pdfCn) {
        this.pdfCn = pdfCn;
        return this;
    }

    public String getPdfCnName() {
        return pdfCnName;
    }

    public SysProjectBean setPdfCnName(String pdfCnName) {
        this.pdfCnName = pdfCnName;
        return this;
    }

    public String getPdfEn() {
        return pdfEn;
    }

    public SysProjectBean setPdfEn(String pdfEn) {
        this.pdfEn = pdfEn;
        return this;
    }

    public String getPdfEnName() {
        return pdfEnName;
    }

    public SysProjectBean setPdfEnName(String pdfEnName) {
        this.pdfEnName = pdfEnName;
        return this;
    }

    public String getPdfKo() {
        return pdfKo;
    }

    public SysProjectBean setPdfKo(String pdfKo) {
        this.pdfKo = pdfKo;
        return this;
    }

    public String getPdfKoName() {
        return pdfKoName;
    }

    public SysProjectBean setPdfKoName(String pdfKoName) {
        this.pdfKoName = pdfKoName;
        return this;
    }

    public String getPdfJa() {
        return pdfJa;
    }

    public SysProjectBean setPdfJa(String pdfJa) {
        this.pdfJa = pdfJa;
        return this;
    }

    public String getPdfJaName() {
        return pdfJaName;
    }

    public SysProjectBean setPdfJaName(String pdfJaName) {
        this.pdfJaName = pdfJaName;
        return this;
    }

    public String getView() {
        return view;
    }

    public SysProjectBean setView(String view) {
        this.view = view;
        return this;
    }

    public String getInstructionCn() {
        return instructionCn;
    }

    public SysProjectBean setInstructionCn(String instructionCn) {
        this.instructionCn = instructionCn;
        return this;
    }

    public String getInstructionEn() {
        return instructionEn;
    }

    public SysProjectBean setInstructionEn(String instructionEn) {
        this.instructionEn = instructionEn;
        return this;
    }

    public String getInstructionJa() {
        return instructionJa;
    }

    public SysProjectBean setInstructionJa(String instructionJa) {
        this.instructionJa = instructionJa;
        return this;
    }

    public String getInstructionKo() {
        return instructionKo;
    }

    public SysProjectBean setInstructionKo(String instructionKo) {
        this.instructionKo = instructionKo;
        return this;
    }

    public String getContentCn() {
        return contentCn;
    }

    public SysProjectBean setContentCn(String contentCn) {
        this.contentCn = contentCn;
        return this;
    }

    public String getContentEn() {
        return contentEn;
    }

    public SysProjectBean setContentEn(String contentEn) {
        this.contentEn = contentEn;
        return this;
    }

    public String getContentKo() {
        return contentKo;
    }

    public SysProjectBean setContentKo(String contentKo) {
        this.contentKo = contentKo;
        return this;
    }

    public String getContentJa() {
        return contentJa;
    }

    public SysProjectBean setContentJa(String contentJa) {
        this.contentJa = contentJa;
        return this;
    }

    public String getWhitePaperLinkCn() {
        return whitePaperLinkCn;
    }

    public SysProjectBean setWhitePaperLinkCn(String whitePaperLinkCn) {
        this.whitePaperLinkCn = whitePaperLinkCn;
        return this;
    }

    public String getWhitePaperLinkEn() {
        return whitePaperLinkEn;
    }

    public SysProjectBean setWhitePaperLinkEn(String whitePaperLinkEn) {
        this.whitePaperLinkEn = whitePaperLinkEn;
        return this;
    }

    public String getWhitePaperLinkKo() {
        return whitePaperLinkKo;
    }

    public SysProjectBean setWhitePaperLinkKo(String whitePaperLinkKo) {
        this.whitePaperLinkKo = whitePaperLinkKo;
        return this;
    }

    public String getWhitePaperLinkJa() {
        return whitePaperLinkJa;
    }

    public SysProjectBean setWhitePaperLinkJa(String whitePaperLinkJa) {
        this.whitePaperLinkJa = whitePaperLinkJa;
        return this;
    }

    public String getGitHub() {
        return gitHub;
    }

    public SysProjectBean setGitHub(String gitHub) {
        this.gitHub = gitHub;
        return this;
    }

    public String getTwitter() {
        return twitter;
    }

    public SysProjectBean setTwitter(String twitter) {
        this.twitter = twitter;
        return this;
    }

    public String getFacebook() {
        return facebook;
    }

    public SysProjectBean setFacebook(String facebook) {
        this.facebook = facebook;
        return this;
    }

    public String getTelegram() {
        return telegram;
    }

    public SysProjectBean setTelegram(String telegram) {
        this.telegram = telegram;
        return this;
    }

    public String getReddit() {
        return reddit;
    }

    public SysProjectBean setReddit(String reddit) {
        this.reddit = reddit;
        return this;
    }

    public String getBiYong() {
        return biYong;
    }

    public SysProjectBean setBiYong(String biYong) {
        this.biYong = biYong;
        return this;
    }

    public String getProjectName() {
        return projectName;
    }

    public SysProjectBean setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public String getObjectName() {
        return objectName;
    }

    public SysProjectBean setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }

    public String getToken() {
        return token;
    }

    public SysProjectBean setToken(String token) {
        this.token = token;
        return this;
    }

    public String getWhitePaperLink() {
        return whitePaperLink;
    }

    public SysProjectBean setWhitePaperLink(String whitePaperLink) {
        this.whitePaperLink = whitePaperLink;
        return this;
    }

    public String getProjectContent() {
        return projectContent;
    }

    public SysProjectBean setProjectContent(String projectContent) {
        this.projectContent = projectContent;
        return this;
    }

    public Long getId() {
        return id;
    }

    public SysProjectBean setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProjectGid() {
        return projectGid;
    }

    public SysProjectBean setProjectGid(String projectGid) {
        this.projectGid = projectGid;
        return this;
    }

    public Long getIdDb() {
        return idDb;
    }

    public SysProjectBean setIdDb(Long idDb) {
        this.idDb = idDb;
        return this;
    }

    public String getOfficialLink() {
        return officialLink;
    }

    public SysProjectBean setOfficialLink(String officialLink) {
        this.officialLink = officialLink;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public SysProjectBean setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public SysProjectBean setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getQueryStr() {
        return queryStr;
    }

    public SysProjectBean setQueryStr(String queryStr) {
        this.queryStr = queryStr;
        return this;
    }

    public String getImg() {
        return img;
    }

    public SysProjectBean setImg(String img) {
        this.img = img;
        return this;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public SysProjectBean setTypeId(Integer typeId) {
        this.typeId = typeId;
        return this;
    }

    public List<WebSiteManagementBean> getWebsiteList() {
        return websiteList;
    }

    public SysProjectBean setWebsiteList(List<WebSiteManagementBean> websiteList) {
        this.websiteList = websiteList;
        return this;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public SysProjectBean setStartTime(Timestamp startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getTokenAddress() {
        return tokenAddress;
    }

    public SysProjectBean setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
        return this;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public SysProjectBean setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
        return this;
    }

    public BigDecimal getPriceRate() {
        return priceRate;
    }

    public SysProjectBean setPriceRate(BigDecimal priceRate) {
        this.priceRate = priceRate;
        return this;
    }

    public BigDecimal getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public SysProjectBean setMinPurchaseAmount(BigDecimal minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
        return this;
    }

    public BigDecimal getHardCap() {
        return hardCap;
    }

    public SysProjectBean setHardCap(BigDecimal hardCap) {
        this.hardCap = hardCap;
        return this;
    }

    public BigDecimal getSoftCap() {
        return softCap;
    }

    public SysProjectBean setSoftCap(BigDecimal softCap) {
        this.softCap = softCap;
        return this;
    }

    public Long getStartTimeLong() {
        return startTimeLong;
    }

    public SysProjectBean setStartTimeLong(Long startTimeLong) {
        this.startTimeLong = startTimeLong;
        return this;
    }

    public Long getEndTimeLong() {
        return endTimeLong;
    }

    public SysProjectBean setEndTimeLong(Long endTimeLong) {
        this.endTimeLong = endTimeLong;
        return this;
    }

    public String getLogStr() {
        return logStr;
    }

    public SysProjectBean setLogStr(String logStr) {
        this.logStr = logStr;
        return this;
    }

    public String getProjectToken() {
        return projectToken;
    }

    public SysProjectBean setProjectToken(String projectToken) {
        this.projectToken = projectToken;
        return this;
    }

    public String getPlatformAddress() {
        return platformAddress;
    }

    public SysProjectBean setPlatformAddress(String platformAddress) {
        this.platformAddress = platformAddress;
        return this;
    }

    public String getProjectLogoLink() {
        return projectLogoLink;
    }

    public SysProjectBean setProjectLogoLink(String projectLogoLink) {
        this.projectLogoLink = projectLogoLink;
        return this;
    }

    public String getProjectImgLink() {
        return projectImgLink;
    }

    public SysProjectBean setProjectImgLink(String projectImgLink) {
        this.projectImgLink = projectImgLink;
        return this;
    }


    public Integer getProjectStatus() {
        return projectStatus;
    }

    public SysProjectBean setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
        return this;
    }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public SysProjectBean setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
        return this;
    }

    public SysProjectBean setTime(Timestamp timestamp) {
        this.createTime = timestamp;
        this.updateTime = timestamp;
        return this;
    }


}