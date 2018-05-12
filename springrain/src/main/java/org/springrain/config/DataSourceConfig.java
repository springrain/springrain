package org.springrain.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springrain.frame.common.BaseLogger;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据库配置
 * @author caomei
 *
 */
@Configuration("configuration-DataSourceConfig")
public class DataSourceConfig extends BaseLogger {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    
    private Integer initialSize=5;
    private Integer minIdle=5;      
    private Integer maxActive=50;
    private Long maxWaitMillis=60000L;
    private Long minEvictableIdleTimeMillis=300000L;
    private Long timeBetweenEvictionRunsMillis=60000L;
    private Integer removeAbandonedTimeout=1800;
    
    
    private Boolean removeAbandoned=true;
    private Boolean logAbandoned=true;
    private Boolean testWhileIdle=true;
    private Boolean testOnBorrow=true;
    private Boolean testOnReturn=false;
    
    //private String filters="stat,wall,slf4j";
   // private String connectionProperties="druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000";
    private String filters="stat,wall";
    private String connectionProperties="druid.stat.slowSqlMillis=1000;druid.stat.logSlowSql=true";
    
    
    /**
     * 自定义 dataSource,用户扩展实现
     */
    @Bean("dataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);// 用户名
        dataSource.setPassword(password);// 密码
        //设置属性
        setDataSourceProperties(dataSource);
        return dataSource;
    }
    
    
    /**
     * 根据事务隔离级别做读写分离,有事务就写,无事务就是读
     * @return
     */
    /*
    @Bean("dataSource")
    public DataSource dataSource1() {
        TransactionDataSourceRouter dataSource = new TransactionDataSourceRouter();
        
        Map<Object, Object>targetDataSources=new HashMap<>();
        targetDataSources.put("dataSourceWrite", dataSourceWrite);
        targetDataSources.put("dataSourceRead", dataSourceRead);
        dataSource.setTargetDataSources(targetDataSources);
        //默认读库
        dataSource.setDefaultTargetDataSource(dataSourceRead);
        return dataSource;
    }
    */
    
    
    
    @Bean("jdbc")
    public NamedParameterJdbcTemplate jdbc() {
        NamedParameterJdbcTemplate jdbc=new NamedParameterJdbcTemplate(dataSource());
        return jdbc;
    }
    
    @Bean("jdbcCall")
    @Scope("prototype")
    public SimpleJdbcCall jdbcCall() {
        SimpleJdbcCall jdbcCall=new SimpleJdbcCall(dataSource());
        return jdbcCall;
    }
    
    private DruidDataSource setDataSourceProperties(DruidDataSource dataSource) {
        try {
            dataSource.setInitialSize(initialSize);
            dataSource.setMinIdle(minIdle);
            dataSource.setMaxActive(maxActive);
            dataSource.setMaxWait(maxWaitMillis);
            dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            dataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
            dataSource.setRemoveAbandoned(removeAbandoned);
            dataSource.setLogAbandoned(logAbandoned);
            dataSource.setTestWhileIdle(testWhileIdle);
            dataSource.setTestOnBorrow(testOnBorrow);
            dataSource.setTestOnReturn(testOnReturn);
            
            dataSource.setFilters(filters);
            dataSource.setConnectionProperties(connectionProperties);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        
        
        return dataSource;
        
    }
    
}
