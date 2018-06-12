package com.witshare.mars.config;

import com.google.gson.Gson;
import com.witshare.mars.constant.CacheConsts;
import com.witshare.mars.constant.EnumI18NProject;
import com.witshare.mars.constant.PropertiesConfig;
import com.witshare.mars.constant.StartupRunnerDefault;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.pojo.dto.SysUserBean;
import com.witshare.mars.pojo.util.LogApiBean;
import com.witshare.mars.service.SysUserService;
import com.witshare.mars.util.RedisKeyUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * 系统日志切面
 */
@Aspect
@Component
public class SystemLogAspect {


    private final static String REQUEST_BODY = "requestBody";
    private final static String REQUEST_URI = "uri";
    private final static String REQUEST_PARAM = "requestParam";
    private final static String PATH_VARIABLE = "pathVariable";
    private final static String REQUEST_HEADER = "requestHeader";
    private final static Gson GSON = new Gson();
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private StartupRunnerDefault startupRunnerDefault;
    @Autowired
    private RedisCommonDao redisCommonDao;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private PropertiesConfig propertiesConfig;

    /**
     * controller 切面
     */
    @Pointcut("execution (* com.witshare.mars.controller..*.*(..))")
    public void controllerAspect() {
    }


    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取必要的参数
        String methodType = joinPoint.getSignature().getName();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = CurrentThreadContext.getResponse();
        String requestBody = charReader(request);
        Map requestMap = getRequestMap(request, requestBody);
        Date start = new Date();
        Object result = null;
        boolean hasAuth;
        //切换国际化表
        loadCookie(request);

        //检验权限
        hasAuth = checkAuth(request);
        if (hasAuth) {
            //执行方法
            result = joinPoint.proceed();
        } else {
            //返回无权限
            response.setStatus(HttpServletResponseWrapper.SC_UNAUTHORIZED);
        }
        //写api调用日志
        writeApiLog(methodType, requestMap, result, response, start);
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
            for (Cookie cookie : cookies) {
                if (CacheConsts.INTERNATIONAL_LANGUAGE.equals(cookie.getName())) {
                    EnumI18NProject i18NProject = EnumI18NProject.getObjByLanguage(cookie.getValue());
                    CurrentThreadContext.setInternationalTableName(i18NProject.getTableName());
                    CurrentThreadContext.setI18N(i18NProject.getRequestLanguage());
                }
                if (CacheConsts.KEY_COOKIE_NAME.equals(cookie.getName())) {
                    CurrentThreadContext.setToken(cookie.getValue());
                }

            }
        }
        //TODO 正式代码删除，调试跨域用
///*//        if (StringUtils.isEmpty(CurrentThreadContext.getToken())) {
//        redisCommonDao.putHash(RedisKeyUtil.getTokenEmailKey(), "ea0d95a82c004c698fa3af10cd15785f", "446390091@qq.com");
//        redisCommonDao.putHash(RedisKeyUtil.getEmailTokenKey(), "446390091@qq.com", "ea0d95a82c004c698fa3af10cd15785f");
//        CurrentThreadContext.setToken("ea0d95a82c004c698fa3af10cd15785f");
//        Cookie cookie1 = new Cookie(KEY_COOKIE_NAME, "ea0d95a82c004c698fa3af10cd15785f");
//        CurrentThreadContext.getResponse().addCookie(cookie1);
//        }*/
        if (StringUtils.isEmpty(CurrentThreadContext.getInternationalTableName())) {
            EnumI18NProject i18NProject = EnumI18NProject.getObjByLanguage(null);
            CurrentThreadContext.setInternationalTableName(i18NProject.getTableName());
            CurrentThreadContext.setI18N(i18NProject.getRequestLanguage());
            Cookie cookie = new Cookie(CacheConsts.INTERNATIONAL_LANGUAGE, i18NProject.getRequestLanguage());
            CurrentThreadContext.getResponse().addCookie(cookie);
        }
        String token = CurrentThreadContext.getToken();
        if (!StringUtils.isEmpty(token)) {
            String email = redisCommonDao.getHash(RedisKeyUtil.getTokenEmailKey(), token);
            String userBeanJson = redisCommonDao.getString(RedisKeyUtil.getCallApiInfo(email));
            if (StringUtils.isEmpty(userBeanJson)) {
                SysUserBean userBean = sysUserService.getByEmail(email, null);
                if (userBean != null) {
                    redisCommonDao.setString(RedisKeyUtil.getCallApiInfo(email), GSON.toJson(userBean));
                }
            }
        }
    }

    /**
     * 检验权限
     *
     * @param request
     * @return
     */
    private boolean checkAuth(HttpServletRequest request) {
        //替换掉项目名,获得Uri
        String requestURI = request.getRequestURI();
        String replace = "/" + CacheConsts.PROJECT_NAME;
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

    /**
     * 装载日志信息
     *
     * @param methodType
     * @param requestMap
     * @param resultObj
     * @param response
     * @param start
     * @return
     */
    public String writeApiLog(String methodType, Map requestMap, Object resultObj, HttpServletResponse response, Date start) {
        LogApiBean logApiBean = new LogApiBean();
        logApiBean.setStartTime(start);
        logApiBean.setEndTime(new Date());
        logApiBean.setProjectName(propertiesConfig.projectName);
        logApiBean.setRequest(requestMap);
        logApiBean.setResponse(resultObj);
        logApiBean.setResponseStatus(response.getStatus());
        logApiBean.setMethodType(methodType);
        String result = GSON.toJson(logApiBean);
//        LOGGER.info(result);
        return result;
    }

    /**
     * 获取请求信息
     *
     * @param request
     * @param requestBody
     * @return
     */
    public Map getRequestMap(HttpServletRequest request, Object requestBody) {

        LinkedHashMap<String, Object> requestMap = new LinkedHashMap<>();
        //将请求URI置入
        String requestURI = request.getRequestURI();
        requestMap.put(REQUEST_URI, requestURI);
        //将请求头置入
        HashMap<Object, Object> requestHeader = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            requestHeader.put(headerName, headerValue);
        }
        requestMap.put(REQUEST_HEADER, requestHeader);

        //将请求地址变量置入
        HashMap<Object, Object> pathVariable = new HashMap<>();
        NativeWebRequest webRequest = new ServletWebRequest(request);
        Map<String, String> uriTemplateVars = (Map<String, String>) webRequest
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        pathVariable.putAll(uriTemplateVars);
        requestMap.put(PATH_VARIABLE, pathVariable);

        //将请求参数置入
        Map<String, String[]> parameterMap = request.getParameterMap();
        HashMap<Object, Object> requestParam = new HashMap<>();
        if (parameterMap != null && parameterMap.size() > 0) {
            Iterator<Map.Entry<String, String[]>> iterator = parameterMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String[]> next = iterator.next();
                requestParam.put(next.getKey(), next.getValue()[0]);
            }
        }
        requestMap.put(REQUEST_PARAM, requestParam);

        //将请求体置入
        requestMap.put(REQUEST_BODY, JSONObject.stringToValue((String) requestBody)/*gson.fromJson((String) requestBody, Map.class)*/);

        return requestMap;
    }

    /**
     * 读取JsonBody
     *
     * @param request
     * @return
     */
    String charReader(HttpServletRequest request) {

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

}
