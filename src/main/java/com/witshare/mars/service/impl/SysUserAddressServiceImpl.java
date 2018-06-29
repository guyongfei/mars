package com.witshare.mars.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.gson.Gson;
import com.witshare.mars.dao.mysql.SysUserAddressMapper;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.pojo.domain.SysUserAddress;
import com.witshare.mars.pojo.domain.SysUserAddressExample;
import com.witshare.mars.pojo.dto.SysUserAddressBean;
import com.witshare.mars.service.SysUserAddressService;
import com.witshare.mars.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2018/6/20.
 */
@Service
public class SysUserAddressServiceImpl implements SysUserAddressService {

    @Autowired
    private SysUserAddressMapper sysUserAddressMapper;
    @Autowired
    private RedisCommonDao redisCommonDao;

    @Override
    public SysUserAddressBean get(String userGid, String projectGid) {
        if (StringUtils.isEmpty(userGid) || StringUtils.isEmpty(projectGid)) {
            return null;
        }
        SysUserAddressBean sysUserAddressBean = null;
        String userProjectAddressKey = RedisKeyUtil.getUserProjectAddressKey(projectGid);
        String address = redisCommonDao.getHash(userProjectAddressKey, userGid);
        if (StringUtils.isEmpty(address)) {
            SysUserAddressExample sysUserAddressExample = new SysUserAddressExample();
            sysUserAddressExample.or().andUserGidEqualTo(userGid).andProjectGidEqualTo(projectGid);
            List<SysUserAddress> sysUserAddresses = sysUserAddressMapper.selectByExample(sysUserAddressExample);
            if (CollectionUtils.isNotEmpty(sysUserAddresses)) {
                SysUserAddress sysUserAddress = sysUserAddresses.get(0);
                sysUserAddressBean = SysUserAddressBean.newInstance()
                        .setPayEthAddress(sysUserAddress.getPayEthAddress())
                        .setGetTokenAddress(sysUserAddress.getGetTokenAddress());
                redisCommonDao.putHash(userProjectAddressKey, userGid, new Gson().toJson(sysUserAddressBean));
            }
        } else {
            sysUserAddressBean = new Gson().fromJson(address, SysUserAddressBean.class);

        }
        return sysUserAddressBean;
    }
}
