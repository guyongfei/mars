package com.witshare.mars.service;

/**
 * 发送邮件实现类
 */
public interface EmailService {
    /**
     * 发送验证码
     *
     * @param email
     * @param verifyCode
     */
    void sendVerifyCode(String email, String verifyCode);

}
