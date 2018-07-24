package com.witshare.mars.pojo.vo;

import com.github.pagehelper.PageInfo;

/**
 * Created by user on 2018/7/24.
 */
public class IndexTxVo {

    private UserTxInfoVo txInfo;

    private PageInfo<RecordUserTxListVo> txList;

    public static IndexTxVo newInstance() {
        return new IndexTxVo();
    }

    public UserTxInfoVo getTxInfo() {
        return txInfo;
    }

    public IndexTxVo setTxInfo(UserTxInfoVo txInfo) {
        this.txInfo = txInfo;
        return this;
    }

    public PageInfo<RecordUserTxListVo> getTxList() {
        return txList;
    }

    public IndexTxVo setTxList(PageInfo<RecordUserTxListVo> txList) {
        this.txList = txList;
        return this;
    }
}
