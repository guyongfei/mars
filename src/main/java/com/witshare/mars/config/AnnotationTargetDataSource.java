package com.witshare.mars.config;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface AnnotationTargetDataSource {
    String value();
}
