package com.ceerdecy.checkutils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author CeerDecy
 * @date 2023/3/4 15:24
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckMap {
    String map();
    String[] fields() default "";
}
