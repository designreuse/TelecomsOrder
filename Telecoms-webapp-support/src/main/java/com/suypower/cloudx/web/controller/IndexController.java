package com.suypower.cloudx.web.controller;

import com.suypower.cloudx.support.helper.AjaxHelper;
import com.suypower.cloudx.web.service.IOverviewService;
import com.suypower.cloudx.web.support.ControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bingdor on 2015/11/25.
 */
@Scope("prototype")
@Controller
public class IndexController extends ControllerSupport {
    @Autowired
    private IOverviewService overviewService;
    @RequestMapping("/index")
    public ModelAndView toIndex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("common/index");
        return modelAndView;
    }

    @RequestMapping("/overview")
    public void getOverview() throws IOException{
        Map<String,Object> map = overviewService.findOverviewData(getCurrentUser().getOrg().getOrgNo());
        try {
            returnJSONView(AjaxHelper.toJSON(map,AjaxHelper.SUCCESS,"查询成功"));
        }catch (Exception e){
            e.printStackTrace();
            returnJSONView(AjaxHelper.toJSON(null,AjaxHelper.FAILURE,"查询失败"));
        }
    }

    @RequestMapping("/monthData")
    public void getYearmonthData() throws IOException{
        List<Map<?,?>> list = overviewService.findYearMonthRcv(getCurrentUser().getOrg().getOrgNo());
        List<String> rcvList = new ArrayList<String>();
        List<String> rcvedList = new ArrayList<String>();
        try {
            for (Map<?,?> map : list){
                rcvList.add(String.valueOf(map.get("RCV_AMT")));
                rcvedList.add(String.valueOf(map.get("RCVED_AMT")));
            }
            Map<String,Object> result = new HashMap<String, Object>();
            result.put("RCV_AMT",rcvList);
            result.put("RCVED_AMT",rcvedList);
            returnJSONView(AjaxHelper.toJSON(result,AjaxHelper.SUCCESS,"查询成功"));
        }catch (Exception e){
            e.printStackTrace();
            returnJSONView(AjaxHelper.toJSON(null,AjaxHelper.FAILURE,"查询失败"));
        }
    }

    @RequestMapping("/yearCharge")
    public void getYearCharge() throws IOException{
        Map<?,?> map = overviewService.findYearCharge(getCurrentUser().getOrg().getOrgNo());
        try {
            returnJSONView(AjaxHelper.toJSON(map,AjaxHelper.SUCCESS,"查询成功"));
        }catch (Exception e){
            e.printStackTrace();
            returnJSONView(AjaxHelper.toJSON(null,AjaxHelper.FAILURE,"查询失败"));
        }
    }

}
