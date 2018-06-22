package com.witshare.mars.controller;

import com.witshare.mars.constant.PropertiesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 管理页面控制类
 */
@Controller
@RequestMapping("/management/page")
public class ManagementPageController {

    private final static String CONTEXT_PATH = "contextPath";
    private final static String FRONT_PATH = "frontPath";

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private PropertiesConfig propertiesConfig;

    /**
     * 管理页面首页
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String managementPage(Map<String, Object> model) throws Exception {
        model.put(CONTEXT_PATH, propertiesConfig.contextPath);
        model.put(FRONT_PATH, propertiesConfig.frontPath);
        return "index";
    }


    /**
     * 项目页面
     */
    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public String modifyStartProjectPage(Map<String, Object> model) throws Exception {
        return "views/project";
    }




}
