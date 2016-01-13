package com.suypower.cloudx.ws.support;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Bingdor on 2016/1/12.
 */
public class JsrHandlerMappingServletAdapter implements Controller {
    private Servlet contoller;

    public JsrHandlerMappingServletAdapter(Servlet contoller)
    {
        this.contoller = contoller;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.contoller.service(request, response);
        return null;
    }
}