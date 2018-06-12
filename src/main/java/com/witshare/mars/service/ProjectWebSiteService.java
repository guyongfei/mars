package com.witshare.mars.service;

import com.witshare.mars.pojo.dto.ProjectWebsiteBean;
import com.witshare.mars.pojo.dto.WebSiteManagementBean;

import java.util.LinkedList;

/**
 * Created by user on 2018/6/12.
 */
public interface ProjectWebSiteService {

    LinkedList<WebSiteManagementBean> select(String projectGid);

    int saveOrUpdate(LinkedList<ProjectWebsiteBean> projectWebsiteBeans);
}
