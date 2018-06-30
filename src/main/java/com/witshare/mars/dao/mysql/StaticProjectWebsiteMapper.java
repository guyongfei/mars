package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.dto.SysProjectBean;

public interface StaticProjectWebsiteMapper {
    int saveOrUpdate(SysProjectBean sysProjectBean);
}