package com.witshare.mars.controller;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.SysChannelBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.pojo.vo.SysChannelVo;
import com.witshare.mars.service.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 渠道管理控制类
 */
@Controller
@RequestMapping("/management")
public class ManagementChannelController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ChannelService channelService;


    /**
     * 新增
     */
    @ResponseBody
    @RequestMapping(value = "/channel", method = RequestMethod.POST)
    public ResponseBean save(@RequestBody SysChannelBean channelBean) {

        //保存
        channelService.save(channelBean);
        return ResponseBean.newInstanceSuccess();
    }

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping(value = "/channels", method = RequestMethod.GET)
    public ResponseBean list(SysChannelBean sysChannelBean) {

        PageInfo<SysChannelVo> pageInfo = channelService.list(sysChannelBean);
        return ResponseBean.newInstanceSuccess(pageInfo);
    }

    /**
     * 详情
     */
    @ResponseBody
    @RequestMapping(value = "/channel", method = RequestMethod.GET)
    public ResponseBean info(SysChannelBean sysChannelBean) {

        SysChannelVo sysChannelVo = channelService.get(sysChannelBean);
        return ResponseBean.newInstanceSuccess(sysChannelVo);
    }


    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/channel", method = RequestMethod.PUT)
    public ResponseBean update(@RequestBody SysChannelBean channelBean) {

        channelService.update(channelBean);
        return ResponseBean.newInstanceSuccess();
    }


    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(value = "/channel/{channelGid}", method = RequestMethod.DELETE)
    public ResponseBean getProjectById(@PathVariable("channelGid") String channelGid) {

        channelService.delete(channelGid);
        return ResponseBean.newInstanceSuccess();
    }


}
