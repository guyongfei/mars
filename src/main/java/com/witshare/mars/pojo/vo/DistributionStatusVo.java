package com.witshare.mars.pojo.vo;

import java.util.LinkedList;

/**
 * Created by user on 2018/6/29.
 */
public class DistributionStatusVo {

    private Long id;
    private Integer userTxStatus;
    private Integer platformTxStatus;
    private Integer count;
    private Integer needDistributeCount;
    private LinkedList<DistributionStatusVo> child;

    public DistributionStatusVo() {
    }

    public static DistributionStatusVo newInstance() {
        return new DistributionStatusVo();
    }

    public Long getId() {
        return id;
    }

    public DistributionStatusVo setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public DistributionStatusVo setCount(Integer count) {
        this.count = count;
        return this;
    }

    public Integer getUserTxStatus() {
        return userTxStatus;
    }

    public DistributionStatusVo setUserTxStatus(Integer userTxStatus) {
        this.userTxStatus = userTxStatus;
        return this;
    }

    public Integer getPlatformTxStatus() {
        return platformTxStatus;
    }

    public DistributionStatusVo setPlatformTxStatus(Integer platformTxStatus) {
        this.platformTxStatus = platformTxStatus;
        return this;
    }

    public LinkedList<DistributionStatusVo> getChild() {
        return child;
    }

    public DistributionStatusVo setChild(LinkedList<DistributionStatusVo> child) {
        this.child = child;
        return this;
    }

    public Integer getNeedDistributeCount() {
        return needDistributeCount;
    }

    public DistributionStatusVo setNeedDistributeCount(Integer needDistributeCount) {
        this.needDistributeCount = needDistributeCount;
        return this;
    }
}
