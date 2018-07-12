package com.witshare.mars.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TokenHelper {
    private static final Logger logger = LoggerFactory.getLogger(TokenHelper.class);

    private String accessKey;

    private String secretKey;

    private static final String TOKEN_VERSION = "v2";

    public static final String X_IBEE_AUTH_TOKEN = "X-IbeeAuth-Token";

    private final List<String> allowedMethods = Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD");

    public TokenHelper() {
    }

    public TokenHelper(String ak, String sk) {
        this.accessKey = ak;
        this.secretKey = sk;
    }

    /**
     * generate the token according the request or response contents
     *
     * @param urlPath    the url of request
     * @param method     request method, must be one of 'GET', 'POST', 'DELETE', 'HEAD',
     *                   'PUT'
     * @param queryParam the query string of request
     * @param body       the post body for request, or response body
     * @param expireTime the token expired time
     * @return the token
     */
    public String generateToken(String urlPath, String method, String queryParam, String body, int expireTime) {
        if (accessKey == null || accessKey.isEmpty() || secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("Invalid AK or SK");
        }
        if (urlPath == null || urlPath.isEmpty()) {
            throw new IllegalArgumentException("Empty url path");
        }
        if (!allowedMethods.contains(method)) {
            throw new IllegalArgumentException("invalid request method");
        }
        String token;
        try {
            // |v2-{AK}-{ExpireTime}|{SK}|
            StringBuffer sbSign = new StringBuffer(String.format("|%s-%s-%d|%s|", TOKEN_VERSION, accessKey, expireTime, secretKey));

            // {UrlPath}|
            sbSign.append(decodeUtf8(urlPath)).append("|");

            // {Method}|
            sbSign.append(method).append("|");

            // {QueryParam}|
            if (StringUtils.isNoneBlank(queryParam)) {
                List<String> qsArray = new ArrayList<String>();
                for (String kv : queryParam.split("&")) {
                    String[] t = kv.split("=");
                    if (t.length > 1) {
                        qsArray.add(String.format("%s=%s", decodeUtf8(t[0]), decodeUtf8(t[1])));
                    } else {
                        qsArray.add(String.format("%s=", decodeUtf8(t[0])));
                    }
                }
                Collections.sort(qsArray);
                boolean first = true;
                for (String s : qsArray) {
                    if (first) {
                        first = false;
                    } else {
                        sbSign.append("&");
                    }
                    sbSign.append(s);
                }
            }
            sbSign.append("|");

            // {body}|
            if (StringUtils.isNoneBlank(body)) {
                sbSign.append(body);
            }
            sbSign.append("|");
//            logger.info("md5 sbSign before:{}", sbSign.toString());
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(sbSign.toString().getBytes("UTF-8"));

            // v2-{AK}-{ExpireTime}-{Signature}
            token = String.format("%s-%s-%s-%s", TOKEN_VERSION, accessKey, expireTime, new String(Hex.encodeHex(digest.digest())));
        } catch (Exception e) {
            logger.error("failed to decode url or query path,e.msg={}", e.getMessage());
            throw new IllegalStateException("Bad encoded url path or query string");
        }
        return token;
    }

    private static String decodeUtf8(String url) {
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            return url;
        }
    }

    /**
     * verify the token
     *
     * @param token      the token for verification
     * @param urlPath    the url of request
     * @param method     request method, must be one of 'GET', 'POST', 'DELETE', 'HEAD',
     *                   'PUT'
     * @param queryParam the query string of request
     * @param body       the post body for request, or response body
     */
    public boolean verifyToken(String token, String urlPath, String method, String queryParam, String body) {
        if (StringUtils.isBlank(token)) {
            logger.warn("token is null");
            return false;
        }
        try {
            String[] tokenParts = token.split("-");
            if (tokenParts.length != 4) {
                logger.warn("invalid token format");
                return false;
            }
            if (!TOKEN_VERSION.equals(tokenParts[0])) {
                logger.warn("invalid token protocol version");
                return false;
            }
            int expireTime = Integer.parseInt(tokenParts[2]);
            if (expireTime < System.currentTimeMillis() / 1000) {
                logger.warn("expired token");
                return false;
            }
            String tokenVerify = generateToken(urlPath, method, queryParam, body, expireTime);
            if (token.equals(tokenVerify)) {
                return true;
            }
        } catch (Exception e) {
            logger.error("failed to parse token '{}',e.msg={}", token, e.getMessage());
        }
        return false;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
