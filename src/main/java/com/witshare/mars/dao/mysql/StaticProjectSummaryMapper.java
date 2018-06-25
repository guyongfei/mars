package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.dto.ProjectSummaryBean;

import java.util.List;

public interface StaticProjectSummaryMapper {

    int saveOrUpdate(List<ProjectSummaryBean> collection);
}