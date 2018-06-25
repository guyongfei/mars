package com.witshare.mars.pojo.dto;

import java.util.Date;

public class ProjectDailyInfoBean extends ProjectSummaryBean {


    private Date currentDay;

    public static ProjectDailyInfoBean newInstance() {
        return new ProjectDailyInfoBean();
    }

    public Date getCurrentDay() {
        return currentDay;
    }

    public ProjectDailyInfoBean setCurrentDay(Date currentDay) {
        this.currentDay = currentDay;
        return this;
    }
}