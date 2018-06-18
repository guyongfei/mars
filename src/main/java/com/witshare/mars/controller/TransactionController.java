package com.witshare.mars.controller;

import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

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
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseBean postTransaction(@RequestBody Map<String, Object> requestBody) throws Exception {

        transactionService.save(requestBody);
        return new ResponseBean(Boolean.TRUE);
    }


}
