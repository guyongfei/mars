package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.domain.SysProjectChannel;
import com.witshare.mars.pojo.domain.SysProjectChannelExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysProjectChannelMapper {
    int countByExample(SysProjectChannelExample example);

    int deleteByExample(SysProjectChannelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysProjectChannel record);

    int insertSelective(SysProjectChannel record);

    List<SysProjectChannel> selectByExample(SysProjectChannelExample example);

    SysProjectChannel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysProjectChannel record, @Param("example") SysProjectChannelExample example);

    int updateByExample(@Param("record") SysProjectChannel record, @Param("example") SysProjectChannelExample example);

    int updateByPrimaryKeySelective(SysProjectChannel record);

    int updateByPrimaryKey(SysProjectChannel record);
}