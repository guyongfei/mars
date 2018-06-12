package com.witshare.mars.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.witshare.mars.dao.mysql.ProjectWebsiteMapper;
import com.witshare.mars.dao.mysql.StaticProjectWebsiteMapper;
import com.witshare.mars.pojo.domain.ProjectWebsite;
import com.witshare.mars.pojo.domain.ProjectWebsiteExample;
import com.witshare.mars.pojo.dto.ProjectWebsiteBean;
import com.witshare.mars.pojo.dto.WebSiteManagementBean;
import com.witshare.mars.service.ProjectWebSiteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProjectWebSiteServiceImpl implements ProjectWebSiteService {

    @Autowired
    private ProjectWebsiteMapper projectWebsiteMapper;
    @Autowired
    private StaticProjectWebsiteMapper staticProjectWebsiteMapper;

    @Override
    public LinkedList<WebSiteManagementBean> select(String projectGid) {
        if (StringUtils.isEmpty(projectGid)) {
            return null;
        }
        ProjectWebsiteExample projectWebsiteExample = new ProjectWebsiteExample();
        projectWebsiteExample.or().andProjectGidEqualTo(projectGid);
        List<ProjectWebsite> projectWebsites = projectWebsiteMapper.selectByExample(projectWebsiteExample);
        if (CollectionUtils.isEmpty(projectWebsites)) {
            return null;
        }
        LinkedList<WebSiteManagementBean> webSiteManagementBeans = new LinkedList<>();
        projectWebsites.forEach(p -> {
            WebSiteManagementBean webSiteManagementBean = WebSiteManagementBean.newInstance().setLinkUrl(p.getLinkUrl()).setWebsiteType(p.getWebsiteType());
            webSiteManagementBeans.add(webSiteManagementBean);
        });
        return webSiteManagementBeans;
    }


    @Override
    public int saveOrUpdate(LinkedList<ProjectWebsiteBean> projectWebsiteBeans) {
        if (CollectionUtils.isEmpty(projectWebsiteBeans)) {
            return 0;
        }
        return staticProjectWebsiteMapper.saveOrUpdate(projectWebsiteBeans);
    }


}
