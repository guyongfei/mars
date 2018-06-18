package com.witshare.mars.controller;


import com.github.pagehelper.PageInfo;
import com.witshare.mars.pojo.dto.ProjectReqBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.pojo.vo.SysProjectBeanFrontInfoVo;
import com.witshare.mars.pojo.vo.SysProjectBeanFrontListVo;
import com.witshare.mars.service.SysProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目控制类
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    SysProjectService sysProjectService;

    /**
     * 获取项目列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseBean getSysProjects(ProjectReqBean projectReqBean) {

        PageInfo<SysProjectBeanFrontListVo> projects = sysProjectService.selectSysProjects(projectReqBean);
        return ResponseBean.newInstanceSuccess(projects);
    }

    /**
     * 获取单个项目详情
     */
    @RequestMapping(value = "/{projectGid}", method = RequestMethod.GET)
    public ResponseBean getProject(@PathVariable("projectGid") String projectGid) {

        SysProjectBeanFrontInfoVo frontInfoVo = sysProjectService.selectProject(projectGid);
        return ResponseBean.newInstanceSuccess(frontInfoVo);
    }


}
