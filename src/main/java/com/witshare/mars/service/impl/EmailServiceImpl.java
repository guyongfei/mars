package com.witshare.mars.service.impl;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import com.sun.xml.internal.ws.api.message.Attachment;
import com.witshare.mars.constant.PropertiesConfig;
import com.witshare.mars.service.EmailService;
import org.apache.http.entity.ContentType;
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
            "<img  src=\"data:image/jpeg;base64,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\">\n" +
            "<div>\n" +
            "    <table>\n" +
            "        <tr>\n" +
            "            <td>Hello,</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td> You have received instructions to enter an one-time authentication code. Your code is:</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td align=\"center\">\n" +
            "                <label style=\"color: white;background-color: dodgerblue;\">%s\n" +
            "                </label>\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td>For security reasons, this code will expire in 15 minutes.</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td>TokenPie Team</td>\n" +
            "        </tr>\n" +
            "        <tr style=\"height: 60px\"><td></td></tr>\n" +
            "        <tr>\n" +
            "            <td align=\"center\">Follow us on:<a href=\"https://0.plus/#/joinchat/HvF0VRA8EMf4P-1lhUfoJg\">Telegram\n" +
            "                Messager</a>\n" +
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
