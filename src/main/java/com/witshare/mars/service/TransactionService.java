package com.witshare.mars.service;

import com.witshare.mars.pojo.dto.RecordUserTxBean;

import java.util.Map;

/**
 * Created by user on 2018/6/18.
 */
public interface TransactionService {

    void setUserAddress(Map<String, String> requestBody);

    void save(Map<String, Object> requestBody);

    RecordUserTxBean selectByPayTx(String payTx);
}
