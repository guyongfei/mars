package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.domain.ProjectChannelStatistic;
import com.witshare.mars.pojo.domain.ProjectChannelStatisticExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectChannelStatisticMapper {
    int countByExample(ProjectChannelStatisticExample example);

    int deleteByExample(ProjectChannelStatisticExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProjectChannelStatistic record);

    int insertSelective(ProjectChannelStatistic record);

    List<ProjectChannelStatistic> selectByExample(ProjectChannelStatisticExample example);

    ProjectChannelStatistic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProjectChannelStatistic record, @Param("example") ProjectChannelStatisticExample example);

    int updateByExample(@Param("record") ProjectChannelStatistic record, @Param("example") ProjectChannelStatisticExample example);

    int updateByPrimaryKeySelective(ProjectChannelStatistic record);

    int updateByPrimaryKey(ProjectChannelStatistic record);
}