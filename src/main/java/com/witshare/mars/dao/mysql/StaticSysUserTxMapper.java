package com.witshare.mars.dao.mysql;

import com.witshare.mars.pojo.dto.RecordUserTxBean;

/**
 * Created by user on 2018/6/20.
 */
public interface StaticSysUserTxMapper {
    int insert(RecordUserTxBean record);
}
