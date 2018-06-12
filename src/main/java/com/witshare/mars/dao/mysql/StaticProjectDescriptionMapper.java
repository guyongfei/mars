package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.dto.SysProjectBean;

public interface StaticProjectDescriptionMapper {

    Long saveOrUpdate(SysProjectBean sysProjectBean);

}