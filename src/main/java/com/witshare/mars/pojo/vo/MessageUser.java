package com.witshare.mars.pojo.vo;

import java.sql.Timestamp;


public class MessageUser {

    private Long id;

    private String nickname;

    private String headImgUrl;

    private Integer noReadCount;

    private String theLastMessage;

    private Timestamp theLastTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Integer getNoReadCount() {
        return noReadCount;
    }

    public void setNoReadCount(Integer noReadCount) {
        this.noReadCount = noReadCount;
    }

    public String getTheLastMessage() {
        return theLastMessage;
    }

    public void setTheLastMessage(String theLastMessage) {
        this.theLastMessage = theLastMessage;
    }

    public Timestamp getTheLastTime() {
        return theLastTime;
    }

    public void setTheLastTime(Timestamp theLastTime) {
        this.theLastTime = theLastTime;
    }
}
