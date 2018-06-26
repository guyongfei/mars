package com.witshare.mars.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Order(-1) // 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

    private final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

   /* @Before("execution(* com.witshare.mars.dao.mysql.JobAndTriggerMapper.*(..)) ")
    public void before(JoinPoint point) {
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?>[] classz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(AnnotationTargetDataSource.class)) {
                AnnotationTargetDataSource data = m.getAnnotation(AnnotationTargetDataSource.class);
                DynamicDataSourceContextHolder.setDataSourceType(data.value());
                logger.info("DataSource:" + data.value());
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @After("execution(* com.witshare.mars.dao.mysql.JobAndTriggerMapper.*(..))")
    public void restoreDataSource(JoinPoint point) {
        DynamicDataSourceContextHolder.clearDataSourceType();
    }*/
}
