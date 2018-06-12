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
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String managementPage(Map<String, Object> model) throws Exception {
        model.put(CONTEXT_PATH, propertiesConfig.contextPath);
        model.put(FRONT_PATH, propertiesConfig.frontPath);
        return "index";
    }

    /**
     * 首页展示项目页面
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/starproject", method = RequestMethod.GET)
    public String modifyProjectPage(Map<String, Object> model) throws Exception {
        return "views/startProject";
    }

    /**
     * 项目页面
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public String modifyStartProjectPage(Map<String, Object> model) throws Exception {
        return "views/project";
    }

    /**
     * 敏感词页面
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sensitive", method = RequestMethod.GET)
    public String sensitivePage(Map<String, Object> model) throws Exception {
        return "views/sensitive";
    }

    /**
     * 广告页面
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/billboard", method = RequestMethod.GET)
    public String billBoardPage(Map<String, Object> model) throws Exception {
        return "views/billBoard";
    }

    /**
     * 首页用户页面
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage(Map<String, Object> model) throws Exception {
        return "views/user";
    }

    /**
     * 项目类型页面
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/projecttype", method = RequestMethod.GET)
    public String projectTypePage(Map<String, Object> model) throws Exception {
        return "views/projectType";
    }

    /**
     * 评论管理
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public String commentPage(Map<String, Object> model) throws Exception {
        return "views/comment";
    }

    /**
     * 基础配置页面
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/baseconfig", method = RequestMethod.GET)
    public String baseConfPage(Model model) throws Exception {
        return "views/baseConfig";
    }

}
