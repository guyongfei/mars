package com.witshare.mars.controller;


import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.service.ExportService;
import com.witshare.mars.service.PlatformAddressService;
import com.witshare.mars.service.ProjectDailyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/test")
public class TestController {


    @Autowired
    private ProjectDailyInfoService projectDailyInfoService;

    @Autowired
    private PlatformAddressService platformAddressService;

    @Autowired
    private ExportService exportService;

    @ResponseBody
    @RequestMapping(value = "/sync-project-summary")
    public ResponseBean syncProjectSummary() {
        projectDailyInfoService.syncDailyInfo();
        return ResponseBean.newInstanceSuccess();
    }

    @ResponseBody
    @RequestMapping(value = "/get-platform-address")
    public ResponseBean setPlatformAddress() {
        String platformAddress = platformAddressService.getPlatformAddress();
        return ResponseBean.newInstanceSuccess(platformAddress);
    }

    @ResponseBody
    @RequestMapping(value = "/get-excel/{projectGid}")
    public void getExcel(@PathVariable("projectGid") String projectGid) {
        exportService.exportProjectExcel(projectGid);
    }

}

