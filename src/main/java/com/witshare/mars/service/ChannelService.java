package com.witshare.mars.service;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.SysChannelBean;
import com.witshare.mars.pojo.vo.SysChannelVo;


public interface ChannelService {

    void save(SysChannelBean channelBean);

    void update(SysChannelBean sysChannelBean);

    void delete( Long id);

    PageInfo<SysChannelVo> list(SysChannelBean sysChannelBean);

    SysChannelVo get(SysChannelBean sysChannelBean);

}
