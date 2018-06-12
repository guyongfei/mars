package com.witshare.mars.pojo.vo;

import java.util.Collection;


public class SysBillBoardListVo<T> {
    private Integer billBoardNum;

    private Collection<T> collection;

    public SysBillBoardListVo(Integer billBoardNum, Collection<T> collection) {
        this.billBoardNum = billBoardNum;
        this.collection = collection;
    }

    public SysBillBoardListVo(String billBoardNum, Collection<T> collection) {
        this.billBoardNum = Integer.parseInt(billBoardNum.trim());
        this.collection = collection;
    }

    public SysBillBoardListVo() {
    }

    public Integer getBillBoardNum() {
        return billBoardNum;
    }

    public void setBillBoardNum(Integer billBoardNum) {
        this.billBoardNum = billBoardNum;
    }

    public void setBillBoardNum(String billBoardNum) {
        this.billBoardNum = Integer.parseInt(billBoardNum.trim());
    }

    public Collection<T> getCollection() {
        return collection;
    }

    public void setCollection(Collection<T> collection) {
        this.collection = collection;
    }
}
