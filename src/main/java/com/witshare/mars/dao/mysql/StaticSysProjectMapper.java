package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.dto.*;
import com.witshare.mars.pojo.vo.SysProjectBeanFrontListVo;
import com.witshare.mars.pojo.vo.SysProjectBeanVo;
import com.witshare.mars.pojo.vo.SysProjectListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface StaticSysProjectMapper {

    int checkExist(SysProjectBean sysProjectBean);

    int saveOrUpdate(SysProjectBean sysProjectBean);

    List<ConfProjectTypeBean> selectProjectType();

    List<SysProjectBeanFrontListVo> selectProjectList(ProjectReqBean projectReqBean);

    List<SysProjectListVo> selectManagementList(SysProjectBean sysProjectBean);

    SysProjectBeanVo selectManagementById(Long id);

    int modifyProjectStatus(Long id);

    int modifyDefaultProject(Long id);

    List<ProjectPageBean> selectMyProjects(@Param("tableName") String tableName, @Param("userId") Long userId);

    List<GlobalSimpleBean> selectSimpleProjectList(@Param("indexShow") Integer indexShow, @Param("name") String name);

    int updateStarProject(List<Long> ids);

    ProjectPdfBean selectPdfById(@Param("tableName") String tableName, @Param("projectId") Long projectId);

    Set<Long> getStarProjectIds();

    int updateProjectFollowCommentCount(@Param("projectId") Long projectId, @Param("followCount") Integer followCount, @Param("commentCount") Integer commentCount);

    List<String> selectAllProjectId(@Param("offSet") Integer offSet, @Param("limit") Integer limit);

}