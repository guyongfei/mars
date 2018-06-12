package com.witshare.mars.controller;

import com.witshare.mars.constant.EnumWitshare;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.service.VerifyCodeService;
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
 * 验证码控制类
 */
@RequestMapping("/verifycode")
@Controller
public class VerifyCodeController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private VerifyCodeService verifyCodeService;

    /**
     * 注册时发送验证码
     *
     * @param request
     * @param response
     * @param requestBody
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/email/register", method = RequestMethod.POST)
    public ResponseBean sendRegisterVerificationCode(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     @RequestBody Map<String, String> requestBody) throws Exception {
        ResponseBean responseBean;
        try {
            verifyCodeService.sendRegisterVerificationCode(requestBody);
            responseBean = new ResponseBean(Boolean.TRUE);
        } catch (WitshareException e) {
            LOGGER.error("sendRegisterVerificationCode fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("sendRegisterVerificationCode fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
        }
        return responseBean;
    }

    /**
     * 非注册时发送验证码
     *
     * @param request
     * @param response
     * @param requestBody
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public ResponseBean sendEmailVerificationCode(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  @RequestBody Map<String, String> requestBody) throws Exception {
        ResponseBean responseBean;
        try {
            verifyCodeService.sendVerifyCode(requestBody);
            responseBean = new ResponseBean(Boolean.TRUE);
        } catch (WitshareException e) {
            LOGGER.error("sendEmailVerificationCode fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("sendEmailVerificationCode fail,{}", e);
            responseBean = new ResponseBean(Boolean.FALSE, EnumWitshare.SYS_ERROR.value());
        }
        return responseBean;
    }


}
