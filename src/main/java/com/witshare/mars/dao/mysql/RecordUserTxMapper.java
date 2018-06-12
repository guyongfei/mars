package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.domain.RecordUserTx;
import com.witshare.mars.pojo.domain.RecordUserTxExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordUserTxMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record_user_tx
     *
     * @mbggenerated
     */
    int countByExample(RecordUserTxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record_user_tx
     *
     * @mbggenerated
     */
    int deleteByExample(RecordUserTxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record_user_tx
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record_user_tx
     *
     * @mbggenerated
     */
    int insert(RecordUserTx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record_user_tx
     *
     * @mbggenerated
     */
    int insertSelective(RecordUserTx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record_user_tx
     *
     * @mbggenerated
     */
    List<RecordUserTx> selectByExample(RecordUserTxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record_user_tx
     *
     * @mbggenerated
     */
    RecordUserTx selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record_user_tx
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") RecordUserTx record, @Param("example") RecordUserTxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record_user_tx
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") RecordUserTx record, @Param("example") RecordUserTxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record_user_tx
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RecordUserTx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record_user_tx
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RecordUserTx record);
}