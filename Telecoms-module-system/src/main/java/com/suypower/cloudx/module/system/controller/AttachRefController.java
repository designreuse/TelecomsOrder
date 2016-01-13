package com.suypower.cloudx.module.system.controller;

import com.suypower.cloudx.module.system.service.IAttachRefService;
import com.suypower.cloudx.support.helper.AjaxHelper;
import com.suypower.cloudx.web.support.ControllerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mengcheng.yu on 2015/11/27.
 */
@Controller
@RequestMapping("/system/attachRef")
public class AttachRefController extends ControllerSupport
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IAttachRefService attachRefService;

    @RequestMapping("/deleteAttachRefsById")
    public void deleteAttachRefById() throws IOException
    {
        Map map = getParamMap();
        try
        {
            List<String> attachRefIdlist = new ArrayList<String>();
            attachRefIdlist.add(map.get("attachRefId").toString());
            List<String> attachIdlist = new ArrayList<String>();
            attachIdlist.add(map.get("attachId").toString());
            attachRefService.deleteAttachRefByIds(attachRefIdlist, attachIdlist);
            returnJSONView(AjaxHelper.toJSON(null, AjaxHelper.SUCCESS, "删除成功。"));
        }
        catch (Exception e)
        {
            logger.debug(e.getMessage(), e);
            returnJSONView(AjaxHelper.toJSON(null, AjaxHelper.FAILURE,"服务器异常,请稍后再试。"));
        }
    }
}
