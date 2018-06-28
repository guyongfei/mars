package com.witshare.mars.controller;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.RecordUserTxBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.pojo.vo.RecordUserTxListVo;
import com.witshare.mars.pojo.vo.RecordUserTxVo;
import com.witshare.mars.pojo.vo.UserTxInfoVo;
import com.witshare.mars.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;


@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * 获取交易需要的相关信息
     */
    @RequestMapping(value = "/transaction/info", method = RequestMethod.GET)
    public ResponseBean getUserAddress(@PathParam("projectGid") String projectGid) throws Exception {

        UserTxInfoVo userTxInfoVo = transactionService.getUserTxInfo(projectGid);
        return ResponseBean.newInstanceSuccess(userTxInfoVo);
    }

    /**
     * 设置用户收发币地址
     */
    @RequestMapping(value = "/transaction/user-address", method = RequestMethod.POST)
    public ResponseBean setUserAddress(@RequestBody Map<String, String> requestBody) throws Exception {

        transactionService.setUserAddress(requestBody);
        return new ResponseBean(Boolean.TRUE);
    }

    /**
     * 提交交易
     */
    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ResponseBean postTransaction(@RequestBody RecordUserTxBean recordUserTxBean) throws Exception {

        transactionService.save(recordUserTxBean);
        return new ResponseBean(Boolean.TRUE);
    }

    /**
     * 获取用户交易列表
     */
    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public ResponseBean getTransactions(RecordUserTxBean recordUserTxBean) throws Exception {

        PageInfo<RecordUserTxListVo> pageInfo = transactionService.selectList(recordUserTxBean);
        return ResponseBean.newInstanceSuccess(pageInfo);
    }

    /**
     * 获取用户单个交易
     */
    @RequestMapping(value = "/transaction/{payTxId}", method = RequestMethod.GET)
    public ResponseBean getTransaction(@PathVariable("payTxId") Long payTxId) throws Exception {

        RecordUserTxVo recordUserTxVo = transactionService.select(payTxId);
        return ResponseBean.newInstanceSuccess(recordUserTxVo);
    }


}
