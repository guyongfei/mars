package com.witshare.mars.service;

import com.witshare.mars.pojo.dto.RecordUserTxBean;
import com.witshare.mars.pojo.vo.SysUserAddressVo;

import java.util.Map;

/**
 * Created by user on 2018/6/18.
 */
public interface TransactionService {

    SysUserAddressVo getUserAddress(String projectGid);

    void setUserAddress(Map<String, String> requestBody);

    void save(RecordUserTxBean requestBody);

    RecordUserTxBean selectByPayTx(String payTx);
}
