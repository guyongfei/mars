package com.witshare.mars.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.dao.mysql.RecordUserTxMapper;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.domain.RecordUserTx;
import com.witshare.mars.pojo.domain.RecordUserTxExample;
import com.witshare.mars.pojo.dto.RecordUserTxBean;
import com.witshare.mars.pojo.dto.SysProjectBean;
import com.witshare.mars.pojo.dto.SysUserAddressBean;
import com.witshare.mars.pojo.vo.DistributionStatusVo;
import com.witshare.mars.service.SysProjectService;
import com.witshare.mars.service.SysUserAddressService;
import com.witshare.mars.service.UserTxService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2018/6/29.
 */
@Service
public class UserTxServiceImpl implements UserTxService {

    @Autowired
    private SysProjectService sysProjectService;
    @Autowired
    private RecordUserTxMapper recordUserTxMapper;
    @Autowired
    private SysUserAddressService sysUserAddressService;

    @Override
    public PageInfo<RecordUserTxBean> getList(RecordUserTxBean recordUserTxBean) {
        if (recordUserTxBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        Integer pageSize = recordUserTxBean.getPageSize();
        Integer pageNum = recordUserTxBean.getPageNum();
        String projectGid = recordUserTxBean.getProjectGid();
        String email = recordUserTxBean.getEmail();
        Long payTxId = recordUserTxBean.getPayTxId();
        String payTx = recordUserTxBean.getPayTx();
        Integer userTxStatus = recordUserTxBean.getUserTxStatus();
        Integer platformTxStatus = recordUserTxBean.getPlatformTxStatus();

        if (StringUtils.isEmpty(projectGid)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        if (pageSize == null) {
            pageSize = Integer.MAX_VALUE;
        }
        if (pageNum == null) {
            pageNum = 1;
        }

        SysProjectBean sysProjectBean = sysProjectService.selectByProjectGid(projectGid);
        if (sysProjectBean == null) {
            throw new WitshareException(EnumResponseText.ErrorProjectId);
        }
        String platformAddress = sysProjectBean.getPlatformAddress();


        RecordUserTxExample recordUserTxExample = new RecordUserTxExample();
        RecordUserTxExample.Criteria or = recordUserTxExample.or();
        or.andProjectGidEqualTo(projectGid);
        if (payTxId != null) {
            or.andIdEqualTo(payTxId - 10000);
        }
        if (StringUtils.isNotEmpty(email)) {
            or.andUserEmailLike("%" + email + "%");
        }
        if (StringUtils.isNotEmpty(payTx)) {
            or.andPayTxEqualTo(payTx);
        }
        if (userTxStatus != null) {
            or.andUserTxStatusEqualTo(userTxStatus);
        }
        if (platformTxStatus != null) {
            or.andPlatformTxStatusEqualTo(platformTxStatus);
        }

        //设置要查询的表
        PageInfo<RecordUserTx> pageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> recordUserTxMapper.selectByExample(recordUserTxExample));

        PageInfo<RecordUserTxBean> pageInfo_ = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, pageInfo_);
        LinkedList<RecordUserTxBean> recordUserTxBeans = new LinkedList<>();
        pageInfo.getList().forEach(p -> {
            RecordUserTxBean recordUserTxBean_ = RecordUserTxBean.newInstance();
            BeanUtils.copyProperties(p, recordUserTxBean_);
            //设置用户支付地址
            SysUserAddressBean sysUserAddressBean = sysUserAddressService.get(p.getUserGid(), projectGid);
            if (sysUserAddressBean != null) {
                recordUserTxBean_.setPayEthAddress(sysUserAddressBean.getPayEthAddress());
            }
            //设置平台受币地址
            recordUserTxBean_.setPlatformAddress(platformAddress);
            recordUserTxBean_.setPayTxId(p.getId() + 10000);
            recordUserTxBeans.add(recordUserTxBean_);
        });
        pageInfo_.setList(recordUserTxBeans);
        return pageInfo_;

    }


    @Override
    public PageInfo<DistributionStatusVo> getPlatformStatusCount(RecordUserTxBean recordUserTxBean) {

        if (recordUserTxBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String projectGid = recordUserTxBean.getProjectGid();
        if (StringUtils.isEmpty(projectGid)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        PageInfo<DistributionStatusVo> distributionStatusVoPageInfo = new PageInfo<>();
        RecordUserTxExample recordUserTxExample = new RecordUserTxExample();
        recordUserTxExample.or().andProjectGidEqualTo(projectGid);
        List<RecordUserTx> recordUserTxes = recordUserTxMapper.selectByExample(recordUserTxExample);
        LinkedList<DistributionStatusVo> distributionStatusVos = new LinkedList<>();
        int[] userTxStatusArr = new int[100];
        int[] platformTxStatusArr = new int[100];
        LinkedList<DistributionStatusVo> childList = new LinkedList<>();
        if (CollectionUtils.isNotEmpty(recordUserTxes)) {
            recordUserTxes.forEach(p -> {
                Integer platformTxStatus = p.getPlatformTxStatus();
                platformTxStatusArr[platformTxStatus]++;
                if (platformTxStatus == 3) {
                    Integer userTxStatus = p.getUserTxStatus();
                    userTxStatusArr[userTxStatus]++;
                }
            });
        }

        for (int i = 0; i < 100; i++) {
            int value = platformTxStatusArr[i];
            if (value > 0) {
                DistributionStatusVo distributionStatusVo = DistributionStatusVo.newInstance()
                        .setPlatformTxStatus(i)
                        .setCount(value);
                distributionStatusVos.add(distributionStatusVo);
                if (i == 3) {
                    distributionStatusVo.setChild(childList);
                }
            }

        }
        for (int i = 0; i < 100; i++) {
            int value = userTxStatusArr[i];
            if (value > 0) {
                DistributionStatusVo distributionStatusVo = DistributionStatusVo.newInstance()
                        .setUserTxStatus(i)
                        .setCount(value);
                childList.add(distributionStatusVo);
            }
        }

        int total = distributionStatusVos.size();
        distributionStatusVoPageInfo.setPageSize(total);
        distributionStatusVoPageInfo.setPageNum(1);
        distributionStatusVoPageInfo.setTotal(total);
        distributionStatusVoPageInfo.setList(distributionStatusVos);
        return distributionStatusVoPageInfo;
    }


}
