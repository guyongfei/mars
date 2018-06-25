package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.domain.ProjectDailyInfo;
import com.witshare.mars.pojo.domain.ProjectDailyInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectDailyInfoMapper {
    int countByExample(ProjectDailyInfoExample example);

    int deleteByExample(ProjectDailyInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProjectDailyInfo record);

    int insertSelective(ProjectDailyInfo record);

    List<ProjectDailyInfo> selectByExample(ProjectDailyInfoExample example);

    ProjectDailyInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProjectDailyInfo record, @Param("example") ProjectDailyInfoExample example);

    int updateByExample(@Param("record") ProjectDailyInfo record, @Param("example") ProjectDailyInfoExample example);

    int updateByPrimaryKeySelective(ProjectDailyInfo record);

    int updateByPrimaryKey(ProjectDailyInfo record);
}