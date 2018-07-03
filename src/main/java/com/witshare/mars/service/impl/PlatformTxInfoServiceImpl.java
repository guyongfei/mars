package com.witshare.mars.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.dao.mysql.RecordPlatformTxMapper;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.domain.RecordPlatformTx;
import com.witshare.mars.pojo.domain.RecordPlatformTxExample;
import com.witshare.mars.pojo.dto.RecordPlatformTxBean;
import com.witshare.mars.service.PlatformTxInfoService;
import com.witshare.mars.service.SysProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.LinkedList;

/**
 * Created by user on 2018/7/2.
 */
@Service
public class PlatformTxInfoServiceImpl implements PlatformTxInfoService {

    @Autowired
    private RecordPlatformTxMapper recordPlatformTxMapper;
    @Autowired
    private SysProjectService sysProjectService;


    @Override
    public PageInfo<RecordPlatformTxBean> getList(RecordPlatformTxBean recordPlatformTxBean) {
        if (recordPlatformTxBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String txHash = recordPlatformTxBean.getTxHash();
        String projectToken = recordPlatformTxBean.getProjectToken();

        Integer pageNum = recordPlatformTxBean.getPageNum();
        Integer pageSize = recordPlatformTxBean.getPageSize();

        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? Integer.MAX_VALUE : pageSize;

        RecordPlatformTxExample recordPlatformTxExample = new RecordPlatformTxExample();
        RecordPlatformTxExample.Criteria or = recordPlatformTxExample.or();
        if (!StringUtils.isBlank(txHash)) {
            or.andTxHashEqualTo(txHash);
        }
        if (!StringUtils.isBlank(projectToken)) {
            or.andProjectTokenLike("%" + projectToken + "%");
        }
        PageInfo<RecordPlatformTx> pageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> recordPlatformTxMapper.selectByExample(recordPlatformTxExample));
        LinkedList<RecordPlatformTxBean> recordPlatformTxBeans = new LinkedList<>();
        pageInfo.getList().forEach(p -> {
            RecordPlatformTxBean recordPlatformTxBean_ = RecordPlatformTxBean.newInstance();
            BeanUtils.copyProperties(p, recordPlatformTxBean_);
            recordPlatformTxBeans.add(recordPlatformTxBean_);
        });
        PageInfo<RecordPlatformTxBean> pageInfo_ = new PageInfo<>();
        pageInfo.setList(null);
        BeanUtils.copyProperties(pageInfo, pageInfo_);
        pageInfo_.setList(recordPlatformTxBeans);
        return pageInfo_;
    }


    @Override
    public void add(RecordPlatformTxBean recordPlatformTxBean) {
        if (recordPlatformTxBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String txHash = recordPlatformTxBean.getTxHash();
        if (StringUtils.isBlank(txHash)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        Timestamp current = new Timestamp(System.currentTimeMillis());
        RecordPlatformTx recordPlatformTx = new RecordPlatformTx();
        recordPlatformTx.setTxHash(txHash);
        recordPlatformTx.setCreateTime(current);
        recordPlatformTx.setUpdateTime(current);
        recordPlatformTxMapper.insertSelective(recordPlatformTx);
    }


    @Override
    public void delete(String txHash) {

        if (StringUtils.isBlank(txHash)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        RecordPlatformTxExample recordPlatformTxExample = new RecordPlatformTxExample();
        recordPlatformTxExample.or().andTxHashEqualTo(txHash);
        int i = recordPlatformTxMapper.deleteByExample(recordPlatformTxExample);
        if (i < 1) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
    }


}
