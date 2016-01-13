package com.suypower.cloudx.web.controller;

import com.suypower.cloudx.module.system.entity.Account;
import com.suypower.cloudx.support.helper.AjaxHelper;
import com.suypower.cloudx.support.util.StringUtil;
import com.suypower.cloudx.web.service.IConsumerService;
import com.suypower.cloudx.web.support.ControllerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Zhengtao on 2015/12/29.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "/global/cons")
public class ConsumerController extends ControllerSupport {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private IConsumerService consumerService;

    @Autowired
    public void setConsumerService(IConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    /**
     * 根据条件查询用户列表
     */
    @RequestMapping("/queryConsumers")
    public void queryConsumers() throws IOException {
        Map<String, String> params = getParamsStringMap();
        String consName = params.get("consName");
        if (!StringUtil.isEmpty(consName))
        {
            consName = new String(consName.getBytes("iso8859-1"),"UTF-8");
            System.out.println(" ******************** " + consName);
            params.put("consName", consName);
        }
        Account account = getCurrentUser();
        if(account != null) {
            params.put("orgNo", account.getOrg().getOrgNo());
        }
        try {
            returnJSONView(AjaxHelper.toJSON(consumerService.queryConsumers(params),
                    AjaxHelper.SUCCESS, "查询用户列表成功"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询用户列表发生异常。", e);
            returnJSONView(AjaxHelper.toJSON(null, AjaxHelper.FAILURE,
                    "服务器异常，请稍后再试。"));
        }
    }


}
