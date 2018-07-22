package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.dto.ProjectStatisticBean;

import java.util.List;

public interface StaticProjectStatisticInfoMapper {

    int saveOrUpdateDaily(List<ProjectStatisticBean> collection);

    int saveOrUpdateChannel(List<ProjectStatisticBean> collection);

}