package com.jf.mydemo.dynamicDs.dataSources;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-01 10:22
 * @Description: 使用自定义的注解指明数据源，默认使用db-master
 * To change this template use File | Settings | File and Templates.
 */

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DBSource {
    String value() default "db-master";
}
