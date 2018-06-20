package com.witshare.mars.controller;

import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.service.EmailService;
import com.witshare.mars.service.VerifyCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

}
