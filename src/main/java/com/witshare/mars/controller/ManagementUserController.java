package com.witshare.mars.controller;

import com.witshare.mars.pojo.dto.SysUserBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户管理类
 */
@Controller
@RequestMapping("/management")
public class ManagementUserController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysUserService sysUserService;

    /**
     * 隐藏用户
     */
    @ResponseBody
    @RequestMapping(value = "/user/hide/{id}", method = RequestMethod.PUT)
    public ResponseBean hideUser(@PathVariable Long id) {

        sysUserService.hideUser(id);
        return ResponseBean.newInstanceSuccess();
    }

    /**
     * 获取用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseBean getUserInfo(@PathVariable Long id) {

        SysUserBean sysUserBean = sysUserService.getByUserId(id, null);
        return ResponseBean.newInstanceSuccess(sysUserBean);
    }

    /**
     * \
     * 修改用户信息
     *
     * @param id
     * @param requestBody
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseBean modifyUserInfo(@PathVariable Long id,
                                       @RequestBody Map<String, String> requestBody) {

        sysUserService.modifyUserInfo(id, requestBody);
        return ResponseBean.newInstanceSuccess();

    }
}
