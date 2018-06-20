package com.witshare.mars.controller;

import com.witshare.mars.pojo.dto.RecordUserTxBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.pojo.vo.SysUserAddressVo;
import com.witshare.mars.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Map;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * 获取用户收发币地址
     */
    @RequestMapping(value = "/user-address", method = RequestMethod.GET)
    public ResponseBean getUserAddress(@PathParam("projectGid") String projectGid) throws Exception {

        SysUserAddressVo userAddress = transactionService.getUserAddress(projectGid);
        return ResponseBean.newInstanceSuccess(userAddress);
    }

    /**
     * 设置用户收发币地址
     */
    @RequestMapping(value = "/user-address", method = RequestMethod.POST)
    public ResponseBean setUserAddress(@RequestBody Map<String, String> requestBody) throws Exception {

        transactionService.setUserAddress(requestBody);
        return new ResponseBean(Boolean.TRUE);
    }

    /**
     * 提交交易
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseBean postTransaction(@RequestBody RecordUserTxBean recordUserTxBean) throws Exception {

        transactionService.save(recordUserTxBean);
        return new ResponseBean(Boolean.TRUE);
    }


}
