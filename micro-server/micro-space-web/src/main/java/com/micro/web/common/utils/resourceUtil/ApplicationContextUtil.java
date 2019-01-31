package com.micro.web.common.utils.resourceUtil;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void resource() throws IOException {
        Resource resource = applicationContext.getResource("classpath:db/sqlite.properties");
        resource.getInputStream();
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-resource.xml");
    }
}
