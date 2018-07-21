package com.witshare.mars.pojo.dto;

import java.sql.Timestamp;

public class SysChannelBean extends BasePageBean {

    private Long id;

    private String name;

    private String channel;

    private String note;

    private Timestamp createTime;

    private Timestamp updateTime;

    public SysChannelBean() {
    }

    public static SysChannelBean newInstance() {
        return new SysChannelBean();
    }

    public Long getId() {
        return id;
    }

    public SysChannelBean setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SysChannelBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getChannel() {
        return channel;
    }

    public SysChannelBean setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public String getNote() {
        return note;
    }

    public SysChannelBean setNote(String note) {
        this.note = note;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public SysChannelBean setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public SysChannelBean setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}