package com.witshare.mars.service.impl;

import com.witshare.mars.constant.PropertiesConfig;
import com.witshare.mars.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import static org.apache.commons.codec.CharEncoding.UTF_8;

/**
 * @see EmailService
 */
@Service
public class EmailServiceImpl implements EmailService {
    private final String VERIFY_CODE = "验证码";
    private final String VERIFY_CODE_STR = "尊敬的用户，您的验证码是%s，有效时间15分钟";
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource(name = "office365MailSender")
    private JavaMailSender office365Sender;

    @Resource(name = "hotmailMailSender")
    private JavaMailSender hotmailSender;

    @Autowired
    private PropertiesConfig propertiesConfig;

    /**
     * @see EmailService#sendVerifyCode(String, String)
     */
    @Override
    public void sendVerifyCode(String email, String verifyCode) {

        try {
            MimeMessage mimeMessage365 = office365Sender.createMimeMessage();
            MimeMessageHelper helper365 = new MimeMessageHelper(mimeMessage365, true, UTF_8);
            helper365.setFrom(propertiesConfig.mailAccountOffice365, propertiesConfig.mailSubjectName);
            helper365.setTo(email);
            helper365.setSubject(VERIFY_CODE);
            helper365.setText(String.format(VERIFY_CODE_STR, verifyCode), true);
            office365Sender.send(mimeMessage365);
            LOGGER.error("sendVerifyCode from defaultMail success.email:{},verifyCode:{}", email, verifyCode);
        } catch (Exception e) {
            MimeMessage mimeMessage = hotmailSender.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, UTF_8);
                helper.setFrom(propertiesConfig.mailAccountHotmail, propertiesConfig.mailSubjectName);
                helper.setTo(email);
                helper.setSubject(VERIFY_CODE);
                helper.setText(String.format(VERIFY_CODE_STR, verifyCode), true);
                hotmailSender.send(mimeMessage);
                LOGGER.error("sendVerifyCode from otherMail success.email:{},verifyCode:{}", email, verifyCode);
            } catch (Exception ex) {
                LOGGER.error("sendVerifyCode fail.email:{},verifyCode:{}", email, verifyCode, ex);
            }
        }
    }
}
