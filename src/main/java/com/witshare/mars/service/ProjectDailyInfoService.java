package com.witshare.mars.service;

import com.witshare.mars.pojo.dto.ProjectDailyInfoBean;

import java.math.BigDecimal;
import java.sql.Timestamp;


public interface ProjectDailyInfoService {

    ProjectDailyInfoBean get(String projectGid, Timestamp timestamp);

    BigDecimal getPrice(String projectGid, Timestamp timestamp);

    int saveOrUpdate(String projectGid, Timestamp dateTime);
}
