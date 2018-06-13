package com.witshare.mars.service;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.ProjectPageBean;
import com.witshare.mars.pojo.dto.ProjectReqBean;
import com.witshare.mars.pojo.dto.ProjectRespBean;
import com.witshare.mars.pojo.dto.SysProjectBean;
import com.witshare.mars.pojo.vo.SysProjectBeanVo;
import com.witshare.mars.pojo.vo.SysProjectListVo;

import java.util.List;
import java.util.Map;

/**
 * 项目详情实现类
 **/
public interface SysProjectService {
    /**
     * 获取首页展示项目
     *
     * @return
     */
    List<ProjectPageBean> getStarProjects();

    /**
     * 分页获取项目列表
     *
     * @param projectReqBean
     * @return
     */
    PageInfo<ProjectPageBean> selectSysProjects(ProjectReqBean projectReqBean);


    ProjectRespBean selectProject(String projectGid);


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
    void update(Map<String, Object> requestBody);

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
     * @param id
     * @return
     */
    SysProjectBeanVo selectManagementById(Long id);


    /**
     * 获取项目评级报告pdf
     *
     * @param projectId
     */
    void getProjectPdf(Long projectId);


    /**
     * 清除所有项目缓存
     */
    void delAllProjectCache();

    void hideProject(Long id);

}
