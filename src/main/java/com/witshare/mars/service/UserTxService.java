package com.witshare.mars.service;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.RecordUserTxBean;
import com.witshare.mars.pojo.vo.DistributionStatusVo;

/**
 * Created by user on 2018/6/29.
 */
public interface UserTxService {
    PageInfo<RecordUserTxBean> getList(RecordUserTxBean recordUserTxBean);

    PageInfo<RecordUserTxBean> getList(String projectGid);

    PageInfo<DistributionStatusVo> getUserTxStatusCount(String projectGid);

    PageInfo<DistributionStatusVo> getPlatformStatusCount(RecordUserTxBean recordUserTxBean);

    PageInfo<DistributionStatusVo> getPlatformStatusCount(String projectGid);

    PageInfo<DistributionStatusVo> getUserTxStatusCount(RecordUserTxBean recordUserTxBean);


}
