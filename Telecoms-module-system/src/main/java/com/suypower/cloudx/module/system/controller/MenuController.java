package com.suypower.cloudx.module.system.controller;

import com.suypower.cloudx.module.system.entity.Account;
import com.suypower.cloudx.module.system.entity.Menu;
import com.suypower.cloudx.module.system.service.IMenuService;
import com.suypower.cloudx.support.exception.CloudxException;
import com.suypower.cloudx.support.helper.AjaxHelper;
import com.suypower.cloudx.web.support.ControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

/**
 * Created by Bingdor on 2015/11/27.
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends ControllerSupport {
    @Autowired
    private IMenuService menuService;
    @RequestMapping("/get")
    public void getMenus() throws IOException{
        Account account = getCurrentUser();
        try {
            List<Menu> menuList = menuService.findMenus(account.getOrg().getOrgNo());
            returnJSONView(AjaxHelper.toJSON(menuList,AjaxHelper.SUCCESS,"菜单加载成功。"));
        } catch (CloudxException e) {
            e.printStackTrace();
            returnJSONView(AjaxHelper.toJSON(null,AjaxHelper.FAILURE,"菜单加载失败，请稍后重试或联系管理员。"));
        }
    }
}
