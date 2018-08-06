package com.witshare.mars.service.impl;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
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
import java.io.IOException;

import static org.apache.commons.codec.CharEncoding.UTF_8;

/**
 * @see EmailService
 */
@Service
public class EmailServiceImpl implements EmailService {
    private final String VERIFY_CODE = "Your TokenPie Security Code";
    private final String VERIFY_CODE_STR = "<html>\n" +
            "<head></head>\n" +
            "<body>\n" +
            "<div>\n" +
            "    <table>\n" +
            "        <tr>\n" +
            "            <td>Hello,</td>\n" +
            "        </tr>\n" +
            "        <tr >\n" +
            "            <td > <p style=\"margin-left: 50px\">You have received instructions to enter an one-time authentication code. Your code is:</p></td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td align=\"center\">\n" +
            "                <label style=\"color: white;background-color: dodgerblue;margin-left: 50px\">%s\n" +
            "                </label>\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td><p style=\"margin-left: 50px\">For security reasons, this code will expire in 15 minutes.</p></td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td><p style=\"margin-left: 50px\">TokenPie Team</p></td>\n" +
            "        </tr>\n" +
            "        <tr style=\"height: 60px\"><td></td></tr>\n" +
            "        <tr>\n" +
            "            <td align=\"center\">\n" +
            "                <img alt=\"tokenpie\" width=\"25px\" src=\"https://tokenpie.s3-ap-northeast-1.amazonaws.com/doc/logo/69b04f15a07145eca5f48f19a3436019.jpeg?_d=1533341481634\">\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td align=\"center\">Follow us on:<a href=\"https://t.me/joinchat/H28ZCVFCu6gnWuFp2wXnYg\">Telegram\n" +
            "                Messenger</a>\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td align=\"center\">Sent by © TokenPie.<a href=\" https://www.tokenpie.io/\"> https://www.tokenpie.io/</a></td>\n" +
            "        </tr>\n" +
            "    </table>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";
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

    @Override
    public boolean service(String toEmail, String verifyCode) throws IOException, SendGridException {
        SendGrid sendgrid = new SendGrid(propertiesConfig.sendGridUserName, propertiesConfig.sendGridPassword);
        if (toEmail == null) {
            return false;
        }
        SendGrid.Email email = new SendGrid.Email();
        email.addTo(toEmail)
                .setFrom(propertiesConfig.sendGridSender)
                .setFromName(propertiesConfig.mailSubjectName)
                .setSubject(VERIFY_CODE)
                .setHtml(String.format(VERIFY_CODE_STR, verifyCode));
        try {
            SendGrid.Response response = sendgrid.send(email);
            if (response.getCode() == 200) {
                LOGGER.error("sendVerifyCode from sendgrid success.email:{},verifyCode:{}", email, verifyCode);
                return true;
            }
        } catch (SendGridException e) {
            e.printStackTrace();
        }
        LOGGER.error("sendVerifyCode from sendgrid fail.email:{},verifyCode:{}", email, verifyCode);
        return false;
    }
}
