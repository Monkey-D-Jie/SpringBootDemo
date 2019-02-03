package com.jf.mydemo.druid;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-03 09:50
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 从数据源配置
 */
@Configuration
@MapperScan(basePackages = ClusterDataSourceConfig.PACKAGE, sqlSessionTemplateRef   = "clusterSqlSessionTemplate")
public class ClusterDataSourceConfig {

    /**
     * 配置多数据源 关键就在这里 这里配置了不同的数据源扫描不同mapper
     */
    static final String PACKAGE = "com.jf.mydemo.dynamicDs.dao.cluster";
    static final String MAPPER_LOCATION = "classpath:mybatis-mapper/*.xml";

    /**
     * 连接数据库信息 这个其实更好的是用配置中心完成
     */
    @Value("${spring.two.datasource.url}")
    private String url;
    @Value("${spring.two.datasource.username}")
    private String username;
    @Value("${spring.two.datasource.password}")
    private String password;
    @Value("${spring.two.datasource.driver-class-name}")
    private String driverClassName;


    /**
     * 下面的配置信息可以读取配置文件，其实可以直接写死 如果是多数据源的话 还是考虑读取配置文件
     */
//    @Value("${spring.datasource.druid.initialSize}")
//    private int initialSize;
//    @Value("${spring.datasource.druid.minIdle}")
//    private int minIdle;
//    @Value("${spring.datasource.druid.maxActive}")
//    private int maxActive;
//    @Value("${spring.datasource.druid.maxWait}")
//    private int maxWait;
//    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
//    private int timeBetweenEvictionRunsMillis;
//    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
//    private int minEvictableIdleTimeMillis;
//    @Value("${spring.datasource.druid.validationQuery}")
//    private String validationQuery;
//    @Value("${spring.datasource.druid.testWhileIdle}")
//    private boolean testWhileIdle;
//    @Value("${spring.datasource.druid.testOnBorrow}")
//    private boolean testOnBorrow;
//    @Value("${spring.datasource.druid.testOnReturn}")
//    private boolean testOnReturn;
//    @Value("${spring.datasource.druid.poolPreparedStatements}")
//    private boolean poolPreparedStatements;
//    @Value("${spring.datasource.druid.maxPoolPreparedStatementPerConnectionSize}")
//    private int maxPoolPreparedStatementPerConnectionSize;
//    @Value("${spring.datasource.druid.filters}")
//    private String filters;
//    @Value("${spring.datasource.druid.connectionProperties}")
//    private String connectionProperties;


//    @Bean(name = "dbCluster1")
//    public DataSource masterDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setDriverClassName(driverClassName);
//
//        //具体配置
//        dataSource.setInitialSize(initialSize);
//        dataSource.setMinIdle(minIdle);
//        dataSource.setMaxActive(maxActive);
//        dataSource.setMaxWait(maxWait);
//        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//        dataSource.setValidationQuery(validationQuery);
//        dataSource.setTestWhileIdle(testWhileIdle);
//        dataSource.setTestOnBorrow(testOnBorrow);
//        dataSource.setTestOnReturn(testOnReturn);
//        dataSource.setPoolPreparedStatements(poolPreparedStatements);
//        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
//        /**
//         * 这个是用来配置 druid 监控sql语句的 非常有用 如果你有两个数据源 这个配置哪个数据源就监控哪个数据源的sql 同时配置那就都监控
//         */
//        try {
//            dataSource.setFilters(filters);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        dataSource.setConnectionProperties(connectionProperties);
//        return dataSource;
//    }

    @Bean(name = "dbCluster1")
    @Qualifier("dbCluster1")
    @ConfigurationProperties(prefix = "spring.two.datasource")
    public DataSource clusterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "clusterTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager(@Qualifier("dbCluster1") DataSource dataSource) {
        return new DataSourceTransactionManager(clusterDataSource());
    }

    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("dbCluster1") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ClusterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(name = "clusterSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("clusterSqlSessionFactory") SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
