package com.ceerdecy.checkutils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author CeerDecy
 * @date 2023/2/25 21:36
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckBean {
    String[] value() default "";
    String param()default "";
    String[] fields() default "";

}
