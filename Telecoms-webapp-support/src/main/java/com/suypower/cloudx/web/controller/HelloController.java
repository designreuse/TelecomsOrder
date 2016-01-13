package com.suypower.cloudx.web.controller;

import com.suypower.cloudx.web.support.ControllerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Bingdor on 2015/11/13.
 */
@Controller
public class HelloController extends ControllerSupport {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/hello/{name}")
    public ModelAndView sayHello(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", name);
        if(logger.isDebugEnabled()){
            logger.debug("Hello,{}!",name);
        }
        modelAndView.setViewName("test/hello");
        return modelAndView;
    }
}
