package com.witshare.mars.service.impl;

import com.witshare.mars.pojo.vo.IndexBean;
import com.witshare.mars.pojo.vo.SysProjectBeanFrontInfoVo;
import com.witshare.mars.service.IndexService;
import com.witshare.mars.service.SysProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private SysProjectService sysProjectService;
    private static final Logger LOG = LoggerFactory.getLogger(ExportServiceImpl.class);

    @Override
    public IndexBean get() {
        SysProjectBeanFrontInfoVo defaultProject = null;
        try {
            defaultProject = sysProjectService.getDefaultProject();
        } catch (Exception e) {
            LOG.info("defaultProject is null.", e);
        }
        return IndexBean.newInstance().setDefaultProject(defaultProject);
    }
}
