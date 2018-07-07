package com.witshare.mars.pojo.dto;

import java.sql.Timestamp;

public class SysUserBean {
    public static final String NICKNAME = "nickname";
    public static final String USER_GID = "userGid";
    public static final String PASSWORD = "password";
    public static final String ORIGIN_PASSWORD = "originPassword";
    public static final String EMAIL = "email";
    public static final String VERIFY_CODE = "verifyCode";
    public static final String IMG_VERIFY_CODE = "imgVerifyCode";
    public static final String IMG_TOKEN = "imgToken";
    public static final String EMAIL_LIKE = "emailLike";
    public static final String START = "start";
    public static final String AVATAR = "avatar";
    public static final String HEAD_IMG_URL = "headImgUrl";
    public static final String IS_MYSELF = "isMyself";
    public static final String PAY_ETH_ADDRESS = "payEthAddress";
    public static final String GET_TOKEN_ADDRESS = "getTokenAddress";

    private Integer pageNum;
    private Integer pageSize;
    private String emailLike;
    private String start;
    private String headImgUrl;
    private String userGid;
    private boolean admin;
    private String managementPage;


    private Long id;
    private String email;
    private String nickname;
    private String userPassword;
    private String salt;
    private Integer starUser;
    private Integer projectNum;
    private Integer commentNum;
    private Integer userStatus;
    private Timestamp createTime;
    private Timestamp updateTime;

    private SysUserBean() {
    }

    public SysUserBean(String email, String nickname, String headImgUrl) {
        this.email = email;
        this.nickname = nickname;
        this.headImgUrl = headImgUrl;
    }


    public SysUserBean(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public static SysUserBean newInstance() {
        return new SysUserBean();
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public SysUserBean setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public SysUserBean setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String getEmailLike() {
        return emailLike;
    }

    public SysUserBean setEmailLike(String emailLike) {
        this.emailLike = emailLike;
        return this;
    }

    public String getStart() {
        return start;
    }

    public SysUserBean setStart(String start) {
        this.start = start;
        return this;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public SysUserBean setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
        return this;
    }

    public String getUserGid() {
        return userGid;
    }

    public SysUserBean setUserGid(String userGid) {
        this.userGid = userGid;
        return this;
    }

    public boolean isAdmin() {
        return admin;
    }

    public SysUserBean setAdmin(boolean admin) {
        this.admin = admin;
        return this;
    }

    public String getManagementPage() {
        return managementPage;
    }

    public SysUserBean setManagementPage(String managementPage) {
        this.managementPage = managementPage;
        return this;
    }

    public Long getId() {
        return id;
    }

    public SysUserBean setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SysUserBean setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public SysUserBean setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public SysUserBean setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public SysUserBean setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public Integer getStarUser() {
        return starUser;
    }

    public SysUserBean setStarUser(Integer starUser) {
        this.starUser = starUser;
        return this;
    }

    public Integer getProjectNum() {
        return projectNum;
    }

    public SysUserBean setProjectNum(Integer projectNum) {
        this.projectNum = projectNum;
        return this;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public SysUserBean setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
        return this;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public SysUserBean setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public SysUserBean setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public SysUserBean setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}