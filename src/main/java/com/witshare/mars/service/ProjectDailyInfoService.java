package com.witshare.mars.service;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.ProjectDailyInfoBean;
import com.witshare.mars.pojo.dto.ProjectSummaryBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by user on 2018/6/25.
 */
public interface ProjectDailyInfoService {

    BigDecimal getSoldAmount(String projectGid);

    ProjectSummaryBean getSummary(String projectGid);

    PageInfo<ProjectDailyInfoBean> getList(ProjectDailyInfoBean projectDailyInfoBean);

    PageInfo<ProjectDailyInfoBean> getList(String projectGid);

    ProjectDailyInfoBean get(String projectGid, Date date);

    void syncDailyInfo();
}
