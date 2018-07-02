package com.witshare.mars.controller;

import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.dto.SysUserBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户控制类
 */
@RestController
public class UserController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysUserService userService;

    /**
     * 注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseBean register(@RequestBody() Map<String, String> requestBody) throws Exception {

        userService.register(requestBody);
        return new ResponseBean(Boolean.TRUE);

    }

    /**
     * 修改密码
     */
    @RequestMapping(value = "/user/password", method = RequestMethod.POST)
    public ResponseBean resetPassword(@RequestParam(value = "action") String action,
                                      @RequestBody() Map<String, String> requestBody) throws Exception {

        switch (action) {
            case "reset":
                userService.putPassword(requestBody);
                break;
            case "modify":
                userService.postPassword(requestBody);
                break;
            default:
                throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        return new ResponseBean(Boolean.TRUE);

    }


    /**
     * 修改用户昵称
     */
    @RequestMapping(value = "/user/nickname", method = RequestMethod.POST)
    public ResponseBean modifyNickname(@RequestBody Map<String, String> requestBody) throws Exception {

        userService.postNickname(requestBody);
        return new ResponseBean(Boolean.TRUE);
    }

    /**
     * 修改用户头像
     */
    @RequestMapping(value = "/user/avatar", method = RequestMethod.POST)
    public ResponseBean modifyAvatar(@RequestBody Map<String, String> requestBody) throws Exception {

        userService.postAvatar(requestBody);
        return new ResponseBean(Boolean.TRUE);
    }

    /**
     * 获取用户详情（暂时只支持本人）
     */
    @RequestMapping(value = "/user-info", method = RequestMethod.GET)
    public ResponseBean getuserInfo() throws Exception {

        SysUserBean currentUser = userService.getCurrentUser().setSalt(null).setUserPassword(null);
        return ResponseBean.newInstanceSuccess(currentUser);
    }


}
