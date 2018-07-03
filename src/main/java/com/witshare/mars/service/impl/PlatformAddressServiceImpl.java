package com.witshare.mars.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.dao.mysql.PlatformAddressMapper;
import com.witshare.mars.dao.mysql.StaticPlatformAddressMapper;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.domain.PlatformAddress;
import com.witshare.mars.pojo.domain.PlatformAddressExample;
import com.witshare.mars.pojo.dto.BasePageBean;
import com.witshare.mars.service.PlatformAddressService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static com.witshare.mars.constant.CacheConsts.KEY_DATA;

/**
 * Created by user on 2018/6/27.
 */
@Service
public class PlatformAddressServiceImpl implements PlatformAddressService {

    @Autowired
    private PlatformAddressMapper platformAddressMapper;
    @Autowired
    private StaticPlatformAddressMapper staticPlatformAddressMapper;

    @Override
    public void delete(String address) {
        PlatformAddressExample platformAddressExample = new PlatformAddressExample();
        platformAddressExample.or().andPlatformAddressEqualTo(address);
        platformAddressMapper.deleteByExample(platformAddressExample);
    }

    @Override
    public void add(Map<String, String> requestBody) {
        if (MapUtils.isEmpty(requestBody)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String words = requestBody.get(KEY_DATA);
        String[] split = words.split("\\s");
        List<String> list = Arrays.asList(split);
        HashSet<String> set = new HashSet<>();
        list.forEach(p -> {
            if (!StringUtils.isAnyBlank(p)) {
                set.add(p.trim());
            }
        });
        if (CollectionUtils.isEmpty(set)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        staticPlatformAddressMapper.saveOrUpdate(set);

    }

    @Override
    public void add(String address) {
        if (StringUtils.isAnyBlank(address)) {
            return;
        }
        Timestamp current = new Timestamp(System.currentTimeMillis());
        PlatformAddress platformAddress = new PlatformAddress();
        platformAddress.setPlatformAddress(address);
        platformAddress.setCreateTime(current);
        platformAddress.setUpdateTime(current);
        platformAddressMapper.insert(platformAddress);
    }


    @Override
    public String getPlatformAddress() {
        PlatformAddressExample platformAddressExample = new PlatformAddressExample();
        List<PlatformAddress> platformAddresses = platformAddressMapper.selectByExample(platformAddressExample);
        if (CollectionUtils.isNotEmpty(platformAddresses)) {
            return platformAddresses.get(0).getPlatformAddress();
        }
        return null;
    }

    @Override
    public int count() {
        PlatformAddressExample platformAddressExample = new PlatformAddressExample();
        return platformAddressMapper.countByExample(platformAddressExample);
    }

    @Override
    public PageInfo<PlatformAddress> getList(BasePageBean basePageBean) {
        if (basePageBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        Integer pageSize = basePageBean.getPageSize();
        Integer pageNum = basePageBean.getPageNum();
        if (pageSize == null || pageNum == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        PlatformAddressExample platformAddressExample = new PlatformAddressExample();
        PageInfo<PlatformAddress> pageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> platformAddressMapper.selectByExample(platformAddressExample));
        return pageInfo;
    }


}
