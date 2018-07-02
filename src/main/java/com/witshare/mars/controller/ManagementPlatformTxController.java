package com.witshare.mars.controller;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.RecordPlatformTxBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.service.PlatformTxInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目交易统计
 */
@Controller
@RequestMapping("/management")
public class ManagementPlatformTxController {


    @Autowired
    private PlatformTxInfoService platformTxInfoService;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping(value = "/platform-tx-infos", method = RequestMethod.GET)
    public ResponseBean list(RecordPlatformTxBean recordPlatformTxBean) {

        PageInfo<RecordPlatformTxBean> list = platformTxInfoService.getList(recordPlatformTxBean);
        return ResponseBean.newInstanceSuccess(list);
    }

    /**
     * 新增
     */
    @ResponseBody
    @RequestMapping(value = "/platform-tx-info", method = RequestMethod.POST)
    public ResponseBean add(@RequestBody RecordPlatformTxBean recordPlatformTxBean) {

        platformTxInfoService.add(recordPlatformTxBean);
        return ResponseBean.newInstanceSuccess();
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(value = "/platform-tx-info/{txHash}", method = RequestMethod.DELETE)
    public ResponseBean delete(@PathVariable("txHash") String txHash) {

        platformTxInfoService.delete(txHash);
        return ResponseBean.newInstanceSuccess();
    }


}
