package com.witshare.mars.aspect;


import com.google.gson.Gson;
import com.witshare.mars.config.CurrentThreadContext;
import com.witshare.mars.constant.CacheConsts;
import com.witshare.mars.constant.EnumI18NProject;
import com.witshare.mars.constant.PropertiesConfig;
import com.witshare.mars.constant.StartupRunnerDefault;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.pojo.dto.SysUserBean;
import com.witshare.mars.pojo.util.LogApiBean;
import com.witshare.mars.service.SysUserService;
import com.witshare.mars.util.JsonUtils;
import com.witshare.mars.util.RedisKeyUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.BufferedReader;
import java.util.*;

import static com.witshare.mars.constant.CacheConsts.PROJECT_NAME;


@Aspect
@Component
public class LogAspect implements ThrowsAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);
    @Autowired
    private PropertiesConfig propertiesConfig;
    @Autowired
    private StartupRunnerDefault startupRunnerDefault;
    @Autowired
    private RedisCommonDao redisCommonDao;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 装载日志信息
     */
    public static String writeApiLog(Map requestMap, Object resultObj, HttpServletResponse response) {
        requestMap.put("endTime", new Date());
        LogApiBean logApiBeanBuilder = LogApiBean.newInstance()
                .setProjectName(PROJECT_NAME)
                .setRequest(requestMap)
                .setResponse(resultObj)
                .setResponseStatus(response.getStatus());
        String result = JsonUtils.toGsonPretty(logApiBeanBuilder);
        LOGGER.info(result);
        return result;
    }

    /**
     * 获取请求信息
     */
    public static Map getRequestMap(ProceedingJoinPoint joinPoint, HttpServletRequest request, Object requestBody) {

        LinkedHashMap<String, Object> requestMap = new LinkedHashMap<>();
        //将请求signature置入
        Signature signature = joinPoint.getSignature();
        requestMap.put("signature", signature);
        //将请求URI置入
        String requestURI = request.getRequestURI();
        requestMap.put("uri", requestURI);
        //将请求头置入
        HashMap<Object, Object> requestHeader = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            requestHeader.put(headerName, headerValue);
        }
        requestMap.put("requestHeader", requestHeader);
        requestMap.put("requestCookie", new Gson().toJson(request.getCookies()));

        //将请求地址变量置入
        HashMap<Object, Object> pathVariable = new HashMap<>();
        NativeWebRequest webRequest = new ServletWebRequest(request);
        Map<String, String> uriTemplateVars = (Map<String, String>) webRequest
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        pathVariable.putAll(uriTemplateVars);
        requestMap.put("pathVariable", pathVariable);

        //将请求参数置入
        Map<String, String[]> parameterMap = request.getParameterMap();
        HashMap<Object, Object> requestParam = new HashMap<>();
        if (parameterMap != null && parameterMap.size() > 0) {
            Iterator<Map.Entry<String, String[]>> iterator = parameterMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String[]> next = iterator.next();
                String s = next.getValue()[0];
                int length = s.length();
                if (length > 100) {
                    s = s.substring(0, 40) + "*****" + s.substring(length - 40);
                }
                String key = next.getKey();
                if ("password".equals(key) || "keystore".equals(key)) {
                    s = s.length() + "";
                }
                requestParam.put(next.getKey(), s);
            }
        }
        requestMap.put("requestParam", requestParam);

        //将请求体置入
        String body = (String) requestBody;
        if (body.length() > 1500) {
            try {
                Map<String, Object> map = JsonUtils.jsonToPojo((String) requestBody, Map.class);
                HashMap<String, Object> newMap = new HashMap<>();
                Set<Map.Entry<String, Object>> entries = map.entrySet();
                Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    try {
                        Map.Entry<String, Object> next = iterator.next();
                        String key = next.getKey();
                        Object value = next.getValue();
                        if (value instanceof String && ((String) value).length() > 100) {
                            value = ((String) value).substring(0, 40) + "*****" + ((String) value).substring(((String) value).length() - 40);
                        }
                        if (value instanceof String){
                            if ("password".equals(key) || "keystore".equals(key)) {
                                value = ((String) value).length() + "";
                            }
                        }
                        newMap.put(key, value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                requestMap.put("requestBody", newMap);
            } catch (Exception e) {
                e.printStackTrace();
                requestMap.put("requestBody", JSONObject.stringToValue((String) requestBody));
            }

        } else {
            requestMap.put("requestBody", JSONObject.stringToValue((String) requestBody));
        }

        requestMap.put("startTime", new Date());

        return requestMap;
    }

    /**
     * 读取JsonBody
     *
     * @param request
     * @return
     */
    public static String charReader(HttpServletRequest request) {

        try {
            BufferedReader br = request.getReader();
            String str, wholeStr = "";
            while ((str = br.readLine()) != null) {
                wholeStr += str;
            }
            return wholeStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Pointcut("execution(* com.witshare.mars.controller..*.*(..))")
    public void controllerMethod() {
    }

    @Around("controllerMethod()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取必要的参数
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = CurrentThreadContext.getResponse();
        String requestBody = charReader(request);
        Map requestMap = getRequestMap(joinPoint, request, requestBody);
        CurrentThreadContext.setRequestMap(requestMap);


        loadCookie(request);

        boolean hasAuth = checkAuth(request);
        Object result = null;
        if (hasAuth) {
            //执行方法
            result = joinPoint.proceed();
        } else {
            //返回无权限
            response.setStatus(HttpServletResponseWrapper.SC_UNAUTHORIZED);
        }

        //写api调用日志
        if ("1".equals(propertiesConfig.writeApiLog)) {
            writeApiLog(requestMap, result, response);
        }

        //删除本地线程变量
        CurrentThreadContext.remove();
        return result;
    }


    /**
     * 加载cookie
     *
     * @param request
     */
    private void loadCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (!ArrayUtils.isEmpty(cookies)) {
            //将token置入cookie
            for (Cookie cookie : cookies) {
                if (CacheConsts.COOKIE_I18N_LANGUAGE.equals(cookie.getName())) {
                    EnumI18NProject i18NProject = EnumI18NProject.getObjByLanguage(cookie.getValue());
                    CurrentThreadContext.setInternationalTableName(i18NProject.getTableName());
                    CurrentThreadContext.setI18N(i18NProject.getRequestLanguage());
                }
                if (CacheConsts.COOKIE_USER_TOKEN.equals(cookie.getName())) {
                    CurrentThreadContext.setToken(cookie.getValue());
                }
            }
        }
        if (StringUtils.isEmpty(CurrentThreadContext.getInternationalTableName())) {
            EnumI18NProject i18NProject = EnumI18NProject.getObjByLanguage(null);
            CurrentThreadContext.setInternationalTableName(i18NProject.getTableName());
            CurrentThreadContext.setI18N(i18NProject.getRequestLanguage());
            Cookie cookie = new Cookie(CacheConsts.COOKIE_I18N_LANGUAGE, i18NProject.getRequestLanguage());
            CurrentThreadContext.getResponse().addCookie(cookie);
        }
        String token = CurrentThreadContext.getToken();
        if (!StringUtils.isEmpty(token)) {
            String email = redisCommonDao.getHash(RedisKeyUtil.getTokenEmailKey(), token);
            String userBeanJson = redisCommonDao.getString(RedisKeyUtil.getCallApiInfo(email));
            if (StringUtils.isEmpty(userBeanJson)) {
                SysUserBean userBean = sysUserService.getByEmail(email, null);
                if (userBean != null) {
                    redisCommonDao.setString(RedisKeyUtil.getCallApiInfo(email), new Gson().toJson(userBean));
                }
            }
        }
    }

    /**
     * 检验权限
     */
    private boolean checkAuth(HttpServletRequest request) {
        //替换掉项目名,获得Uri
        String requestURI = request.getRequestURI();
        String replace = "/" + PROJECT_NAME;
        if (requestURI.startsWith(replace)) {
            requestURI = requestURI.replace(replace, "");
        }
        //检验免登陆
        Set<String> freeAuthSet = startupRunnerDefault.getFreeAuthSet();
        for (String freeAuth : freeAuthSet) {
            if (requestURI.startsWith(freeAuth)) {
                return true;
            }
        }
        //检验token
        String token = CurrentThreadContext.getToken();
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        String email = redisCommonDao.getHash(RedisKeyUtil.getTokenEmailKey(), token);
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        CurrentThreadContext.setEmail(email);

        //判断用户是否在管理员列表里
        Set<String> adminUserSet = startupRunnerDefault.getAdminUserSet();
        boolean isAdmin = adminUserSet.contains(email);
        //检验管理员权限
        Set<String> adminPathSet = startupRunnerDefault.getAdminPathSet();
        for (String adminPath : adminPathSet) {
            if (requestURI.startsWith(adminPath)) {
                return isAdmin;
            }
        }
        return true;
    }
}
