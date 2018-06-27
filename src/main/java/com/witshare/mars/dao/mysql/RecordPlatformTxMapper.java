package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.domain.RecordPlatformTx;
import com.witshare.mars.pojo.domain.RecordPlatformTxExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordPlatformTxMapper {
    int countByExample(RecordPlatformTxExample example);

    int deleteByExample(RecordPlatformTxExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RecordPlatformTx record);

    int insertSelective(RecordPlatformTx record);

    List<RecordPlatformTx> selectByExample(RecordPlatformTxExample example);

    RecordPlatformTx selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RecordPlatformTx record, @Param("example") RecordPlatformTxExample example);

    int updateByExample(@Param("record") RecordPlatformTx record, @Param("example") RecordPlatformTxExample example);

    int updateByPrimaryKeySelective(RecordPlatformTx record);

    int updateByPrimaryKey(RecordPlatformTx record);
}