package belog.annotation;

/**
 * 字段注解
 * Created by Beldon.
 * Copyright (c) 2016, All Rights Reserved.
 * http://beldon.me
 */
public @interface Column {

    /**
     * 字段名称
     *
     * @return String
     */
    String name() default "";

    /**
     * 默认值
     *
     * @return String
     */
    String defaultValue() default "";

    /**
     * 自读类型
     *
     * @return String
     */
    String type() default "";

    /**
     * 字段长度
     *
     * @return int
     */
    int length() default 255;
}
