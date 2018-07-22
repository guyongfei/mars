package com.witshare.mars.pojo.dto;

/**
 * Created by user on 2018/7/21.
 */
public class SyncChannelRegisterCount {

    private String channel;
    private String channelName;
    private Integer registerCount;

    public String getChannel() {
        return channel;
    }

    public SyncChannelRegisterCount setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public Integer getRegisterCount() {
        return registerCount;
    }

    public SyncChannelRegisterCount setRegisterCount(Integer registerCount) {
        this.registerCount = registerCount;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public SyncChannelRegisterCount setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }
}
