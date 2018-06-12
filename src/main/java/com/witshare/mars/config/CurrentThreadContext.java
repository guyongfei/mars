package com.witshare.mars.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 本地线程类
 */
public class CurrentThreadContext {
    private static ThreadLocal<Map> _requestMap = new ThreadLocal<Map>();
    private static ThreadLocal<HttpServletResponse> _response = new ThreadLocal<HttpServletResponse>();
    private static ThreadLocal<HttpServletRequest> _request = new ThreadLocal<HttpServletRequest>();
    private static ThreadLocal<String> _token = new ThreadLocal<String>();
    private static ThreadLocal<String> _email = new ThreadLocal<String>();
    private static ThreadLocal<String> _table = new ThreadLocal<String>();
    private static ThreadLocal<String> _I18N = new ThreadLocal<String>();

    public static HttpServletResponse getResponse() {
        HttpServletResponse response = _response.get();
        return response;
    }

    public static void setResponse(HttpServletResponse response) {
        _response.set(response);
    }

    public static void remove() {
        removeToken();
        removeI18N();
        removeInternationalTableName();
        removeRequest();
        removeResponse();
        removeEmail();
        removeRequestMap();
    }

    public static Map getRequestMap() {
        return _requestMap.get();
    }

    public static void setRequestMap(Map requestMap) {
        _requestMap.set(requestMap);
    }

    public static void removeRequestMap() {
        _requestMap.remove();
    }

    public static void removeResponse() {
        _response.remove();
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = _request.get();
        return request;
    }

    public static void setRequest(HttpServletRequest request) {
        _request.set(request);
    }

    public static void removeRequest() {
        _request.remove();
    }

    public static String getToken() {
        String token = _token.get();
        return token;
    }

    public static void setToken(String token) {
        _token.set(token);
    }

    public static void removeToken() {
        _token.remove();
    }

    public static String getInternationalTableName() {
        return _table.get();
    }

    public static void setInternationalTableName(String tableName) {
        _table.set(tableName);
    }

    public static void removeInternationalTableName() {
        _table.remove();
    }


    public static String getI18N() {
        return _I18N.get();
    }

    public static void setI18N(String I18N) {
        _I18N.set(I18N);
    }

    public static void removeI18N() {
        _I18N.remove();
    }

    public static String getEmail() {
        return _email.get();
    }

    public static void setEmail(String email) {
        _email.set(email);
    }

    public static void removeEmail() {
        _email.remove();
    }
}
