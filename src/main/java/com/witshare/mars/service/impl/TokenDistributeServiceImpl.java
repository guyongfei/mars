package com.witshare.mars.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.witshare.mars.config.CurrentThreadContext;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.pojo.dto.SysProjectBean;
import com.witshare.mars.pojo.dto.TokenDistributeBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.service.SysProjectService;
import com.witshare.mars.service.TokenDistributeService;
import com.witshare.mars.util.HttpClientUtil;
import com.witshare.mars.util.RedisKeyUtil;
import com.witshare.mars.util.TokenHelper;
import com.witshare.mars.util.UUIDUtil;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

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

    @Autowired
    private RedisCommonDao redisCommonDao;
    @Autowired
    private SysProjectService sysProjectService;

    @Override
    public ResponseBean execTokenDistribute(TokenDistributeBean tokenDistributeBean) {
        String keystore = tokenDistributeBean.getKeystore();
        transArray(tokenDistributeBean);
        SysProjectBean sysProjectBean = sysProjectService.selectByProjectGid(tokenDistributeBean.getProjectGid());
        if (Objects.nonNull(sysProjectBean)) {
            String address = sysProjectBean.getPlatformAddress();
            if (StringUtils.isNoneBlank(address, keystore)) {
                //缓存keystore
                String walletV3JsonKey = RedisKeyUtil.getWalletV3JsonKey(address);
                redisCommonDao.setString(walletV3JsonKey, keystore);
                redisCommonDao.expire(walletV3JsonKey, 60 * 10);
            }
        }
        String rid = UUIDUtil.get12UUID();
        String reqPath = "/token/distribute";
        String url = MOON_HOST + reqPath;
        Map<String, Object> body = new HashedMap<String, Object>() {{
            put("projectGid", tokenDistributeBean.getProjectGid());
            put("password", tokenDistributeBean.getPassword());
            put("id", tokenDistributeBean.getId());
            put("userTxStatusArr", tokenDistributeBean.getUserTxStatus());
            put("platformTxStatusArr", tokenDistributeBean.getPlatformTxStatus());
        }};
        String bodyJson = JSON.toJSONString(body);
        String reqToken = getToken(reqPath, "POST", null, bodyJson);
        //记录当前操作人
        String email = CurrentThreadContext.getEmail();
        //移除密码
        body.remove("password");
        logger.info("execTokenDistribute() request==>email={};rid={}; url={}; param={}", email, rid, url, body);
        String result = HttpClientUtil.doPost(url, bodyJson, reqToken, MOON_TOKEN_HEADER_NAME);
        logger.info("execTokenDistribute() response==>rid={}; res={}", rid, result);
        ResponseBean responseBean = parseResult(result);
        return responseBean;
    }

    void transArray(TokenDistributeBean tokenDistributeBean) {
        String userTxStatusStr = tokenDistributeBean.getUserTxStatusStr();
        String platformTxStatusStr = tokenDistributeBean.getPlatformTxStatusStr();
        LinkedList<Integer> userTx = new LinkedList<>();
        LinkedList<Integer> platformStatus = new LinkedList<>();
        if (!StringUtils.isAnyBlank(userTxStatusStr)) {
            String[] split = userTxStatusStr.split(",");
            Arrays.stream(split).forEach(p -> {
                userTx.add(Integer.parseInt(p));
            });
            Integer[] userTxStatus = new Integer[userTx.size()];
            Integer[] integers = userTx.toArray(userTxStatus);
            tokenDistributeBean.setUserTxStatus(integers);
        }
        if (!StringUtils.isAnyBlank(platformTxStatusStr)) {
            String[] split = platformTxStatusStr.split(",");
            Arrays.stream(split).forEach(p -> {
                platformStatus.add(Integer.parseInt(p));
            });
            Integer[] platformStatuses = new Integer[platformStatus.size()];
            Integer[] integers = platformStatus.toArray(platformStatuses);
            tokenDistributeBean.setPlatformTxStatus(integers);
        }


    }

    @Override
    public ResponseBean getTokenDistributeProgress(String projectGid) {
        String rid = UUIDUtil.get12UUID();
        String reqPath = "/token/distribute/progress";
        String params = "projectGid=" + projectGid;
        String url = MOON_HOST + reqPath;
        String reqToken = getToken(reqPath, "GET", params, null);
        logger.info("getTokenDistributeProgress() request==>rid={}; url={}; param={}", rid, url, params);
        String result = HttpClientUtil.doGet(url + "?" + params, reqToken, MOON_TOKEN_HEADER_NAME);
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
    @Override
    public String getToken(String url, String method, String queryParam, String bodyJson) {
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
