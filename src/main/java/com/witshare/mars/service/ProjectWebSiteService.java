package com.witshare.mars.service;

import com.witshare.mars.pojo.dto.SysProjectBean;

import java.util.Map;

/**
 * Created by user on 2018/6/12.
 */
public interface ProjectWebSiteService {


//    int saveOrUpdate(LinkedList<ProjectWebsiteBean> projectWebsiteBeans);

    int saveOrUpdate(SysProjectBean sysProjectBean);

    Map<String, String> select(String projectGid);
}
