package com.fc.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chi.fang
 * @date 2019/04/05
 */

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})// 注解作用在
@Retention(RetentionPolicy.SOURCE) // 注解存在的范围
public @interface ThreadSafe {
    String value() default "";
}
