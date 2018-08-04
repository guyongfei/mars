package com.witshare.mars.service;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.ProjectStatisticBean;
import com.witshare.mars.pojo.dto.ProjectSummaryBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by user on 2018/6/25.
 */
public interface ProjectStatisticService {

    BigDecimal getSoldAmount(String projectGid);

    ProjectSummaryBean getSummary(String projectGid);

    PageInfo<ProjectStatisticBean> getList(ProjectStatisticBean projectStatisticBean);

    PageInfo<ProjectStatisticBean> getList(String projectGid);

    PageInfo<ProjectStatisticBean> getChannelList(String projectGid);

    ProjectStatisticBean get(String projectGid, Date date);

    void syncDailyInfo();

    void syncSummaryToken();

    ProjectSummaryBean getSummaryToken(String projectGid);
}
