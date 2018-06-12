package com.witshare.mars.controller;

import com.witshare.mars.constant.EnumWitshare;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.pojo.vo.LoginVo;
import com.witshare.mars.service.SysUserService;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.CredentialsException;
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
 * 登录登出控制类
 */
@Controller
public class LoginController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysUserService sysUserService;


    /**
     * 登录
     *
     * @param requestBody
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseBean login(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestBody Map<String, String> requestBody) {
        ResponseBean responseBean;
        try {
            LoginVo loginVo = sysUserService.login(requestBody);
            responseBean = new ResponseBean(Boolean.TRUE, "", loginVo);
        } catch (CredentialsException e) {
            LOGGER.info("login fail.Error password.", e);
            responseBean = new ResponseBean(Boolean.FALSE, "Error password.");
        } catch (AccountException e) {
            LOGGER.info("login fail.Error userName.", e);
            responseBean = new ResponseBean(Boolean.FALSE, "Error userName.");
        } catch (WitshareException e) {
            LOGGER.info("login fail.", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        } catch (Exception e) {
            LOGGER.info("login fail.", e);
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
        }
        return responseBean;
    }


    /**
     * 登出
     */
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseBean logout() {

        sysUserService.logout();
        return new ResponseBean(Boolean.TRUE);

    }


}
