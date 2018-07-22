package com.witshare.mars.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.witshare.mars.config.DistributedLocker;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.dao.mysql.SysChannelMapper;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.domain.SysChannel;
import com.witshare.mars.pojo.domain.SysChannelExample;
import com.witshare.mars.pojo.dto.SysChannelBean;
import com.witshare.mars.pojo.vo.SysChannelVo;
import com.witshare.mars.service.ChannelService;
import com.witshare.mars.util.RedisKeyUtil;
import com.witshare.mars.util.WitshareUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2018/7/20.
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private SysChannelMapper sysChannelMapper;
    @Autowired
    private RedisCommonDao redisCommonDao;
    @Autowired
    private DistributedLocker distributedLocker;

    private final static String PROJECT_CHANNEL_LOCK = "projectChannelLock:%s";
    private final static int PROJECT_CHANNEL_TIME = 10;
    private final String CHANNEL_REG = "^[a-zA-Z0-9]{6}$";

    @Override
    public void save(SysChannelBean channelBean) {

        if (channelBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String channel = channelBean.getChannel();
        String note = channelBean.getNote();
        String name = channelBean.getName();
        if (StringUtils.isAnyBlank(channel, note, name)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        boolean channelMatch = channel.matches(CHANNEL_REG);
        if (!channelMatch) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        //加锁校验
        //TODO 渠道名未 加锁
        String channelLockKey = String.format(PROJECT_CHANNEL_LOCK, channel);
        String lockId = distributedLocker.lock(channelLockKey, PROJECT_CHANNEL_TIME);
        try {
            if (StringUtils.isEmpty(lockId)) {
                throw new WitshareException(EnumResponseText.ErrorChannel);
            }
            SysChannelExample sysChannelExample = new SysChannelExample();
            sysChannelExample.or().andChannelEqualTo(channel);
            sysChannelExample.or().andNameEqualTo(name);
            List<SysChannel> sysChannels = sysChannelMapper.selectByExample(sysChannelExample);
            if (CollectionUtils.isNotEmpty(sysChannels)) {
                SysChannel sysChannel = sysChannels.get(0);
                if (sysChannel.getChannel().equals(name)) {
                    throw new WitshareException(EnumResponseText.ErrorChannelName);
                }
                throw new WitshareException(EnumResponseText.ErrorChannel);
            }
            //存库
            Timestamp current = new Timestamp(System.currentTimeMillis());
            SysChannel sysChannel = new SysChannel();
            channelBean.
                    setChannelGid(WitshareUtils.getUUID())
                    .setCreateTime(current)
                    .setUpdateTime(current);
            BeanUtils.copyProperties(channelBean, sysChannel);
            sysChannelMapper.insertSelective(sysChannel);
            redisCommonDao.setString(RedisKeyUtil.getChannelInfoKey(channel), new Gson().toJson(sysChannel));
        } catch (Exception e) {
            throw e;
        } finally {
            distributedLocker.unlock(channelLockKey, lockId);
        }
    }


    @Override
    public PageInfo<SysChannelVo> list(SysChannelBean sysChannelBean) {
        if (sysChannelBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        Integer pageSize = sysChannelBean.getPageSize();
        Integer pageNum = sysChannelBean.getPageNum();
        if (pageNum == null || pageSize == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String queryStr = sysChannelBean.getQueryStr();
        SysChannelExample sysChannelExample = new SysChannelExample();
        if (StringUtils.isNotEmpty(queryStr)) {
            sysChannelExample.or().andChannelLike("%" + queryStr + "%");
            sysChannelExample.or().andNameLike("%" + queryStr + "%");
        }
        sysChannelExample.setOrderByClause("create_time desc");
        PageInfo<SysChannel> pageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> sysChannelMapper.selectByExample(sysChannelExample));

        PageInfo<SysChannelVo> pageInfo_ = new PageInfo<>();
        LinkedList<SysChannelVo> sysChannelVos = new LinkedList<>();
        final int totalRegisterCount = getRegisterCount(null);
        pageInfo.getList().forEach(p -> {
            String channel = p.getChannel();
            SysChannelVo sysChannelVo = SysChannelVo.newInstance();
            BeanUtils.copyProperties(p, sysChannelVo);
            //获取注册人数
            sysChannelVo.setRegisterCount(getRegisterCount(channel));
            sysChannelVo.setTotalRegisterCount(totalRegisterCount);
            sysChannelVos.add(sysChannelVo);
        });
        pageInfo.setList(null);
        BeanUtils.copyProperties(pageInfo, pageInfo_);
        pageInfo_.setList(sysChannelVos);

        return pageInfo_;
    }

    /**
     * 获取注册用户数
     */
    private int getRegisterCount(String channel) {
        channel = StringUtils.isEmpty(channel) ? "total" : channel;
        String registerCountKey = RedisKeyUtil.getChannelRegisterCountKey();
        String countStr = redisCommonDao.getHash(registerCountKey, channel);
        return NumberUtils.isNumber(countStr) ? Integer.parseInt(countStr) : 0;
    }


    @Override
    public SysChannelVo get(SysChannelBean sysChannelBean) {
        if (sysChannelBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String channel = sysChannelBean.getChannel();
        if (StringUtils.isAnyBlank(channel)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        SysChannelExample sysChannelExample = new SysChannelExample();
        sysChannelExample.or().andChannelEqualTo(channel);
        List<SysChannel> sysChannels = sysChannelMapper.selectByExample(sysChannelExample);
        if (CollectionUtils.isNotEmpty(sysChannels)) {
            SysChannel sysChannel = sysChannels.get(0);
            SysChannelVo sysChannelVo = SysChannelVo.newInstance();
            BeanUtils.copyProperties(sysChannel, sysChannelVo);
            return sysChannelVo;
        }
        return null;
    }

    @Override
    public SysChannelBean get(String channel) {
        if (StringUtils.isAnyBlank(channel) || "0".equals(channel)) {
            return null;
        }
        String json = redisCommonDao.getString(RedisKeyUtil.getChannelInfoKey(channel));
        if (!StringUtils.isAnyBlank(json)) {
            return new Gson().fromJson(json, SysChannelBean.class);
        }
        SysChannelExample sysChannelExample = new SysChannelExample();
        sysChannelExample.or().andChannelEqualTo(channel);
        List<SysChannel> sysChannels = sysChannelMapper.selectByExample(sysChannelExample);
        if (CollectionUtils.isNotEmpty(sysChannels)) {
            SysChannel sysChannel = sysChannels.get(0);
            SysChannelBean sysChannelBean = SysChannelBean.newInstance();
            BeanUtils.copyProperties(sysChannel, sysChannelBean);
            this.cacheChannel(sysChannelBean);
            return sysChannelBean;
        }
        return null;
    }


    @Override
    public void delete(String channelGid) {
        if (StringUtils.isAnyBlank(channelGid)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        SysChannelExample sysChannelExample = new SysChannelExample();
        sysChannelExample.or().andChannelGidEqualTo(channelGid);
        List<SysChannel> sysChannels = sysChannelMapper.selectByExample(sysChannelExample);
        if (CollectionUtils.isEmpty(sysChannels)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        sysChannelMapper.deleteByExample(sysChannelExample);
        this.deleteCacheChannel(sysChannels.get(0).getChannel());
    }


    @Override
    public void update(SysChannelBean sysChannelBean) {
        if (sysChannelBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String name = sysChannelBean.getName();
        String note = sysChannelBean.getNote();
        String channelGid = sysChannelBean.getChannelGid();
        if (StringUtils.isAnyBlank(name, channelGid)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        SysChannelExample sysChannelExample = new SysChannelExample();
        sysChannelExample.or().andChannelGidEqualTo(channelGid);
        List<SysChannel> sysChannels = sysChannelMapper.selectByExample(sysChannelExample);
        if (CollectionUtils.isEmpty(sysChannels)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        SysChannel sysChannelDb = sysChannels.get(0);
        //加锁校验
        //TODO 渠道名未 加锁
        String channelLockKey = String.format(PROJECT_CHANNEL_LOCK, sysChannelDb.getChannel());
        String lockId = distributedLocker.lock(channelLockKey, PROJECT_CHANNEL_TIME);
        try {
            if (StringUtils.isEmpty(lockId)) {
                throw new WitshareException(EnumResponseText.ErrorChannel);
            }

            sysChannelExample.clear();
            sysChannelExample.or().andNameEqualTo(name).andChannelGidEqualTo(channelGid);
            List<SysChannel> sysChannels1 = sysChannelMapper.selectByExample(sysChannelExample);
            if (CollectionUtils.isNotEmpty(sysChannels1)) {
                SysChannel sysChannel = sysChannels.get(0);
                if (sysChannel.getChannel().equals(name)) {
                    throw new WitshareException(EnumResponseText.ErrorChannelName);
                }
                throw new WitshareException(EnumResponseText.ErrorChannel);
            }
            sysChannelDb.setName(name);
            sysChannelDb.setNote(note);
            sysChannelDb.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            sysChannelMapper.updateByPrimaryKeySelective(sysChannelDb);
            this.deleteCacheChannel(sysChannelDb.getChannel());
        } catch (WitshareException e) {
            throw e;
        } catch (Exception e) {
            throw new WitshareException(EnumResponseText.SysError);
        } finally {
            distributedLocker.unlock(channelLockKey, lockId);
        }

    }

    public void cacheChannel(SysChannelBean sysChannelBean) {
        if (sysChannelBean == null) {
            return;
        }
        String redisKey = RedisKeyUtil.getChannelInfoKey(sysChannelBean.getChannel());
        String json = new Gson().toJson(sysChannelBean);
        redisCommonDao.setString(redisKey, json);

    }

    public void deleteCacheChannel(String channel) {
        String redisKey = RedisKeyUtil.getChannelInfoKey(channel);
        redisCommonDao.delRedisKey(redisKey);
    }

}
