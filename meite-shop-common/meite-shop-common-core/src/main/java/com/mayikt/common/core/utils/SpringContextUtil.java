package com.mayikt.common.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext){
        SpringContextUtil.applicationContext = applicationContext;
    }
    
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
    
    public static Object getBean(Class var1) throws BeansException {
        return applicationContext.getBean(var1);
    }
}