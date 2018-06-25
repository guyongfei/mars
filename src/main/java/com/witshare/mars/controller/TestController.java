package com.witshare.mars.controller;


import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.service.ProjectDailyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/test")
public class TestController {


    @Autowired
    private ProjectDailyInfoService projectDailyInfoService;

    @RequestMapping(value = "/sync-project-summary")
    public ResponseBean syncProjectSummary() {
        projectDailyInfoService.syncDailyInfo();
        return ResponseBean.newInstanceSuccess();
    }

}

