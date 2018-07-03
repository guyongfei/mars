package com.witshare.mars.controller;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.domain.PlatformAddress;
import com.witshare.mars.pojo.dto.BasePageBean;
import com.witshare.mars.pojo.dto.PlatformAddressBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.service.PlatformAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户管理类
 */
@Controller
@RequestMapping("/management")
public class ManagementPlatformAddressController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private PlatformAddressService platformAddressService;

    /**
     * 添加地址
     */
    @ResponseBody
    @RequestMapping(value = "/platform-address", method = RequestMethod.POST)
    public ResponseBean add(@RequestBody Map<String, String> requestBody) {
        platformAddressService.add(requestBody);
        return ResponseBean.newInstanceSuccess();
    }

    /**
     * 获取列表
     */
    @ResponseBody
    @RequestMapping(value = "/platform-addresses", method = RequestMethod.GET)
    public ResponseBean list(BasePageBean basePageBean) {

        PageInfo<PlatformAddress> pageInfo = platformAddressService.getList(basePageBean);
        return ResponseBean.newInstanceSuccess(pageInfo);
    }

    /**
     * 获取平台地址数量
     */
    @ResponseBody
    @RequestMapping(value = "/platform-addresses/count", method = RequestMethod.GET)
    public ResponseBean count(BasePageBean basePageBean) {

        int count = platformAddressService.count();
        return ResponseBean.newInstanceSuccess(count);
    }

    /**
     * 删除地址
     */
    @ResponseBody
    @RequestMapping(value = "/platform-address/{address}", method = RequestMethod.DELETE)
    public ResponseBean delete(@PathVariable("address") String address) {
        platformAddressService.delete(address);
        return ResponseBean.newInstanceSuccess();
    }


}
