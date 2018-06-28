package com.witshare.mars.service.impl;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.dto.BasePageBean;
import com.witshare.mars.pojo.dto.PlatformAddressBean;
import com.witshare.mars.service.PlatformAddressService;
import com.witshare.mars.util.RedisKeyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.witshare.mars.constant.CacheConsts.KEY_DATA;

/**
 * Created by user on 2018/6/27.
 */
@Service
public class PlatformAddressServiceImpl implements PlatformAddressService {

    @Autowired
    private RedisCommonDao redisCommonDao;

    @Override
    public void delete(String address) {
        String platformAddressKey = RedisKeyUtil.getPlatformAddressKey();
        redisCommonDao.zDel(platformAddressKey, address);
    }

    @Override
    public void add(Map<String, String> requestBody) {
        if (MapUtils.isEmpty(requestBody)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String words = requestBody.get(KEY_DATA);
        String[] split = words.split("\n");
        List<String> list = Arrays.asList(split);
        HashSet<String> set = new HashSet<>();
        set.addAll(list);
        if (CollectionUtils.isEmpty(set)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        long nowTime = System.currentTimeMillis();
        //保存
        set.forEach(p -> {
            double v = nowTime + RandomUtils.nextDouble(0, 1);
            redisCommonDao.zAdd(RedisKeyUtil.getPlatformAddressKey(), p, v);
        });
    }

    @Override
    public String getPlatformAddress() {
        Set<String> strings = redisCommonDao.zGetAndDelete(RedisKeyUtil.getPlatformAddressKey());
        if (CollectionUtils.isNotEmpty(strings)) {
            return strings.iterator().next();
        }
        return null;
    }

    @Override
    public int count() {
        String platformAddressKey = RedisKeyUtil.getPlatformAddressKey();
        return (int) redisCommonDao.zCount(platformAddressKey);
//        return 1;
    }

    @Override
    public PageInfo<PlatformAddressBean> getList(BasePageBean basePageBean) {
        if (basePageBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        Integer pageSize = basePageBean.getPageSize();
        Integer pageNum = basePageBean.getPageNum();
        if (pageSize == null || pageNum == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        pageSize = pageSize <= 0 ? 10 : pageSize;
        pageNum = pageNum <= 0 ? 1 : pageNum;

        int total = this.count();

        PageInfo<PlatformAddressBean> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(total);

        if (total == 0) {
            return pageInfo;
        }
        int totalPage = total / pageSize + 1;
        pageNum = pageNum <= totalPage ? pageNum : totalPage;
        int offset = (pageNum - 1) * pageSize;


        String platformAddressKey = RedisKeyUtil.getPlatformAddressKey();
        Set<String> set = redisCommonDao.zRevRangeByScore(platformAddressKey, Double.MIN_VALUE, Double.MAX_VALUE, offset, pageSize);
        LinkedList<PlatformAddressBean> list = new LinkedList<>();
        set.forEach(p -> {
            PlatformAddressBean platformAddressBean = PlatformAddressBean.newInstance().setAddress(p);
            list.add(platformAddressBean);
        });
        pageInfo.setList(list);
        return pageInfo;
    }


}
