package com.micro.web.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.sqlite.SQLiteDataSource;
import org.sqlite.javax.SQLiteConnectionPoolDataSource;

import javax.sql.DataSource;

/**
 * sqlite
 * 采用默认 com.zaxxer.hikari.HikariDataSource 连接池.
 */
@Configuration
public class DataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    static{
        //初始化 sqlite 数据库
        initSqlite();

    }

    private static void initSqlite() {
//        Resources
//        SQLiteDataSource ds = new SQLiteDataSource();
//        ds.set
    }


    @Bean(name = "sqliteDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari.sqlite")
    public DataSource mysqlDataSource() {
        logger.info("Initialized sqliteDataSource");
        return DataSourceBuilder.create().type(SQLiteConnectionPoolDataSource.class).build();
    }


    @Bean(name = "sqliteJdbcTemplate")
    @Primary
    public JdbcTemplate jdbcTemplate(@Qualifier("sqliteDataSource") DataSource dataSource) {
        logger.info("sqliteDataSource datasource: {}", dataSource.getClass().getName());
        return new JdbcTemplate(dataSource);
    }

}
