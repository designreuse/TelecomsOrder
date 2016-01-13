package com.suypower.cloudx.module.system.controller;

import com.suypower.cloudx.module.system.entity.Account;
import com.suypower.cloudx.module.system.entity.PageTemplate;
import com.suypower.cloudx.module.system.service.IPageTplService;
import com.suypower.cloudx.support.exception.CloudxException;
import com.suypower.cloudx.web.support.ControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Bingdor on 2015/12/1.
 */
@Controller
@RequestMapping("/system/dashboard")
public class DashboardController extends ControllerSupport {
    @Autowired
    private IPageTplService pageTplService;
    @RequestMapping("/show")
    public ModelAndView showDashboard(){
        ModelAndView modelAndView = new ModelAndView();
        Account account = getCurrentUser();
        try {
            PageTemplate pageTemplate =pageTplService.findUserPageTpl(account.getAccountID());
            modelAndView.setViewName(pageTemplate==null?"common/simpleDashboard":pageTemplate.getPath());
        } catch (CloudxException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
}
