package com.witshare.mars.aspect;


import com.witshare.mars.config.CurrentThreadContext;
import com.witshare.mars.constant.PropertiesConfig;
import com.witshare.mars.pojo.util.LogApiBean;
import com.witshare.mars.util.JsonUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.*;

import static com.witshare.mars.constant.CacheConsts.PROJECT_NAME;


@Aspect
@Component
public class LogAspect implements ThrowsAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);
    @Autowired
    private PropertiesConfig propertiesConfig;

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
        Object result;

        //执行方法
        result = joinPoint.proceed();

        //写api调用日志
        if ("1".equals(propertiesConfig.writeApiLog)) {
            writeApiLog(requestMap, result, response);
        }

        //删除本地线程变量
        CurrentThreadContext.remove();
        return result;
    }
}
