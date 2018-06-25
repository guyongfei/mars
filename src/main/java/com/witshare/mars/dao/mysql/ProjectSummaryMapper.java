package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.domain.ProjectSummary;
import com.witshare.mars.pojo.domain.ProjectSummaryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectSummaryMapper {
    int countByExample(ProjectSummaryExample example);

    int deleteByExample(ProjectSummaryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProjectSummary record);

    int insertSelective(ProjectSummary record);

    List<ProjectSummary> selectByExample(ProjectSummaryExample example);

    ProjectSummary selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProjectSummary record, @Param("example") ProjectSummaryExample example);

    int updateByExample(@Param("record") ProjectSummary record, @Param("example") ProjectSummaryExample example);

    int updateByPrimaryKeySelective(ProjectSummary record);

    int updateByPrimaryKey(ProjectSummary record);
}