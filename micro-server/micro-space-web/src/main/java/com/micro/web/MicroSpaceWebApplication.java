package com.micro.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value={"${spring.config.location.db}"})
@MapperScan(basePackages = {"com.micro.web.dao"})
//(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class MicroSpaceWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroSpaceWebApplication.class, args);
    }

}

