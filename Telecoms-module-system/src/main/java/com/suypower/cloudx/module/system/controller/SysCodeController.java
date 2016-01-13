package com.suypower.cloudx.module.system.controller;

import com.suypower.cloudx.module.system.service.ISysCodeService;
import com.suypower.cloudx.support.helper.AjaxHelper;
import com.suypower.cloudx.web.support.ControllerSupport;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * Created by Zhengtao on 2015/12/2.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("/system/code")
public class SysCodeController extends ControllerSupport{

    private static Logger logger = Logger.getLogger(SysCodeController.class);

    private ISysCodeService sysCodeService;

    @Autowired
    public void setSysCodeService(ISysCodeService sysCodeService) {
        this.sysCodeService = sysCodeService;
    }

    @RequestMapping("/querySysCodes")
    public void querySysCodes() throws IOException {
        String codeSortName = request.getParameter("codeSortName");
        try {
            returnJSONView(AjaxHelper.toJSON(sysCodeService.querySysCodesBySortName(codeSortName),
                    AjaxHelper.SUCCESS, "查询系统代码成功"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询系统代码发生异常。", e);
            returnJSONView(AjaxHelper.toJSON(null, AjaxHelper.FAILURE,
                    "查询系统代码失败"));
        }
    }
}
