package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.dto.ProjectDailyInfoBean;

import java.util.List;

public interface StaticProjectDailyInfoMapper {

    int saveOrUpdate(List<ProjectDailyInfoBean> collection);

}