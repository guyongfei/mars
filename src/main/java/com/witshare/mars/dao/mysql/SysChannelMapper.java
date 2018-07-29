package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.domain.SysChannel;
import com.witshare.mars.pojo.domain.SysChannelExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysChannelMapper {
    int countByExample(SysChannelExample example);

    int deleteByExample(SysChannelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysChannel record);

    int insertSelective(SysChannel record);

    List<SysChannel> selectByExample(SysChannelExample example);

    SysChannel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysChannel record, @Param("example") SysChannelExample example);

    int updateByExample(@Param("record") SysChannel record, @Param("example") SysChannelExample example);

    int updateByPrimaryKeySelective(SysChannel record);

    int updateByPrimaryKey(SysChannel record);
}