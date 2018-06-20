package com.witshare.mars.aspect;

import com.witshare.mars.config.CurrentThreadContext;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.util.ResponseBean;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Map;

import static com.witshare.mars.aspect.LogAspect.writeApiLog;


@ControllerAdvice
public class ControllerAdviceAspect {

    private static Logger logger = LoggerFactory.getLogger(ControllerAdviceAspect.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object processException(NativeWebRequest request, Exception e) {

        Map requestMap = CurrentThreadContext.getRequestMap();
        HttpServletResponse response = CurrentThreadContext.getResponse();
        Signature signature = (Signature) requestMap.get("signature");
        Class declaringType = signature.getDeclaringType();
        logger = LoggerFactory.getLogger(declaringType);
        Object result = null;
        if (e instanceof WitshareException) {
            result = ResponseBean.newInstanceError(e.getMessage());
        } else if (e instanceof SQLException) {
            result = ResponseBean.newInstanceError(EnumResponseText.ErrorSql.getEn());
        } else {
            result = ResponseBean.newInstanceError(e.getClass().getName() + ": " + e.getMessage());
            logger.error("{} fail:", signature.getDeclaringTypeName(), e);
        }
        writeApiLog(requestMap, result, response);
        return result;
    }


}
