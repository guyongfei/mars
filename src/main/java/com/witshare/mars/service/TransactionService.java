package com.witshare.mars.service;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.RecordUserTxBean;
import com.witshare.mars.pojo.vo.RecordUserTxListVo;
import com.witshare.mars.pojo.vo.RecordUserTxVo;
import com.witshare.mars.pojo.vo.SysUserAddressVo;
import com.witshare.mars.pojo.vo.UserTxInfoVo;

import java.util.Map;

/**
 * Created by user on 2018/6/18.
 */
public interface TransactionService {

    UserTxInfoVo getUserTxInfo(String projectGid);

    void setUserAddress(Map<String, String> requestBody);

    void save(RecordUserTxBean requestBody);

    RecordUserTxBean selectByPayTx(String payTx);

    int selectBuyCount(String userGid, String projectGid);

    PageInfo<RecordUserTxListVo> selectList(RecordUserTxBean recordUserTxBean);

    RecordUserTxVo select(Long payTxId);

    void syncGasPrice();
}
