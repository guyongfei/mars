package com.witshare.mars.service;

import java.util.Map;

/**
 * 验证码实现类
 */
public interface VerifyCodeService {
    /**
     * 发送注册验证码
     *
     * @param requestBody
     */
    void sendRegisterVerificationCode(Map<String, String> requestBody);

    /**
     * 发送非注册验证码
     *
     * @param requestBody
     */

    void sendVerifyCode(Map<String, String> requestBody);

    /**
     * 校验注册验证码
     *
     * @param phoneNo
     * @param verifyCode
     * @return
     */
    boolean checkRegisterVerifyCode(String phoneNo, String verifyCode);

    /**
     * 校验飞注册验证码
     *
     * @param phoneNo
     * @param verifyCode
     * @return
     */
    boolean checkVerifyCode(String phoneNo, String verifyCode);
}
