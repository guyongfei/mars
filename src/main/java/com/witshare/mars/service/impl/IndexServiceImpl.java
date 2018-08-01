package com.witshare.mars.service.impl;

import com.witshare.mars.constant.PropertiesConfig;
import com.witshare.mars.pojo.vo.IndexBean;
import com.witshare.mars.pojo.vo.SysProjectBeanFrontInfoVo;
import com.witshare.mars.service.IndexService;
import com.witshare.mars.service.SysProjectService;
import com.witshare.mars.util.WitshareUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private SysProjectService sysProjectService;
    private static final Logger LOG = LoggerFactory.getLogger(ExportServiceImpl.class);
    @Autowired
    private PropertiesConfig propertiesConfig;

    @Override
    public IndexBean get() {
        SysProjectBeanFrontInfoVo defaultProject = null;
        try {
            defaultProject = sysProjectService.getDefaultProject();
            //计算免费赠送的结束时间
            Timestamp startTime = defaultProject.getStartTime();
            Timestamp periodEndTime = WitshareUtils.getPeriodEndTime(startTime, propertiesConfig.freeGivePeriod);
            defaultProject.setFreeGiveRate(propertiesConfig.freeGiveRate);
            defaultProject.setFreeGiveEnd(periodEndTime);
        } catch (Exception e) {
            LOG.info("defaultProject is null.", e);
        }
        return IndexBean.newInstance().setDefaultProject(defaultProject);
    }
}
