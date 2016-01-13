package com.suypower.cloudx.web.controller;

import com.suypower.cloudx.module.system.entity.Account;
import com.suypower.cloudx.support.encrypt.DesEncrypter;
import com.suypower.cloudx.web.SysConst;
import com.suypower.cloudx.web.support.ControllerSupport;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by Bingdor on 2015/11/17.
 */
@Controller
@RequestMapping(value = "/passport")
public class PassportController extends ControllerSupport {
    @RequestMapping(value = "/login")
    public void login() throws IOException{
        Account account = null;
        try {
            account = (Account) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/");
        }
        session.setAttribute(SysConst.SESSION_USER, account);
        response.sendRedirect("/index");
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    public static void main(String[] args) {
        System.out.println(DesEncrypter.encrypt("123456"));
    }

}
