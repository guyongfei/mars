package com.witshare.mars.controller;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.config.DistributedLocker;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.dao.mysql.SysProjectMapper;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.domain.SysProject;
import com.witshare.mars.pojo.domain.SysProjectExample;
import com.witshare.mars.pojo.dto.SysProjectBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.pojo.vo.SysProjectBeanVo;
import com.witshare.mars.pojo.vo.SysProjectListVo;
import com.witshare.mars.service.PlatformAddressService;
import com.witshare.mars.service.SysProjectService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 项目管理控制类
 */
@Controller
@RequestMapping("/management")
public class ManagementProjectController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysProjectMapper sysProjectMapper;
    @Autowired
    private SysProjectService sysProjectService;
    @Autowired
    private PlatformAddressService platformAddressService;
    @Autowired
    private DistributedLocker distributedLocker;

    private final static String PLATFORM_ADDRESS_LOCK = "platformAddressLock:";
    private final static int PLATFORM_ADDRESS_TIME = 60;

    /**
     * 新增项目
     */
    @ResponseBody
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public ResponseBean addProject(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestBody String requestBody) {

        //加锁
        String platformAddress = platformAddressService.getPlatformAddress();
        if (StringUtils.isEmpty(platformAddress)) {
            throw new WitshareException(EnumResponseText.NoPlatformAddress);
        }
        String txLockKey = PLATFORM_ADDRESS_LOCK + platformAddress;
        String lockId = distributedLocker.lock(txLockKey, PLATFORM_ADDRESS_TIME);
        if (StringUtils.isEmpty(lockId)) {
            throw new WitshareException(EnumResponseText.NoPlatformAddress);
        }
        //保存
        sysProjectService.save(requestBody, platformAddress);

        //释放锁
        distributedLocker.unlock(txLockKey, lockId);

        return ResponseBean.newInstanceSuccess();
    }

    /**
     * 修改项目
     */
    @ResponseBody
    @RequestMapping(value = "/project", method = RequestMethod.PUT)
    public ResponseBean updateProject(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestBody String requestBody) {

        sysProjectService.update(requestBody);
        return ResponseBean.newInstanceSuccess();
    }


    /**
     * 获取单个项目详情
     */
    @ResponseBody
    @RequestMapping(value = "/project/{projectGid}", method = RequestMethod.GET)
    public ResponseBean getProjectById(@PathVariable String projectGid) {

        SysProjectBeanVo sysProjectBeanVo = sysProjectService.selectManagementByProjectGid(projectGid);
        return ResponseBean.newInstanceSuccess(sysProjectBeanVo);
    }

    /**
     * 根据条件获取项目
     */
    @ResponseBody
    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public ResponseBean getProjectByTokenAddress(@RequestParam("tokenAddress") String tokenAddress) {

        if (StringUtils.isAnyBlank(tokenAddress)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        SysProjectExample sysProjectExample = new SysProjectExample();
        sysProjectExample.or().andTokenAddressEqualTo(tokenAddress);
        List<SysProject> sysProjects = sysProjectMapper.selectByExample(sysProjectExample);
        if (CollectionUtils.isEmpty(sysProjects)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        return ResponseBean.newInstanceSuccess(sysProjects.get(0));
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
