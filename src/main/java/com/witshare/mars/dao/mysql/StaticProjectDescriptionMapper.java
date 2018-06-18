package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.dto.ProjectDescriptionBean;
import com.witshare.mars.pojo.dto.SysProjectBean;
import org.apache.ibatis.annotations.Param;

public interface StaticProjectDescriptionMapper {

    Long saveOrUpdate(SysProjectBean sysProjectBean);

    ProjectDescriptionBean selectByTableName(@Param("tableName") String tableName, @Param("projectGid") String projectGid);

}