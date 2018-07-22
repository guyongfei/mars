package com.witshare.mars.pojo.dto;

import java.util.Date;

public class ProjectStatisticBean extends ProjectSummaryBean {

    private Date currentDay;
    private String action;
    private String channel;
    private String channelName;

    public static ProjectStatisticBean newInstance() {
        return new ProjectStatisticBean();
    }

    public Date getCurrentDay() {
        return currentDay;
    }

    public ProjectStatisticBean setCurrentDay(Date currentDay) {
        this.currentDay = currentDay;
        return this;
    }

    public String getAction() {
        return action;
    }

    public ProjectStatisticBean setAction(String action) {
        this.action = action;
        return this;
    }

    public String getChannel() {
        return channel;
    }

    public ProjectStatisticBean setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public ProjectStatisticBean setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }
}