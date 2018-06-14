package com.witshare.mars.controller;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.constant.EnumWitshare;
import com.witshare.mars.exception.WitshareException;
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
     *
     * @param request
     * @param response
     * @param requestBody
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public ResponseBean addProject(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestBody String requestBody) {
        ResponseBean responseBean;
        try {
            sysProjectService.save(requestBody);
            responseBean = new ResponseBean(Boolean.TRUE);
        } catch (WitshareException e) {
            LOGGER.error("addProject fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("addProject fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
        }
        return responseBean;
    }

    /**
     * 修改项目
     *
     * @param request
     * @param response
     * @param requestBody
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/project", method = RequestMethod.PUT)
    public ResponseBean updateProject(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestBody Map<String, Object> requestBody) {
        ResponseBean responseBean;
        try {
            sysProjectService.update(requestBody);
            responseBean = new ResponseBean(Boolean.TRUE);
        } catch (WitshareException e) {
            LOGGER.error("updateProject fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("updateProject fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
        }
        return responseBean;
    }


    /**
     *
     * 获取单个项目详情
     */
    @ResponseBody
    @RequestMapping(value = "/project/{projectGid}", method = RequestMethod.GET)
    public ResponseBean getProjectById(@PathVariable String projectGid) {
        ResponseBean responseBean;
        try {
            SysProjectBeanVo sysProjectBeanVo = sysProjectService.selectManagementByGid(projectGid);
            responseBean = new ResponseBean(Boolean.TRUE, "", sysProjectBeanVo);
        } catch (WitshareException e) {
            LOGGER.error("getProjectById fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("getProjectById fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
        }
        return responseBean;
    }


    @ResponseBody
    @RequestMapping(value = "/project/hide/{projectGid}", method = RequestMethod.PUT)
    public ResponseBean hideProject(@PathVariable String projectGid) {
        ResponseBean responseBean;
        try {
            sysProjectService.hideProject(projectGid);
            responseBean = new ResponseBean(Boolean.TRUE);
        } catch (WitshareException e) {
            LOGGER.error("hideProject fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("hideProject fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
        }
        return responseBean;
    }

    /**
     * 获取项目列表
     *
     * @param sysProjectBean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/project/list", method = RequestMethod.GET)
    public ResponseBean projectList(SysProjectBean sysProjectBean) {
        ResponseBean responseBean;
        try {
            PageInfo<SysProjectListVo> pageInfo = sysProjectService.selectManagementList(sysProjectBean);
            responseBean = new ResponseBean(Boolean.TRUE, "", pageInfo);
        } catch (WitshareException e) {
            LOGGER.error("projectList fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("projectList fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
        }
        return responseBean;
    }

}
