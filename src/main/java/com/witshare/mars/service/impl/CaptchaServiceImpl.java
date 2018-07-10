package com.witshare.mars.service.impl;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.witshare.mars.config.DistributedLocker;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.service.CaptchaService;
import com.witshare.mars.util.RedisKeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Resource(name = "captchaProducer")
    private DefaultKaptcha captchaProducer;

    @Autowired
    private RedisCommonDao redisCommonDao;
    @Autowired
    private DistributedLocker distributedLocker;

    private final static String CAPTCHA_LOCK = "captchaLock:";
    private final static int CAPTCHA_TIME = 60;

    @Override
    public void checkCaptcha(String code, String token) {

        if (StringUtils.isAnyBlank(code, token)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String verifyCodeImgKey = RedisKeyUtil.getVerifyCodeImgKey(token);
        boolean existsKey = redisCommonDao.existsKey(verifyCodeImgKey);
        if (!existsKey) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String captchaLockKey = CAPTCHA_LOCK + token;
        boolean locked = distributedLocker.isLocked(captchaLockKey);
        if (locked) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String redisCode = redisCommonDao.getString(verifyCodeImgKey);
        if (!StringUtils.equals(redisCode, code)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        distributedLocker.lock(captchaLockKey, CAPTCHA_TIME);
    }


    @Override
    public void genCaptcha(HttpServletRequest request, HttpServletResponse response, String token) throws IOException {

        if (StringUtils.isAnyBlank(token) || token.trim().length() != 32) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        String capText;
        capText = this.gen(request, response);
        // 自定义的缓存，保存图片验证码，保存 5 min 有效
        String verifyCodeImgKey = RedisKeyUtil.getVerifyCodeImgKey(token);
        redisCommonDao.put(verifyCodeImgKey, capText, 5, TimeUnit.MINUTES);
    }

    /**
     * 采用了 Google 提供的 Kaptcha 验证码库生成验证码
     *
     * @param request  HttpServletRequest 请求
     * @param response HttpServletResponse 返回，用于输出验证码图片流
     */
    private String gen(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // p3p 跨域
        response.setHeader("P3P", "CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");

        // create the text for the image
        String capText = captchaProducer.createText();
        // store the text in the session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return capText;
    }

}
