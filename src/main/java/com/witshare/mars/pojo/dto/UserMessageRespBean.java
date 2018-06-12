package com.witshare.mars.pojo.dto;

import java.sql.Timestamp;

public class UserMessageRespBean {

    public static final Integer POSITION_LEFT = 0;

    public static final Integer POSITION_RIGHT = 1;

    private Long id;


    private String messageContent;


    private Long fromUserId;


    private Long toUserId;


    private Integer messageStatus;


    private Timestamp createTime;


    private Timestamp todayZeroTime;


    private Integer position;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Timestamp getTodayZeroTime() {
        return todayZeroTime;
    }

    public void setTodayZeroTime(Timestamp todayZeroTime) {
        this.todayZeroTime = todayZeroTime;
    }
}