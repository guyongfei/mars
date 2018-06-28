package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.domain.RecordUserTx;
import com.witshare.mars.pojo.domain.RecordUserTxExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordUserTxMapper {
    int countByExample(RecordUserTxExample example);

    int deleteByExample(RecordUserTxExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RecordUserTx record);

    int insertSelective(RecordUserTx record);

    List<RecordUserTx> selectByExample(RecordUserTxExample example);

    RecordUserTx selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RecordUserTx record, @Param("example") RecordUserTxExample example);

    int updateByExample(@Param("record") RecordUserTx record, @Param("example") RecordUserTxExample example);

    int updateByPrimaryKeySelective(RecordUserTx record);

    int updateByPrimaryKey(RecordUserTx record);
}