package com.witshare.mars.controller;

import com.witshare.mars.constant.EnumWitshare;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.service.SysProjectService;
import com.witshare.mars.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户控制类
 */
@Controller
public class UserController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysProjectService sysProjectService;

    /**
     * 注册
     *
     * @param request
     * @param response
     * @param requestBody
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseBean register(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestBody() Map<String, String> requestBody) throws Exception {
        ResponseBean responseBean;
        try {
            userService.register(requestBody);
            responseBean = new ResponseBean(Boolean.TRUE);
        } catch (WitshareException e) {
            LOGGER.error("sendVerificationCode fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("sendVerificationCode fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
        }
        return responseBean;
    }

    /**
     * 修改密码
     *
     * @param request
     * @param response
     * @param requestBody
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public ResponseBean resetPassword(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestBody() Map<String, String> requestBody) throws Exception {
        ResponseBean responseBean;
        try {
            userService.putPassword(requestBody);
            responseBean = new ResponseBean(Boolean.TRUE);
        } catch (WitshareException e) {
            LOGGER.error("resetPassword fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("resetPassword fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
        }
        return responseBean;
    }

    /**
     * 重置密码
     *
     * @param request
     * @param response
     * @param requestBody
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/user/password", method = RequestMethod.POST)
    public ResponseBean modifyPassword(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestBody Map<String, String> requestBody) throws Exception {
        ResponseBean responseBean;
        try {
            userService.postPassword(requestBody);
            responseBean = new ResponseBean(Boolean.TRUE);
        } catch (WitshareException e) {
            LOGGER.error("modifyPassword fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("modifyPassword fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
        }
        return responseBean;
    }

    /**
     * 修改用户昵称
     *
     * @param request
     * @param response
     * @param requestBody
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/user/nickname", method = RequestMethod.POST)
    public ResponseBean modifyNickname(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestBody Map<String, String> requestBody) throws Exception {
        ResponseBean responseBean;
        try {
            userService.postNickname(requestBody);
            responseBean = new ResponseBean(Boolean.TRUE);
        } catch (WitshareException e) {
            LOGGER.error("modifyNickname fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("modifyNickname fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
        }
        return responseBean;
    }

    /**
     * 修改用户头像
     *
     * @param request
     * @param response
     * @param requestBody
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/user/avatar", method = RequestMethod.POST)
    public ResponseBean modifyAvatar(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestBody Map<String, String> requestBody) throws Exception {
        ResponseBean responseBean;
        try {
            userService.postAvatar(requestBody);
            responseBean = new ResponseBean(Boolean.TRUE);
        } catch (WitshareException e) {
            LOGGER.error("modifyAvatar fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("modifyAvatar fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
        }
        return responseBean;
    }


}
