package belog.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 实体类注解，和 Table 作用一样
 * Created by Beldon.
 * Copyright (c) 2016, All Rights Reserved.
 * http://beldon.me
 */
@Target(TYPE)
@Retention(RUNTIME)

public @interface Entity {

    /**
     * 表名称
     *
     * @return String
     */
    String name() default "";
}