package com.witshare.mars.controller;


import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.pojo.vo.IndexBean;
import com.witshare.mars.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/")
public class IndexController {


    @Autowired
    private IndexService indexService;


    @ResponseBody
    @RequestMapping(value = "/index")
    public ResponseBean syncProjectSummary() {
        IndexBean indexBean = indexService.get();
        return ResponseBean.newInstanceSuccess(indexBean);
    }

}

