package com.jf.mydemo.dynamicDs.dataSources;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-01 10:15
 * @Description: 数据源配置
 * To change this template use File | Settings | File and Templates.
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "dynamicSource")
    public DynamicDataSource dataSource(@Qualifier("dbMaster") DataSource dbMaster,
                                        @Qualifier("dbCluster") DataSource dbRead) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dbMaster);

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>(5);
        dsMap.put("dbMaster", dbMaster);
        dsMap.put("dbCluster", dbRead);
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }

    @Bean(name = "dbMaster")
    //通过注解声明，把数据源的信息从配置文件application.yml中拿出来-第①种方式
    //另外就是从.properties文件中把配置信息拿出来-第②种方式
    @ConfigurationProperties(prefix = "spring.one.datasource")
    public DataSource dbMaster() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dbCluster")
    @ConfigurationProperties(prefix = "spring.two.datasource")
    public DataSource dbRead() {
        return DataSourceBuilder.create().build();
    }
}
