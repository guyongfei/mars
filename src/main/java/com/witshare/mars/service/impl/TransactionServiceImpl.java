package com.witshare.mars.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.witshare.mars.config.DistributedLocker;
import com.witshare.mars.constant.EnumProjectStatus;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.constant.PropertiesConfig;
import com.witshare.mars.dao.mysql.RecordUserTxMapper;
import com.witshare.mars.dao.mysql.StaticSysUserTxMapper;
import com.witshare.mars.dao.mysql.SysUserAddressMapper;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.domain.RecordUserTx;
import com.witshare.mars.pojo.domain.RecordUserTxExample;
import com.witshare.mars.pojo.domain.SysUserAddress;
import com.witshare.mars.pojo.domain.SysUserAddressExample;
import com.witshare.mars.pojo.dto.*;
import com.witshare.mars.pojo.vo.IndexTxVo;
import com.witshare.mars.pojo.vo.RecordUserTxListVo;
import com.witshare.mars.pojo.vo.RecordUserTxVo;
import com.witshare.mars.pojo.vo.UserTxInfoVo;
import com.witshare.mars.service.*;
import com.witshare.mars.util.HttpClientUtil;
import com.witshare.mars.util.RedisKeyUtil;
import com.witshare.mars.util.WitshareUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.witshare.mars.constant.CacheConsts.TX_ID_INCREMENT;
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
    @Autowired
    private PropertiesConfig propertiesConfig;
    @Autowired
    private TokenDistributeService tokenDistributeService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private RedisCommonDao redisCommonDao;
    @Autowired
    private DistributedLocker distributedLocker;
    private final static String USER_TX_LOCK = "userTxLock:";
    private final static int USER_TX_LOCK_TIME = 5;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    @Override
    public IndexTxVo getIndexTxInfo(RecordUserTxBean recordUserTxBean) {
        String projectGid = recordUserTxBean.getProjectGid();
        IndexTxVo indexTxVo = IndexTxVo.newInstance();
        return indexTxVo.setTxInfo(this.getUserTxInfo(projectGid))
                .setTxList(this.selectList(recordUserTxBean));
    }

    @Override
    public UserTxInfoVo getUserTxInfo(String projectGid) {
        if (StringUtils.isEmpty(projectGid)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        SysUserBean currentUser = sysUserService.getCurrentUser();
        String userGid = currentUser.getUserGid();
        UserTxInfoVo userTxInfoVo = UserTxInfoVo.newInstance().setUserGid(userGid);
        SysProjectBean sysProjectBean = sysProjectService.selectByProjectGid(projectGid);
        if (sysProjectBean == null) {
            throw new WitshareException(EnumResponseText.ErrorProjectGId);
        }
        BeanUtils.copyProperties(sysProjectBean, userTxInfoVo);
        //获取用户地址
        SysUserAddressExample sysUserAddressExample = new SysUserAddressExample();
        sysUserAddressExample.or().andProjectGidEqualTo(projectGid).andUserGidEqualTo(userGid);
        List<SysUserAddress> sysUserAddresses = sysUserAddressMapper.selectByExample(sysUserAddressExample);
        if (CollectionUtils.isNotEmpty(sysUserAddresses)) {
            BeanUtils.copyProperties(sysUserAddresses.get(0), userTxInfoVo);
        }
        //是否已经交易次数上限
        int txCount = this.selectBuyCount(userGid, projectGid);
        int projectUserTxMax = propertiesConfig.projectUserTxMax;
        userTxInfoVo.setTxCountLimit(NumberUtils.compare(projectUserTxMax, txCount) <= 0).setTxCount(txCount);
        //交易价格
        MoonGetPriceResponseBean.Result gasPrice = this.getGasPrice();
        userTxInfoVo.setGasPrice(gasPrice);
        return userTxInfoVo;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void saveIndexTx(RecordUserTxBean recordUserTxBean) {
        Map<String, String> stringMap = WitshareUtils.objectToRedisMap(recordUserTxBean);
        String payEthAddress = recordUserTxBean.getPayEthAddress();
        //无则设置，有则跳过
        if (StringUtils.isNotBlank(payEthAddress)) {
            this.setUserAddress(stringMap);
        } else {
            String projectGid = recordUserTxBean.getProjectGid();
            SysUserBean currentUser = sysUserService.getCurrentUser();
            String userGid = currentUser.getUserGid();
            SysUserAddressExample sysUserAddressExample = new SysUserAddressExample();
            sysUserAddressExample.or().andProjectGidEqualTo(projectGid).andUserGidEqualTo(userGid);
            List<SysUserAddress> sysUserAddresses = sysUserAddressMapper.selectByExample(sysUserAddressExample);
            if (CollectionUtils.isEmpty(sysUserAddresses)) {
                throw new WitshareException(EnumResponseText.AddressMust);
            }
        }
        this.save(recordUserTxBean);
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
        String channel = channelService.checkChannel(recordUserTxBean.getChannel());
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
                || (sysProjectBean.getProjectStatus() != EnumProjectStatus.Status1.getStatus().intValue()
                && sysProjectBean.getProjectStatus() != EnumProjectStatus.Status2.getStatus().intValue())) {
            throw new WitshareException(EnumResponseText.ErrorProjectGId);
        }
        // 最低认购数量判断
        if (sysProjectBean.getMinPurchaseAmount().compareTo(payAmount) > 0) {
            throw new WitshareException(EnumResponseText.NotReachMinPurchaseAmount);
        }
        // 最高认购数量判断
        if (sysProjectBean.getMaxPurchaseAmount().compareTo(payAmount) < 0) {
            throw new WitshareException(EnumResponseText.NotReachMaxPurchaseAmount);
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
        String userGid = currentUser.getUserGid();
        recordUserTxBean.setUserGid(userGid)
                .setUserEmail(currentUser.getEmail())
                .setChannel(channel)
                .setProjectToken(sysProjectBean.getProjectToken())
                .setTime(current);

        //每个用户的交易数量受到限制
        String txLockKey = USER_TX_LOCK + userGid;
        String lockId = distributedLocker.lock(txLockKey, USER_TX_LOCK_TIME);
        if (StringUtils.isEmpty(lockId)) {
            throw new WitshareException(EnumResponseText.ErrorPayTx);
        }
        try {
            int projectUserTxMax = propertiesConfig.projectUserTxMax;
            if (-1 != projectUserTxMax) {
                int buyCount = this.selectBuyCount(userGid, projectGid);
                if (NumberUtils.compare(projectUserTxMax, buyCount) <= 0) {
                    throw new WitshareException(EnumResponseText.ReachTxMax);
                }
            }
            //保存
            staticSysUserTxMapper.insert(recordUserTxBean);
        } catch (Exception e) {
            LOGGER.info("save tx fail.userGid:{},projectGid:{}", userGid, projectGid, e);
            throw e;
        } finally {
            distributedLocker.unlock(txLockKey, lockId);
        }

    }

    /**
     * 查询交易
     */
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

    /**
     * 查询已认购次数
     */
    @Override
    public int selectBuyCount(String userGid, String projectGid) {
        if (StringUtils.isEmpty(userGid) || StringUtils.isEmpty(projectGid)) {
            return 0;
        }
        RecordUserTxExample recordUserTxExample = new RecordUserTxExample();
        recordUserTxExample.or().andUserGidEqualTo(userGid).andProjectGidEqualTo(projectGid);
        List<RecordUserTx> recordUserTxes = recordUserTxMapper.selectByExample(recordUserTxExample);
        return recordUserTxes.size();
    }

    /**
     * 查询认购列表
     */
    @Override
    public PageInfo<RecordUserTxListVo> selectList(RecordUserTxBean recordUserTxBean) {
        if (recordUserTxBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String projectGid = recordUserTxBean.getProjectGid();
        Integer pageNum = recordUserTxBean.getPageNum();
        Integer pageSize = recordUserTxBean.getPageSize();
        SysUserBean currentUser = sysUserService.getCurrentUser();
        recordUserTxBean.setUserGid(currentUser.getUserGid());
        if (StringUtils.isEmpty(projectGid) || pageNum == null || pageSize == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        PageInfo<RecordUserTxBean> pageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> staticSysUserTxMapper.select(recordUserTxBean));

        PageInfo<RecordUserTxListVo> pageInfoVo = new PageInfo<>();
        LinkedList<RecordUserTxListVo> vos = new LinkedList<>();
        pageInfo.getList().forEach(p -> {
            RecordUserTxListVo vo = RecordUserTxListVo.newInstance();
            BeanUtils.copyProperties(p, vo);
            vo.setPayTxId(p.getId() + TX_ID_INCREMENT);
            vos.add(vo);
        });
        pageInfo.setList(null);
        BeanUtils.copyProperties(pageInfo, pageInfoVo);
        pageInfoVo.setList(vos);
        return pageInfoVo;

    }

    /**
     * 根据交易Id查找
     */
    @Override
    public RecordUserTxVo select(Long payTxId) {
        if (payTxId == null || payTxId <= TX_ID_INCREMENT) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        SysUserBean currentUser = sysUserService.getCurrentUser();
        String userGid = currentUser.getUserGid();
        RecordUserTx recordUserTx = recordUserTxMapper.selectByPrimaryKey(payTxId - TX_ID_INCREMENT);
        if (recordUserTx != null && StringUtils.equals(userGid, recordUserTx.getUserGid())) {
            RecordUserTxVo recordUserTxVo = RecordUserTxVo.newInstance();
            BeanUtils.copyProperties(recordUserTx, recordUserTxVo);
            recordUserTxVo.setPayTxId(payTxId);
            return recordUserTxVo;
        }
        return null;
    }

    /**
     * 请求mooon的GasPrice
     */
    private MoonGetPriceResponseBean.Result getGasPriceRequest() {
        String reqPath = "/gas/current";
        String url = propertiesConfig.moonHost + reqPath;
        String reqToken = tokenDistributeService.getToken(reqPath, RequestMethod.GET.name(), null, null);
        String result = HttpClientUtil.doGet(url, reqToken, propertiesConfig.moonTokenName);
        if (StringUtils.isNotEmpty(result)) {
            MoonGetPriceResponseBean moonGetPriceResponseBean = new Gson().fromJson(result, MoonGetPriceResponseBean.class);
            if ("0".equals(moonGetPriceResponseBean.getCode())) {
                return moonGetPriceResponseBean.getResult();
            }
        }
        return null;
    }

    /**
     * 同步GasPrice
     */
    @Override
    public void syncGasPrice() {
        MoonGetPriceResponseBean.Result gasPriceRequest = this.getGasPriceRequest();
        if (gasPriceRequest != null) {
            redisCommonDao.setString(RedisKeyUtil.getGasPriceKey(), new Gson().toJson(gasPriceRequest));
        }
    }

    /**
     * 获取GasPrice
     */
    private MoonGetPriceResponseBean.Result getGasPrice() {
        String gasPriceStr = redisCommonDao.getString(RedisKeyUtil.getGasPriceKey());
        if (StringUtils.isNotEmpty(gasPriceStr)) {
            return new Gson().fromJson(gasPriceStr, MoonGetPriceResponseBean.Result.class);
        }
        return null;
    }


}
