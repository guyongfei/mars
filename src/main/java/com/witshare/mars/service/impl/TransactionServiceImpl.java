package com.witshare.mars.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.witshare.mars.constant.EnumProjectStatus;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.dao.mysql.RecordUserTxMapper;
import com.witshare.mars.dao.mysql.StaticSysUserTxMapper;
import com.witshare.mars.dao.mysql.SysUserAddressMapper;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.domain.RecordUserTx;
import com.witshare.mars.pojo.domain.RecordUserTxExample;
import com.witshare.mars.pojo.domain.SysUserAddress;
import com.witshare.mars.pojo.domain.SysUserAddressExample;
import com.witshare.mars.pojo.dto.RecordUserTxBean;
import com.witshare.mars.pojo.dto.SysProjectBean;
import com.witshare.mars.pojo.dto.SysUserAddressBean;
import com.witshare.mars.pojo.dto.SysUserBean;
import com.witshare.mars.pojo.vo.SysUserAddressVo;
import com.witshare.mars.service.SysProjectService;
import com.witshare.mars.service.SysUserService;
import com.witshare.mars.service.TransactionService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static com.witshare.mars.pojo.dto.RecordUserTxBean.PROJECT_GID;
import static com.witshare.mars.pojo.dto.SysUserBean.GET_TOKEN_ADDRESS;
import static com.witshare.mars.pojo.dto.SysUserBean.PAY_ETH_ADDRESS;

