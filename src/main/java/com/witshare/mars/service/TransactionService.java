package com.witshare.mars.service;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.RecordUserTxBean;
import com.witshare.mars.pojo.vo.*;

import java.util.Map;

/**
 * Created by user on 2018/6/18.
 */
public interface TransactionService {

    UserTxInfoVo getUserTxInfo(String projectGid);

    IndexTxVo getIndexTxInfo(RecordUserTxBean recordUserTxBean);

    void setUserAddress(Map<String, String> requestBody) throws Exception;

    void save(RecordUserTxBean requestBody) throws Exception;

    void saveIndexTx(RecordUserTxBean requestBody) throws Exception;

    RecordUserTxBean selectByPayTx(String payTx);

    int selectBuyCount(String userGid, String projectGid);

    PageInfo<RecordUserTxListVo> selectList(RecordUserTxBean recordUserTxBean);

    RecordUserTxVo select(Long payTxId);

    void syncGasPrice();
}
