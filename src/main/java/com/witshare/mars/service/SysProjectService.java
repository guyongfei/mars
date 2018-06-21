package com.witshare.mars.service;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.ProjectReqBean;
import com.witshare.mars.pojo.dto.SysProjectBean;
import com.witshare.mars.pojo.vo.SysProjectBeanFrontInfoVo;
import com.witshare.mars.pojo.vo.SysProjectBeanFrontListVo;
import com.witshare.mars.pojo.vo.SysProjectBeanVo;
import com.witshare.mars.pojo.vo.SysProjectListVo;

/**
 * 项目详情实现类
 **/
public interface SysProjectService {

    /**
     * 分页获取项目列表
     *
     * @param projectReqBean
     * @return
     */
    PageInfo<SysProjectBeanFrontListVo> selectSysProjects(ProjectReqBean projectReqBean);


    SysProjectBeanFrontInfoVo selectProject(String projectGid);


    /**
     * 新增项目
     *
     * @param requestBody
     */
    void save(String requestBody);

    /**
     * 更新项目
     *
     * @param requestBody
     */
    void update(String requestBody);

    /**
     * 获取可访问连接地址
     *
     * @param source
     * @return
     */
    String getPictureUrl(String source);


    /**
     * 管理页面获取项目列表
     *
     * @param sysProjectBean
     * @return
     */
    PageInfo<SysProjectListVo> selectManagementList(SysProjectBean sysProjectBean);

    /**
     * 管理页面获取单个项目详情
     *
     * @return
     */
    SysProjectBeanVo selectManagementByProjectGid(String projectGid);

    /**
     * 清除所有项目缓存
     */
    void delAllProjectCache();

    void hideProject(String projectGid);

    SysProjectBean selectByProjectGid(String projectGid);


}
