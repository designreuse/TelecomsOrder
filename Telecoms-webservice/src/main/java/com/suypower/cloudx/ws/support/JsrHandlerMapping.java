package com.suypower.cloudx.ws.support;

import org.apache.cxf.aegis.databinding.AegisServiceConfiguration;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.jaxws.support.JaxWsServiceConfiguration;
import org.apache.cxf.jaxws.support.JaxWsServiceFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;
import org.springframework.web.servlet.mvc.Controller;

import javax.jws.WebService;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Bingdor on 2016/1/12.
 */

public class JsrHandlerMapping extends AbstractUrlHandlerMapping implements BeanPostProcessor {
    private String urlPrefix = "/services/";

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class clazz = AopUtils.getTargetClass(bean);
        if (clazz.isAnnotationPresent(WebService.class)) {
            WebService ws = (WebService) clazz.getAnnotation(WebService.class);
            createAndPublishEndpoint(this.urlPrefix + ws.serviceName(), bean);
            registerHandler(this.urlPrefix + ws.name(), new ServletAdapter(new CXFServlet()));
        } else if (this.logger.isDebugEnabled()) {
            this.logger.debug("Rejected bean '" + beanName + "' since it has no WebService annotation");
        }

        return bean;
    }

    private void createAndPublishEndpoint(String url, Object implementor) {
        JaxWsServiceFactoryBean aegisServiceFactoryBean = new JaxWsServiceFactoryBean();

        ServerFactoryBean serverFactoryBean = new ServerFactoryBean();
        serverFactoryBean.setServiceBean(implementor);
        serverFactoryBean.setServiceClass(AopUtils.getTargetClass(implementor));
        serverFactoryBean.setAddress(url);

        serverFactoryBean.setServiceFactory(aegisServiceFactoryBean);

        serverFactoryBean.getServiceFactory().getServiceConfigurations().add(0, new AegisServiceConfiguration());
        serverFactoryBean.getServiceFactory().getServiceConfigurations().add(1, new JaxWsServiceConfiguration());
        serverFactoryBean.create();
    }

    public static class ServletAdapter implements Controller {
        private Servlet contoller;

        public ServletAdapter(Servlet contoller) {
            this.contoller = contoller;
        }

        public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
            this.contoller.service(request, response);
            return null;
        }
    }
}
