package com.witshare.mars.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.regexp.internal.RE;
import com.witshare.mars.pojo.dto.TokenDistributeBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.service.TokenDistributeService;
import com.witshare.mars.util.HttpClientUtil;
import com.witshare.mars.util.TokenHelper;
import com.witshare.mars.util.UUIDUtil;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenDistributeServiceImpl implements TokenDistributeService {

    private static final Logger logger = LoggerFactory.getLogger(TokenDistributeServiceImpl.class);

    @Value("${moon.host}")
    private String MOON_HOST;
    @Value("${moon.token_header_name}")
    private String MOON_TOKEN_HEADER_NAME;
    @Value("${moon.ak}")
    private String MOON_AK;
    @Value("${moon.sk}")
    private String MOON_SK;

    @Override
    public ResponseBean execTokenDistribute(TokenDistributeBean tokenDistributeBean) {
        String rid = UUIDUtil.get12UUID();
        String reqPath = "/token/distribute";
        String url = MOON_HOST + reqPath;
        Map<String, Object> body = new HashedMap<String, Object>() {{
            put("projectGid", tokenDistributeBean.getProjectGid());
            put("password", tokenDistributeBean.getPassword());
            put("payTxId", tokenDistributeBean.getPayTxId());
            put("userTxStatusArr", tokenDistributeBean.getUserTxStatus());
            put("platformTxStatusArr", tokenDistributeBean.getPlatformTxStatus());
        }};
        String reqToken = getToken(reqPath, "POST", null, JSON.toJSONString(body));
        logger.info("execTokenDistribute() request==>rid={}; url={}; param={}", rid, url, body);
        String result = HttpClientUtil.doPost(url, reqToken, MOON_TOKEN_HEADER_NAME);
        logger.info("execTokenDistribute() response==>rid={}; res={}", rid, result);
        ResponseBean responseBean = parseResult(result);
        return responseBean;
    }

    @Override
    public ResponseBean getTokenDistributeProgress(String projectGid) {
        String rid = UUIDUtil.get12UUID();
        String reqPath = "/token/distribute/progress";
        String params = "?projectGid=" + projectGid;
        String url = MOON_HOST + reqPath + params;
        String reqToken = getToken(reqPath, "GET", params, null);
        logger.info("getTokenDistributeProgress() request==>rid={}; url={}; param={}", rid, url, params);
        String result = HttpClientUtil.doGet(url, reqToken, MOON_TOKEN_HEADER_NAME);
        logger.info("getTokenDistributeProgress() response==>rid={}; res={}", rid, result);
        ResponseBean responseBean = parseResult(result);
        return responseBean;
    }


    /**
     * 生成token
     *
     * @param url
     * @param queryParam
     * @param bodyJson
     * @return
     */
    private String getToken(String url, String method, String queryParam, String bodyJson) {
        int ttTime = (int) (System.currentTimeMillis() / 1000 + 600L);
        TokenHelper tokenHelper = new TokenHelper(MOON_AK, MOON_SK);
        String token = tokenHelper.generateToken(url, method, queryParam, bodyJson, ttTime);
        return token;
    }

    private ResponseBean parseResult(String result) {
        ResponseBean responseBean = ResponseBean.newInstanceError("failed");
        if (StringUtils.isNotBlank(result)) {
            JSONObject res = JSON.parseObject(result);
            if ("0".equals(res.getString("code"))) {
                responseBean = ResponseBean.newInstanceSuccess(res.get("result"));
            } else {
                responseBean = ResponseBean.newInstanceError(res.getString("message"));
            }
        }
        return responseBean;
    }

}
