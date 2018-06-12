package com.witshare.mars.config;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1) // 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

    private final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

//    @Before("execution(* com.witshare.mars.dao.mysql.JobAndTriggerMapper.*(..)) or execution(* com.witshare.mars.dao.mysql.JerukdbMapper.*(..)) ")
//    public void before(JoinPoint point) {
//        Object target = point.getTarget();
//        String method = point.getSignature().getName();
//        Class<?>[] classz = target.getClass().getInterfaces();
//        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
//        try {
//            Method m = classz[0].getMethod(method, parameterTypes);
//            if (m != null && m.isAnnotationPresent(AnnotationTargetDataSource.class)) {
//                AnnotationTargetDataSource data = m.getAnnotation(AnnotationTargetDataSource.class);
//                DynamicDataSourceContextHolder.setDataSourceType(data.value());
//                logger.info("DataSource:" + data.value());
//            }
//
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        }
//    }

//    @After("execution(* com.witshare.mars.dao.mysql.JobAndTriggerMapper.*(..)) or execution(* com.witshare.mars.dao.mysql.JerukdbMapper.*(..)) ")
//    public void restoreDataSource(JoinPoint point) {
//        DynamicDataSourceContextHolder.clearDataSourceType();
//    }
}
