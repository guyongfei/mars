package com.witshare.mars.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.witshare.mars.config.DistributedLocker;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.dao.mysql.SysChannelMapper;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.domain.SysChannel;
import com.witshare.mars.pojo.domain.SysChannelExample;
import com.witshare.mars.pojo.dto.SysChannelBean;
import com.witshare.mars.pojo.vo.SysChannelVo;
import com.witshare.mars.service.ChannelService;
import com.witshare.mars.service.SysProjectService;
import org.apache.commons.lang3.StringUtils;
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
    private SysProjectService sysProjectService;
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
            channelBean.setCreateTime(current)
                    .setUpdateTime(current);
            BeanUtils.copyProperties(channelBean, sysChannel);
            sysChannelMapper.insertSelective(sysChannel);
        } catch (WitshareException e) {
            throw e;
        } catch (Exception e) {
            throw new WitshareException(EnumResponseText.SysError);
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
        pageInfo.getList().forEach(p -> {
            SysChannelVo sysChannelVo = SysChannelVo.newInstance();
            BeanUtils.copyProperties(p, sysChannelVo);
            sysChannelVos.add(sysChannelVo);
        });
        pageInfo.setList(null);
        BeanUtils.copyProperties(pageInfo, pageInfo_);
        pageInfo_.setList(sysChannelVos);

        return pageInfo_;
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
    public void delete(Long id) {
        if (id == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        SysChannel sysChannel = sysChannelMapper.selectByPrimaryKey(id);
        if (sysChannel == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        sysChannelMapper.deleteByPrimaryKey(id);
    }


    @Override
    public void update(SysChannelBean sysChannelBean) {
        if (sysChannelBean == null) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String channel = sysChannelBean.getChannel();
        String name = sysChannelBean.getName();
        String note = sysChannelBean.getNote();
        Long id = sysChannelBean.getId();
        if (StringUtils.isAnyBlank(channel, name) || id == null) {
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
            sysChannelExample.or().andIdEqualTo(id);
            List<SysChannel> sysChannels = sysChannelMapper.selectByExample(sysChannelExample);
            if (CollectionUtils.isEmpty(sysChannels)) {
                throw new WitshareException(EnumResponseText.ErrorRequest);
            }
            sysChannelExample.clear();
            sysChannelExample.or().andChannelEqualTo(channel).andIdNotEqualTo(id);
            sysChannelExample.or().andNameEqualTo(name).andIdNotEqualTo(id);
            List<SysChannel> sysChannels1 = sysChannelMapper.selectByExample(sysChannelExample);
            if (CollectionUtils.isNotEmpty(sysChannels1)) {
                SysChannel sysChannel = sysChannels.get(0);
                if (sysChannel.getChannel().equals(name)) {
                    throw new WitshareException(EnumResponseText.ErrorChannelName);
                }
                throw new WitshareException(EnumResponseText.ErrorChannel);
            }
            SysChannel sysChannel = sysChannels.get(0);
            sysChannel.setId(id);
            sysChannel.setName(name);
            sysChannel.setNote(note);
            sysChannel.setChannel(channel);
            sysChannel.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            sysChannelMapper.updateByPrimaryKeySelective(sysChannel);
        } catch (WitshareException e) {
            throw e;
        } catch (Exception e) {
            throw new WitshareException(EnumResponseText.SysError);
        } finally {
            distributedLocker.unlock(channelLockKey, lockId);
        }

    }


}
