package com.micro.web.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext  applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(null == applicationContext){
            SpringUtil.applicationContext =  applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    /**
     * 根据name 获取 bean
     * @param name
     * @return
     */
    public static Object getBean(String name)
    {
        return getApplicationContext().getBean(name);
    }

    /**
     * 根据class 获取 bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Object getBean(Class<T> clazz)
    {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 根据name class获取 bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz)
    {
        return getApplicationContext().getBean(name, clazz);
    }
}
