package com.witshare.mars.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 2018/7/7.
 */
public interface CaptchaService {

    void checkCaptcha(String code, String imgToken);

    void genCaptcha(HttpServletRequest request, HttpServletResponse response, String token) throws IOException;
}
