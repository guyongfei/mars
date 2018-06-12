package com.witshare.mars.controller;


import com.github.pagehelper.PageInfo;
import com.witshare.mars.constant.EnumWitshare;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.dto.ProjectPageBean;
import com.witshare.mars.pojo.dto.ProjectReqBean;
import com.witshare.mars.pojo.dto.ProjectRespBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.service.SysProjectService;
import com.witshare.mars.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 项目控制类
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    SysProjectService sysProjectService;
    @Autowired
    SysUserService userService;

    /**
     * 获取项目列表
     *
     * @param projectReqBean
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseBean getSysProjects(ProjectReqBean projectReqBean) {
        ResponseBean responseBean = null;
        try {
            PageInfo<ProjectPageBean> projects = sysProjectService.selectSysProjects(projectReqBean);
            responseBean = new ResponseBean(projects);
        } catch (WitshareException e) {
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
            LOGGER.error("getSysProjectsError: {}", e);
        } catch (Exception e) {
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
            LOGGER.error("getSysProjectsError: {}", e);
        }
        return responseBean;
    }

    /**
     * 获取单个项目详情
     *
     * @param projectGid
     * @return
     */
    @RequestMapping(value = "/{projectGid}", method = RequestMethod.GET)
    public ResponseBean getProject(@PathVariable("projectGid") String projectGid) {
        ResponseBean responseBean = null;
        try {
            ProjectRespBean projectRespBean = sysProjectService.selectProject(projectGid);
            responseBean = new ResponseBean(projectRespBean);
        } catch (WitshareException e) {
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
            LOGGER.error("getProjectError: {}", e);
        } catch (Exception e) {
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
            LOGGER.error("getProjectError: {}", e);
        }
        return responseBean;
    }

    /**
     * 获取项目关注者信息
     *
     * @param request
     * @param response
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/followers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getProjectUsers(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestParam Map<String, String> requestBody) {
        ResponseBean responseBean = null;
        try {
            PageInfo<Map<String, Object>> projectUsers = userService.getProjectUsers(requestBody);
            responseBean = new ResponseBean(projectUsers);
        } catch (WitshareException e) {
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
            LOGGER.error("getProjectUsersError: {}", e);
        } catch (Exception e) {
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
            LOGGER.error("getProjectUsersError: {}", e);
        }
        return responseBean;
    }

    /**
     * 获取项目评级报告pdf
     *
     * @param id
     */
    @RequestMapping(value = "/pdf/{id}", method = RequestMethod.GET)
    public void getPdf(@PathVariable Long id) {
        sysProjectService.getProjectPdf(id);
    }

}
