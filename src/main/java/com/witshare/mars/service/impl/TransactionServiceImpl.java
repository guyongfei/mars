package com.witshare.mars.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.witshare.mars.constant.EnumProjectStatus;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.dao.mysql.RecordUserTxMapper;
import com.witshare.mars.dao.mysql.SysUserMapper;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.domain.RecordUserTx;
import com.witshare.mars.pojo.domain.RecordUserTxExample;
import com.witshare.mars.pojo.domain.SysUser;
import com.witshare.mars.pojo.dto.RecordUserTxBean;
import com.witshare.mars.pojo.dto.SysProjectBean;
import com.witshare.mars.pojo.dto.SysUserBean;
import com.witshare.mars.service.ProjectDailyInfoService;
import com.witshare.mars.service.SysProjectService;
import com.witshare.mars.service.SysUserService;
import com.witshare.mars.service.TransactionService;
import com.witshare.mars.util.RedisKeyUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static com.witshare.mars.pojo.dto.RecordUserTxBean.*;
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
    private SysUserService sysUserService;
    @Autowired
    private SysProjectService sysProjectService;
    @Autowired
    private ProjectDailyInfoService projectDailyInfoService;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisCommonDao redisCommonDao;

    @Override
    public void setUserAddress(Map<String, String> requestBody) {
        if (MapUtils.isEmpty(requestBody)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String payEthAddress = requestBody.get(PAY_ETH_ADDRESS);
        String getTokenAddress = requestBody.get(GET_TOKEN_ADDRESS);
        if (StringUtils.isEmpty(payEthAddress) || StringUtils.isEmpty(getTokenAddress)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        //不能重复设置
        SysUserBean currentUser = sysUserService.getCurrentUser();
        if (StringUtils.isNotEmpty(currentUser.getGetTokenAddress())
                || StringUtils.isNotEmpty(currentUser.getPayEthAddress())) {
            throw new WitshareException(EnumResponseText.CannotRepeatSetting);
        }
        //保存值
        Timestamp current = new Timestamp(System.currentTimeMillis());
        SysUserBean sysUserBean = SysUserBean.newInstance().setId(currentUser.getId())
                .setPayEthAddress(payEthAddress)
                .setGetTokenAddress(getTokenAddress)
                .setUpdateTime(current);
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserBean, sysUser);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);

        redisCommonDao.delRedisKey(RedisKeyUtil.getCallApiInfo(currentUser.getEmail()));
    }

    /**
     * 保存交易
     */
    @Override
    public void save(Map<String, Object> requestBody) {

        if (MapUtils.isEmpty(requestBody)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        SysUserBean currentUser = sysUserService.getCurrentUser();
        String projectGid = (String) requestBody.get(PROJECT_GID);
        String payTx = (String) requestBody.get(PAY_TX);
        BigDecimal priceRate = (BigDecimal) requestBody.get(PRICE_RATE);
        BigDecimal payAmount = (BigDecimal) requestBody.get(PAY_AMOUNT);
        BigDecimal hopeGetAmount = (BigDecimal) requestBody.get(HOPE_GET_AMOUNT);
        int payCoinType = (int) requestBody.get(PAY_COIN_TYPE);
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
                || sysProjectBean.getProjectStatus() != EnumProjectStatus.Status1.getStatus()
                || sysProjectBean.getProjectStatus() != EnumProjectStatus.Status2.getStatus()) {
            throw new WitshareException(EnumResponseText.ErrorProjectGId);
        }
        //价格判断
        BigDecimal price = projectDailyInfoService.getPrice(projectGid, null);
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
        RecordUserTxBean recordUserTxBean = RecordUserTxBean.newInstance()
                .setUserGid(currentUser.getUserGid())
                .setUserEmail(currentUser.getEmail())
                .setProjectGid(sysProjectBean.getProjectGid())
                .setProjectToken(sysProjectBean.getProjectToken())
                .setPayAmount(payAmount)
                .setPayCoinType(payCoinType)
                .setPayTx(payTx)
                .setPriceRate(priceRate)
                .setHopeGetAmount(hopeGetAmount)
                .setTime(current);
        RecordUserTx recordUserTx = new RecordUserTx();
        BeanUtils.copyProperties(recordUserTxBean, recordUserTx);
        recordUserTxMapper.insertSelective(recordUserTx);
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
