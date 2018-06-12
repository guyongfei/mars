package com.witshare.mars.controller;

import com.witshare.mars.constant.EnumWitshare;
import com.witshare.mars.exception.WitshareException;
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
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/hide/{id}", method = RequestMethod.PUT)
    public ResponseBean hideUser(@PathVariable Long id) {
        ResponseBean responseBean;
        try {
            sysUserService.hideUser(id);
            responseBean = new ResponseBean(Boolean.TRUE);
        } catch (WitshareException e) {
            LOGGER.error("hideUser fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("hideUser fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
        }
        return responseBean;
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseBean getUserInfo(@PathVariable Long id) {
        ResponseBean responseBean;
        try {
            SysUserBean sysUserBean = sysUserService.getByUserId(id, null);
            responseBean = new ResponseBean(Boolean.TRUE, "", sysUserBean);
        } catch (WitshareException e) {
            LOGGER.error("modifyUserInfo fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("modifyUserInfo fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
        }
        return responseBean;
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
    public ResponseBean modifyUserInfo(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        ResponseBean responseBean;
        try {
            sysUserService.modifyUserInfo(id, requestBody);
            responseBean = new ResponseBean(Boolean.TRUE);
        } catch (WitshareException e) {
            LOGGER.error("modifyUserInfo fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("modifyUserInfo fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
        }
        return responseBean;
    }
}
