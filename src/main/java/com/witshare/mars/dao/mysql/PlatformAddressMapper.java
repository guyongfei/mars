package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.domain.PlatformAddress;
import com.witshare.mars.pojo.domain.PlatformAddressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlatformAddressMapper {
    int countByExample(PlatformAddressExample example);

    int deleteByExample(PlatformAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PlatformAddress record);

    int insertSelective(PlatformAddress record);

    List<PlatformAddress> selectByExample(PlatformAddressExample example);

    PlatformAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PlatformAddress record, @Param("example") PlatformAddressExample example);

    int updateByExample(@Param("record") PlatformAddress record, @Param("example") PlatformAddressExample example);

    int updateByPrimaryKeySelective(PlatformAddress record);

    int updateByPrimaryKey(PlatformAddress record);
}