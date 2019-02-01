package com.jf.mydemo.dynamicDs.dataSources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-01 10:21
 * @Description: 动态切换数据源
 * To change this template use File | Settings | File and Templates.
 */

public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("数据源为：{}", DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }
}
