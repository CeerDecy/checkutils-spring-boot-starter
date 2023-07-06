package com.ceerdecy.checkutils.annotation;

import java.lang.annotation.*;

/**
 * @author CeerDecy
 * @date 2023/2/25 21:36
 */
@Repeatable(value = CheckBeans.class)
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckBean {
    String[] value() default "";
    String bean() default "";
    String[] fields() default "";
}
