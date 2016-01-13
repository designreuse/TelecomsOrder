/**
 * 文件名：ControllerSupport.java
 *
 * 版本信息：
 * 日期：Sep 5, 2014
 * Copyright © 2014 , All Rights Reserved
 *
 */
package com.suypower.cloudx.web.support;

import com.suypower.cloudx.module.system.entity.Account;
import com.suypower.cloudx.support.jdbc.Page;
import com.suypower.cloudx.support.util.StringUtil;
import com.suypower.cloudx.web.SysConst;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 类名称：ControllerSupport
 * 类描述：Cotroller基类
 * 创建人：Fangyuan Chen
 * 创建时间：Sep 5, 2014 10:29:04 AM
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @version 1.0
 */
public class ControllerSupport {

    protected static final Integer PAGE_SIZE = 20;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected ServletContext context;
    /**
     * 设置Request和Response
     *
     * @param arg0
     * @param arg1
     */
    @ModelAttribute
    public void setRequestAndResponse(HttpServletRequest arg0,
                                      HttpServletResponse arg1) {
        this.request = arg0;
        this.response = arg1;
        this.session = request.getSession();
        this.context = this.context == null ? ContextLoader
                .getCurrentWebApplicationContext().getServletContext()
                : this.context;
    }

    /**
     * 获取参数Map
     *
     * @return
     */
    protected Map<String, String> getParamMap() {
        Map<String, String> params = new HashMap<String, String>();
        if (request != null) {
            Enumeration<?> nameEnumeration = request.getParameterNames();
            while (nameEnumeration.hasMoreElements()) {
                String paramName = String
                        .valueOf(nameEnumeration.nextElement());
                String paramValue = request.getParameter(paramName);
                params.put(paramName, "".equals(paramValue) ? null : paramValue);
            }
        }
        Account account = getCurrentUser();
        params.put("currentUser", account.getAccountID());
        params.put("orgNo", account.getOrg().getOrgNo());
        return params;
    }

    protected Map<String, Object> getParamsObjectMap() {
        Map<String, Object> params = new HashMap<String, Object>();
        if (request != null) {
            Enumeration<?> nameEnumeration = request.getParameterNames();
            while (nameEnumeration.hasMoreElements()) {
                String paramName = String
                        .valueOf(nameEnumeration.nextElement());
                String paramValue = request.getParameter(paramName);
                if ("true".equalsIgnoreCase(paramValue)
                        || "false".equalsIgnoreCase(paramValue)) {
                    params.put(paramName, Boolean.parseBoolean(paramValue));
                } else {
                    params.put(paramName, "".equals(paramValue) ? null : paramValue);
                }
            }
        }
        Account account = getCurrentUser();
        params.put("currentUser", account.getAccountID());
        params.put("orgNo", account.getOrg().getOrgNo());
        return params;
    }

    /**
     * 获取参数Map
     *
     * @return
     */
    protected Map<String, String> getParamsStringMap() {
        Map<String, String> params = new HashMap<String, String>();
        if (request != null) {
            Enumeration<?> nameEnumeration = request.getParameterNames();
            while (nameEnumeration.hasMoreElements()) {
                String paramName = String
                        .valueOf(nameEnumeration.nextElement());
                String paramValue = request.getParameter(paramName);
                params.put(paramName, "".equals(paramValue) ? null : paramValue);
            }
        }
        Account account = getCurrentUser();
        params.put("currentUser", account.getAccountID());
        params.put("orgNo", account.getOrg().getOrgNo());
        return params;
    }

    /**
     * 获取参数Map(参数名大写)
     *
     * @return
     */
    protected Map<String, String> getUppercaseParamMap() {
        Map<String, String> params = new HashMap<String, String>();
        Enumeration<?> nameEnumeration = request.getParameterNames();
        while (nameEnumeration.hasMoreElements()) {
            String paramName = String.valueOf(nameEnumeration.nextElement());
            String paramValue = request.getParameter(paramName);
            params.put(paramName.toUpperCase(), "".equals(paramValue) ? null : paramValue);
        }
        return params;
    }

    protected Page<?> getParamsPage() {
        Page page = new Page();
        Map<String, Object> params = getParamsObjectMap();
        String size = params.get("rows") == null ? "10" : String.valueOf(params
                .get("rows"));
        String pageNo = params.get("page") == null ? "1" : String
                .valueOf(params.get("page"));

        size = StringUtil.isEmpty(size) ? "10" : size;
        pageNo = (StringUtil.isEmpty(pageNo) || "0".equals(pageNo)) ? "1"
                : pageNo;
        page.setPageSize(Integer.parseInt(size));
        page.setPageNo(Integer.parseInt(pageNo));
        page.setParams(params);
        return page;
    }

    protected Page<?> getParamsPage4BTable() {
        Page page = new Page();
        Map<String, Object> params = getParamsObjectMap();
        String size = params.get("limit") == null ? "10" : String.valueOf(params
                .get("limit"));
        String offset = params.get("offset") == null ? "0" : String
                .valueOf(params.get("offset"));

        size = StringUtil.isEmpty(size) ? "10" : size;
        int pageNo = (StringUtil.isEmpty(offset) || "0".equals(offset)) ? 1
                : (Integer.parseInt(offset) / Integer.parseInt(size)) + 1;
        page.setPageSize(Integer.parseInt(size));
        page.setPageNo(pageNo);
        page.setParams(params);
        return page;
    }

    protected Page<?> getParamsPage4JQGrid() {
        Page page = new Page();
        Map<String, Object> params = getParamsObjectMap();
        String size = params.get("rows") == null ? "10" : String.valueOf(params
                .get("rows"));
        String pageNo = params.get("page") == null ? "1" : String
                .valueOf(params.get("page"));

        size = StringUtil.isEmpty(size) ? "10" : size;
        pageNo = (StringUtil.isEmpty(pageNo) || "0".equals(pageNo)) ? "1"
                : pageNo;
        page.setPageSize(Integer.parseInt(size));
        page.setPageNo(Integer.parseInt(pageNo));
        page.setParams(params);
        return page;
    }

    /**
     * 返回JSON对象
     * @param json
     * @throws IOException
     */
    public void returnJSONView(String json) throws IOException{
        HttpResponseHelper.response(response,json);
    }

    /**
     * 获取当前登录人信息
     * @return
     */
    protected Account getCurrentUser(){
        Account user = null;
        Object object = session.getAttribute(SysConst.SESSION_USER);
        if(object instanceof Account){
            user = (Account) object;
        }
        return user;
    }
}