/**
 * Created by user on 2018/6/18.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private RecordUserTxMapper recordUserTxMapper;
    @Autowired
    private StaticSysUserTxMapper staticSysUserTxMapper;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysProjectService sysProjectService;
    @Autowired
    private SysUserAddressMapper sysUserAddressMapper;

    @Override
    public SysUserAddressVo getUserAddress(String projectGid) {
        if (StringUtils.isEmpty(projectGid)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        //不能重复设置
        SysUserBean currentUser = sysUserService.getCurrentUser();
        String userGid = currentUser.getUserGid();
        SysProjectBean sysProjectBean = sysProjectService.selectByProjectGid(projectGid);
        if (sysProjectBean == null) {
            throw new WitshareException(EnumResponseText.ErrorProjectGId);
        }
        SysUserAddressExample sysUserAddressExample = new SysUserAddressExample();
        sysUserAddressExample.or().andProjectGidEqualTo(projectGid).andUserGidEqualTo(userGid);
        List<SysUserAddress> sysUserAddresses = sysUserAddressMapper.selectByExample(sysUserAddressExample);
        if (CollectionUtils.isEmpty(sysUserAddresses)) {
            return null;
        }
        SysUserAddressVo sysUserAddressVo = SysUserAddressVo.newInstance();
        BeanUtils.copyProperties(sysUserAddresses.get(0), sysUserAddressVo);
        return sysUserAddressVo;
    }

    @Override
    public void setUserAddress(Map<String, String> requestBody) {
        if (MapUtils.isEmpty(requestBody)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String payEthAddress = requestBody.get(PAY_ETH_ADDRESS);
        String projectGid = requestBody.get(PROJECT_GID);
        String getTokenAddress = requestBody.get(GET_TOKEN_ADDRESS);
        if (StringUtils.isEmpty(payEthAddress)
                || StringUtils.isEmpty(getTokenAddress)
                || StringUtils.isEmpty(projectGid)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        //不能重复设置
        SysUserBean currentUser = sysUserService.getCurrentUser();
        String userGid = currentUser.getUserGid();
        SysProjectBean sysProjectBean = sysProjectService.selectByProjectGid(projectGid);
        if (sysProjectBean == null) {
            throw new WitshareException(EnumResponseText.ErrorProjectGId);
        }

        SysUserAddressExample sysUserAddressExample = new SysUserAddressExample();
        sysUserAddressExample.or().andProjectGidEqualTo(projectGid).andUserGidEqualTo(userGid);
        List<SysUserAddress> sysUserAddresses = sysUserAddressMapper.selectByExample(sysUserAddressExample);
        if (CollectionUtils.isNotEmpty(sysUserAddresses)) {
            throw new WitshareException(EnumResponseText.CannotRepeatSetting);
        }
        //保存
        Timestamp current = new Timestamp(System.currentTimeMillis());
        SysUserAddressBean sysUserAddressBean = SysUserAddressBean.newInstance()
                .setUserGid(userGid)
                .setEmail(currentUser.getEmail())
                .setProjectGid(projectGid)
                .setProjectToken(sysProjectBean.getProjectToken())
                .setGetTokenAddress(getTokenAddress)
                .setPayEthAddress(payEthAddress)
                .setCreateTime(current)
                .setUpdateTime(current);
        SysUserAddress sysUserAddress = new SysUserAddress();
        BeanUtils.copyProperties(sysUserAddressBean, sysUserAddress);
        sysUserAddressMapper.insertSelective(sysUserAddress);
    }

    /**
     * 保存交易
     */
    @Override
    public void save(RecordUserTxBean recordUserTxBean) {

        if (recordUserTxBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        SysUserBean currentUser = sysUserService.getCurrentUser();
        String projectGid = recordUserTxBean.getProjectGid();
        String payTx = recordUserTxBean.getPayTx();
        BigDecimal priceRate = recordUserTxBean.getPriceRate();
        BigDecimal payAmount = recordUserTxBean.getPayAmount();
        BigDecimal hopeGetAmount = recordUserTxBean.getHopeGetAmount();
        int payCoinType = recordUserTxBean.getPayCoinType();
        if (currentUser == null
                || StringUtils.isEmpty(projectGid)
                || StringUtils.isEmpty(payTx)
                || priceRate == null
                || payAmount == null
                || hopeGetAmount == null
                || payCoinType != 0) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }

        // 项目状态判断
        SysProjectBean sysProjectBean = sysProjectService.selectByProjectGid(projectGid);
        if (sysProjectBean == null
                || (sysProjectBean.getProjectStatus() != EnumProjectStatus.Status1.getStatus()
                && sysProjectBean.getProjectStatus() != EnumProjectStatus.Status2.getStatus())) {
            throw new WitshareException(EnumResponseText.ErrorProjectGId);
        }
        //价格判断
        BigDecimal price = sysProjectBean.getPriceRate();
        if (price.compareTo(priceRate) != 0
                || price.compareTo(hopeGetAmount.divide(payAmount, 10, 4)) != 0) {
            throw new WitshareException(EnumResponseText.ErrorPriceRate);
        }
        // 交易号判断
        RecordUserTxBean recordUserTxDb = this.selectByPayTx(payTx);
        if (recordUserTxDb != null) {
            throw new WitshareException(EnumResponseText.ErrorPayTx);
        }
        Timestamp current = new Timestamp(System.currentTimeMillis());
        recordUserTxBean.setUserGid(currentUser.getUserGid())
                .setUserEmail(currentUser.getEmail())
                .setProjectToken(sysProjectBean.getProjectToken())
                .setTime(current);
        //保存
        staticSysUserTxMapper.insert(recordUserTxBean);
    }


    @Override
    public RecordUserTxBean selectByPayTx(String payTx) {
        if (StringUtils.isEmpty(payTx)) {
            return null;
        }
        RecordUserTxExample recordUserTxExample = new RecordUserTxExample();
        recordUserTxExample.or().andPayTxEqualTo(payTx);
        List<RecordUserTx> recordUserTxes = recordUserTxMapper.selectByExample(recordUserTxExample);
        if (CollectionUtils.isNotEmpty(recordUserTxes)) {
            RecordUserTxBean recordUserTxBean = RecordUserTxBean.newInstance();
            BeanUtils.copyProperties(recordUserTxes.get(0), recordUserTxBean);
            return recordUserTxBean;
        }
        return null;
    }
}
