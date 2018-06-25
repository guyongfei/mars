package com.witshare.mars.controller;

import com.witshare.mars.pojo.dto.TokenDistributeBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.service.TokenDistributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 认购记录控制类
 */
@Controller
@RequestMapping("/pay-record")
public class ManagementRecordUserTxController {

    @Autowired
    private TokenDistributeService tokenDistributeService;

    /**
     * 打币
     */
    @ResponseBody
    @RequestMapping(value = "/token/distribute", method = RequestMethod.POST)
    public ResponseBean addProject(@RequestBody TokenDistributeBean tokenDistributeBean) {
        return tokenDistributeService.execTokenDistribute(tokenDistributeBean);
    }

    /**
     * 获取打币进度
     */
    @ResponseBody
    @RequestMapping(value = "/token/distribute/progress", method = RequestMethod.GET)
    public ResponseBean addProject(String projectGid) {
        return tokenDistributeService.getTokenDistributeProgress(projectGid);
    }


}
