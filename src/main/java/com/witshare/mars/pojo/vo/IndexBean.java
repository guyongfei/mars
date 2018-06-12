package com.witshare.mars.pojo.vo;

import com.witshare.mars.pojo.dto.ProjectPageBean;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class IndexBean {

    private List<SysUserViewVo> startUserList = new LinkedList<>();
    private List<ConfCoopPartnerVo> coopPartnerList = new LinkedList<>();
    private List<ConfBaseInfoVo> baseInfoList = new LinkedList<>();
    private Collection<SysBillBoardVo> billBoardList = new LinkedList<>();
    private List<ProjectPageBean> starProjectList = new LinkedList<>();

    public IndexBean() {
    }

    public IndexBean(List<SysUserViewVo> startUserList, List<ConfCoopPartnerVo> coopPartnerList, List<ConfBaseInfoVo> baseInfoList, Collection<SysBillBoardVo> billBoardList, List<ProjectPageBean> starProjectList) {
        this.startUserList = startUserList;
        this.coopPartnerList = coopPartnerList;
        this.baseInfoList = baseInfoList;
        this.billBoardList = billBoardList;
        this.starProjectList = starProjectList;
    }

    public List<SysUserViewVo> getStartUserList() {
        return startUserList;
    }

    public void setStartUserList(List<SysUserViewVo> startUserList) {
        this.startUserList = startUserList;
    }

    public List<ConfCoopPartnerVo> getCoopPartnerList() {
        return coopPartnerList;
    }

    public void setCoopPartnerList(List<ConfCoopPartnerVo> coopPartnerList) {
        this.coopPartnerList = coopPartnerList;
    }

    public List<ConfBaseInfoVo> getBaseInfoList() {
        return baseInfoList;
    }

    public void setBaseInfoList(List<ConfBaseInfoVo> baseInfoList) {
        this.baseInfoList = baseInfoList;
    }

    public Collection<SysBillBoardVo> getBillBoardList() {
        return billBoardList;
    }

    public void setBillBoardList(Collection<SysBillBoardVo> billBoardList) {
        this.billBoardList = billBoardList;
    }

    public List<ProjectPageBean> getStarProjectList() {
        return starProjectList;
    }

    public void setStarProjectList(List<ProjectPageBean> starProjectList) {
        this.starProjectList = starProjectList;
    }
}
