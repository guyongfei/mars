package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.dto.SysProjectBean;
import com.witshare.mars.pojo.dto.WebSiteManagementBean;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface StaticProjectWebsiteMapper {
    Long save(SysProjectBean sysProjectBean);

    List<Map<String, Object>> selectWebSiteList(@Param("projectGid") String projectGid, @Param("linkCategory") Integer linkCategory);

    List<WebSiteManagementBean> selectManagementWebsite(Long id);

    int saveOrUpdate(LinkedList list);
}