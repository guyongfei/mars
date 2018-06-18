package com.witshare.mars.controller;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.SysProjectBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.pojo.vo.SysProjectBeanVo;
import com.witshare.mars.pojo.vo.SysProjectListVo;
import com.witshare.mars.service.SysProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 项目管理控制类
 */
@Controller
@RequestMapping("/management")
public class ManagementProjectController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysProjectService sysProjectService;

    /**
     * 新增项目
     */
    @ResponseBody
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public ResponseBean addProject(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestBody String requestBody) {

        sysProjectService.save(requestBody);
        return ResponseBean.newInstanceSuccess();
    }

    /**
     * 修改项目
     */
    @ResponseBody
    @RequestMapping(value = "/project", method = RequestMethod.PUT)
    public ResponseBean updateProject(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestBody Map<String, Object> requestBody) {

        sysProjectService.update(requestBody);
        return ResponseBean.newInstanceSuccess();
    }


    /**
     * 获取单个项目详情
     */
    @ResponseBody
    @RequestMapping(value = "/project/{projectGid}", method = RequestMethod.GET)
    public ResponseBean getProjectById(@PathVariable String projectGid) {

        SysProjectBeanVo sysProjectBeanVo = sysProjectService.selectManagementByGid(projectGid);
        return ResponseBean.newInstanceSuccess(sysProjectBeanVo);
    }

    /**
     * 修改项目是否可用状态
     */
    @ResponseBody
    @RequestMapping(value = "/project/hide/{projectGid}", method = RequestMethod.PUT)
    public ResponseBean hideProject(@PathVariable String projectGid) {

        sysProjectService.hideProject(projectGid);
        return ResponseBean.newInstanceSuccess();
    }

    /**
     * 获取项目列表
     */
    @ResponseBody
    @RequestMapping(value = "/project/list", method = RequestMethod.GET)
    public ResponseBean projectList(SysProjectBean sysProjectBean) {

        PageInfo<SysProjectListVo> pageInfo = sysProjectService.selectManagementList(sysProjectBean);
        return ResponseBean.newInstanceSuccess(pageInfo);
    }

}
