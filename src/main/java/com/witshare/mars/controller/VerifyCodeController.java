package com.witshare.mars.controller;

import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.service.CaptchaService;
import com.witshare.mars.service.EmailService;
import com.witshare.mars.service.VerifyCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 验证码控制类
 */
@RequestMapping("/verify-code")
@Controller
public class VerifyCodeController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private VerifyCodeService verifyCodeService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private CaptchaService captchaService;

    /**
     * 注册时发送验证码
     */
    @ResponseBody
    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public ResponseBean sendRegisterVerificationCode(@RequestParam(value = "action") String action,
                                                     @RequestBody Map<String, String> requestBody) throws Exception {

        switch (action) {
            case "register":
                verifyCodeService.sendRegisterVerificationCode(requestBody);
                break;
            case "other":
                verifyCodeService.sendVerifyCode(requestBody);
                break;
            default:
                throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        return new ResponseBean(Boolean.TRUE);
    }

    /**
     * 获取图形验证码
     */
    @ResponseBody
    @RequestMapping(value = "/img", method = RequestMethod.GET)
    public void getVerifyCodeImg(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam("imgToken") String token) throws Exception {

        captchaService.genCaptcha(request, response, token);

    }

    /**
     * 校验图形验证码
     */
    @ResponseBody
    @RequestMapping(value = "/img", method = RequestMethod.POST)
    public void checkVerifyCodeImg(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam("imgVerifyCode") String code,
                                   @RequestParam("imgToken") String token) throws Exception {

        captchaService.checkCaptcha(code, token);

    }


}
