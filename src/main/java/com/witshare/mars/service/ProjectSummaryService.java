package com.witshare.mars.service;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.ProjectSummaryBean;

/**
 * Created by user on 2018/6/28.
 */
public interface ProjectSummaryService {

    PageInfo<ProjectSummaryBean> getList(ProjectSummaryBean projectSummaryBean);
}
