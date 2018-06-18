package com.witshare.mars.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.witshare.mars.dao.mysql.ProjectWebsiteMapper;
import com.witshare.mars.dao.mysql.StaticProjectWebsiteMapper;
import com.witshare.mars.pojo.domain.ProjectWebsite;
import com.witshare.mars.pojo.domain.ProjectWebsiteExample;
import com.witshare.mars.pojo.dto.SysProjectBean;
import com.witshare.mars.service.ProjectWebSiteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectWebSiteServiceImpl implements ProjectWebSiteService {

    @Autowired
    private ProjectWebsiteMapper projectWebsiteMapper;
    @Autowired
    private StaticProjectWebsiteMapper staticProjectWebsiteMapper;

    @Override
    public int saveOrUpdate(SysProjectBean sysProjectBean) {
        if (sysProjectBean == null) {
            return 0;
        }
        return staticProjectWebsiteMapper.saveOrUpdate(sysProjectBean);
    }

    @Override
    public Map<String, String> select(String projectGid) {
        if (StringUtils.isEmpty(projectGid)) {
            return null;
        }
        ProjectWebsiteExample projectWebsiteExample = new ProjectWebsiteExample();
        projectWebsiteExample.or().andProjectGidEqualTo(projectGid);
        List<ProjectWebsite> projectWebsites = projectWebsiteMapper.selectByExample(projectWebsiteExample);
        if (CollectionUtils.isNotEmpty(projectWebsites)) {
            HashMap<String, String> map = new HashMap<>();
            projectWebsites.forEach(p -> {
                map.put(p.getWebsiteType(), p.getLinkUrl());
            });
            return map;
        }
        return null;
    }


}
