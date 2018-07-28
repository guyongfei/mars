package com.witshare.mars.pojo.vo;

import java.sql.Timestamp;

public class SysChannelVo {

    private Long id;
    private String  channelGid;

    private String  channelName;

    private String name;

    private String channel;

    private String note;

    private Integer registerCount;
    private Integer totalRegisterCount;

    private Timestamp createTime;

    private Timestamp updateTime;

    public SysChannelVo() {
    }

    public static SysChannelVo newInstance() {
        return new SysChannelVo();
    }

    public Long getId() {
        return id;
    }

    public SysChannelVo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SysChannelVo setName(String name) {
        this.name = name;
        return this;
    }

    public String getChannel() {
        return channel;
    }

    public SysChannelVo setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public String getNote() {
        return note;
    }

    public SysChannelVo setNote(String note) {
        this.note = note;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public SysChannelVo setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public SysChannelVo setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getRegisterCount() {
        return registerCount;
    }

    public SysChannelVo setRegisterCount(Integer registerCount) {
        this.registerCount = registerCount;
        return this;
    }

    public Integer getTotalRegisterCount() {
        return totalRegisterCount;
    }

    public SysChannelVo setTotalRegisterCount(Integer totalRegisterCount) {
        this.totalRegisterCount = totalRegisterCount;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public SysChannelVo setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }


    public String getChannelGid() {
        return channelGid;
    }

    public SysChannelVo setChannelGid(String channelGid) {
        this.channelGid = channelGid;
        return this;
    }
}