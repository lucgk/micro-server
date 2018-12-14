package com.micro.web.common.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ConfigLoadUtil implements EnvironmentAware {
	
	private final static Logger logger = LoggerFactory.getLogger(ConfigLoadUtil.class);

    @Autowired
    private static Environment env;


    public static String getConfValueBykey(String key){
        try {
			if(StringUtils.isNotBlank(key)){
			    return env.getProperty(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取配置参数异常:",e);
		}
        return "";
    }

    public  void setEnvironment(Environment environment) {
        this.env = environment;
    }
}
