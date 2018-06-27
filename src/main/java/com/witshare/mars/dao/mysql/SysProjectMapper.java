package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.domain.SysProject;
import com.witshare.mars.pojo.domain.SysProjectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysProjectMapper {
    int countByExample(SysProjectExample example);

    int deleteByExample(SysProjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysProject record);

    int insertSelective(SysProject record);

    List<SysProject> selectByExample(SysProjectExample example);

    SysProject selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysProject record, @Param("example") SysProjectExample example);

    int updateByExample(@Param("record") SysProject record, @Param("example") SysProjectExample example);

    int updateByPrimaryKeySelective(SysProject record);

    int updateByPrimaryKey(SysProject record);
}