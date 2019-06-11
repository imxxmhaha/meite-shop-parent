package com.mayikt.common.aop;

import java.lang.annotation.*;

/**
 * @author xxm
 * @version 1.0
 * @Description: redis事务注解
 * @date 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisTransAdvice {

    public String description() default "";
}
