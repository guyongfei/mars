package com.witshare.mars.service;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.domain.SysProject;
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
     * 前台获取列表
     */
    PageInfo<SysProjectBeanFrontListVo> selectSysProjects(ProjectReqBean projectReqBean);

    /**
     * 前台获取详情
     */
    SysProjectBeanFrontInfoVo selectProject(String projectGid);

    /**
     * 前台获取默认项目
     */
    SysProjectBeanFrontInfoVo getDefaultProject();

    /**
     * 管理页面获取项目列表
     */
    PageInfo<SysProjectListVo> selectManagementList(SysProjectBean sysProjectBean);

    /**
     * 管理页面获取单个项目详情
     */
    SysProjectBeanVo selectManagementByProjectGid(String projectGid);

    /**
     * 新增项目
     */
    void save(String requestBody, String platformAddress);

    /**
     * 更新项目
     */
    void update(String requestBody);

    /**
     * 隐藏单个项目
     */
    void hideProject(String projectGid);

    /**
     * 设置首页项目
     */
    void defaultProject(String projectGid);

    /**
     * 修改项目状态
     */
    void projectStatus(String projectGid, Integer projectStatus);

    /**
     * 查询单个项目
     */
    SysProjectBean selectByProjectGid(String projectGid);

    /**
     * 删除单个项目缓存
     */
    void deleteProjectCache(String projectGid);

    /**
     * 获取可访问连接地址
     */
    String getPictureUrl(String source);

    /**
     * 获取前端的状态，0-未开始，1-已开始，3-成功
     */
    int getFrontProjectStatus(int status);

    /**
     * 实时更改项目状态
     */
    void updateProjectStatus(SysProject sysProjectBean);


}
