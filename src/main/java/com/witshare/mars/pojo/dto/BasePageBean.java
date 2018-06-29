package com.witshare.mars.pojo.dto;

/**
 * Created by user on 2018/6/27.
 */
public class BasePageBean {

    private Integer pageSize;
    private Integer pageNum;
    private String orderCondition;
    private String queryStr;
    private Integer ascOrdesc;

    public BasePageBean() {
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public BasePageBean setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public BasePageBean setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public String getOrderCondition() {
        return orderCondition;
    }

    public BasePageBean setOrderCondition(String orderCondition) {
        this.orderCondition = orderCondition;
        return this;
    }

    public Integer getAscOrdesc() {
        return ascOrdesc;
    }

    public BasePageBean setAscOrdesc(Integer ascOrdesc) {
        this.ascOrdesc = ascOrdesc;
        return this;
    }

    public String getQueryStr() {
        return queryStr;
    }

    public BasePageBean setQueryStr(String queryStr) {
        this.queryStr = queryStr;
        return this;
    }
}
