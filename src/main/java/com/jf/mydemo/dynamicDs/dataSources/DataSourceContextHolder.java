package com.jf.mydemo.dynamicDs.dataSources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-01 10:21
 * @Description: 设置数据源的相关属性
 * To change this template use File | Settings | File and Templates.
 */

class DataSourceContextHolder {

    private static final Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);

    /**
     * 默认数据源
     */
    static final String DEFAULT_DS = "db-master";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    static void setDB(String dbType) {
        log.debug("切换到{}数据源", dbType);
        contextHolder.set(dbType);
    }

    // 获取数据源名
    static String getDB() {
        return contextHolder.get();
    }

    // 清除数据源名
    static void clearDB() {
        contextHolder.remove();
    }
}