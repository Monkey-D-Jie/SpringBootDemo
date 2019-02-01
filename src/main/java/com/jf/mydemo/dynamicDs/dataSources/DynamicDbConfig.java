package com.jf.mydemo.dynamicDs.dataSources;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-01 10:19
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

@Configuration
@MapperScan(basePackages = {"com.jf.mydemo.dynamicDs.dao"}, sqlSessionFactoryRef = "dynamicSqlSessionFactory")
public class DynamicDbConfig {

    private static String MYBATIS_CONFIG = "mybatis-config.xml";
    /**     * mybatis mapper resource 路径     */
    private static String MAPPER_PATH = "mybatis-mapper/**.xml";


    private final DataSource dynamicDS;

    @Autowired
    public DynamicDbConfig(DataSource dynamicSource) {
        this.dynamicDS = dynamicSource;
    }

    @Bean
    public SqlSessionFactory dynamicSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));

        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;

        factoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
        factoryBean.setDataSource(dynamicDS);
        return factoryBean.getObject();

    }
}

