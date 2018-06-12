package com.witshare.mars.config;

import java.lang.annotation.*;

/**
 * 日志注解
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Documented
public @interface AnnotationLog {

    String operationType() default "";

    String operationName() default "";

}
