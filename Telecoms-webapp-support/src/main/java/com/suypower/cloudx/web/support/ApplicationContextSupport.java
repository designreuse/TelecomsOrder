package com.suypower.cloudx.web.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Created by Bingdor on 2015/12/30.
 */
public class ApplicationContextSupport {
    private static Logger logger = LoggerFactory.getLogger(ApplicationContextSupport.class);
    private static ApplicationContext applicationContext = null;

    /**
     * 根据类型获取实例
     *
     * @param requiredType
     * @return
     */
    public static <T> T getBean(Class<T> requiredType) {
        try {
            return getContext().getBean(requiredType);
        } catch (Exception e) {
            logger.error("根据类型获取实例发生异常：", e);
            return null;
        }
    }

    /**
     * 根据bean id获取实例
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        try {
            return getContext().getBean(name);
        } catch (Exception e) {
            logger.error("根据类型获取实例发生异常：", e);
            return null;
        }
    }

    /**
     * 根据bean id 和其他参数获取实例
     *
     * @param name
     * @param args
     * @return
     */
    public static Object getBean(String name, Object... args) {
        try {
            return getContext().getBean(name, args);
        } catch (Exception e) {
            logger.error("根据类型获取实例发生异常：", e);
            return null;
        }
    }

    /**
     * 根据类型和bean id获取实例
     *
     * @param name
     * @param requiredType
     * @return
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        try {
            return getContext().getBean(name, requiredType);
        } catch (Exception e) {
            logger.error("根据类型获取实例发生异常：", e);
            return null;
        }
    }

    public static ApplicationContext getContext(){
        if(applicationContext == null){
            applicationContext = WebApplicationContextUtils
                    .getWebApplicationContext(ContextLoader.getCurrentWebApplicationContext().getServletContext());
        }
        return applicationContext;
    }
}
