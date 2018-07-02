package com.witshare.mars.service;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.RecordPlatformTxBean;

/**
 * Created by user on 2018/7/2.
 */
public interface PlatformTxInfoService {

    PageInfo<RecordPlatformTxBean> getList(RecordPlatformTxBean recordPlatformTxBean);

    void add(RecordPlatformTxBean recordPlatformTxBean);

    void delete(String txHash);

}
