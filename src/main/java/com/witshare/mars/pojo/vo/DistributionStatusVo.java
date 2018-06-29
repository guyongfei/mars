package com.witshare.mars.pojo.vo;

/**
 * Created by user on 2018/6/29.
 */
public class DistributionStatusVo {

    private Long id;
    private Integer status;
    private Integer count;

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

    public Integer getStatus() {
        return status;
    }

    public DistributionStatusVo setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public DistributionStatusVo setCount(Integer count) {
        this.count = count;
        return this;
    }
}
