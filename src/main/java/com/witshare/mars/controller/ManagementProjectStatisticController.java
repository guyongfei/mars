package com.witshare.mars.controller;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.ProjectDailyInfoBean;
import com.witshare.mars.pojo.dto.ProjectSummaryBean;
import com.witshare.mars.pojo.dto.SysProjectBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.pojo.vo.SysProjectBeanVo;
import com.witshare.mars.pojo.vo.SysProjectListVo;
import com.witshare.mars.service.ProjectDailyInfoService;
import com.witshare.mars.service.ProjectSummaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目管理控制类
 */
@Controller
@RequestMapping("/management")
public class ManagementProjectStatisticController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProjectSummaryService projectSummaryService;
    @Autowired
    private ProjectDailyInfoService projectDailyInfoService;

    /**
     * 查询列表
     */
    @ResponseBody
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public ResponseBean list(ProjectSummaryBean projectSummaryBean) {

        PageInfo<ProjectSummaryBean> pageInfo = projectSummaryService.getList(projectSummaryBean);
        return ResponseBean.newInstanceSuccess(pageInfo);
    }

    /**
     * 查询单个项目
     */
    @ResponseBody
    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public ResponseBean get(ProjectDailyInfoBean projectDailyInfoBean) {

        PageInfo<ProjectDailyInfoBean> pageInfo = projectDailyInfoService.getList(projectDailyInfoBean);
        return ResponseBean.newInstanceSuccess(pageInfo);
    }


}
