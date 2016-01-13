/**
 * 文件名：AjaxHelper.java
 *
 * 版本信息：
 * 日期：Dec 6, 2013
 * Copyright © 2013 ,suypower.com All Rights Reserved
 *
 */
package com.suypower.cloudx.support.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suypower.cloudx.support.jdbc.Page;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类名称：AjaxHelper
 * 类描述：
 * 创建人：Fangyuan Chen
 * 创建时间：Dec 6, 2013 8:12:01 PM
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @version 1.0
 */
public final class AjaxHelper {
    public static final String SUCCESS = "0";
    public static final String FAILURE = "1";

    private static final String STATUS = "status";
    private static final String MSG = "msg";
    private static final String DATA = "data";

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static ObjectMapper mapper;

    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        mapper = new ObjectMapper();
        mapper.setDateFormat(dateFormat);
    }

    /**
     * 生成JSON消息
     *
     * @param object
     * @param flag
     * @param msg
     * @return
     */
    public static String toJSON(Object object, String flag, String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(STATUS, flag);
        map.put(MSG, msg);
        map.put(DATA, object);
        String resultStr = "";
        try {
            resultStr = JSONHelper.Object2JSON(map);
        } catch (Exception e) {
            return resultStr;
        }
        return resultStr;
    }

    public static String toJSON(List list) {
        /*String resultStr = "";
        try {
            resultStr = JSONHelper.Object2JSON(list);
        } catch (Exception e) {
            return resultStr;
        }
        return resultStr;*/
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", 1);
        map.put("records", list.size());
        map.put("rows", list);
        String resultStr = "";
        try {
            resultStr = JSONHelper.Object2JSON(map);
        } catch (Exception e) {
            return resultStr;
        }
        return resultStr;
    }

    /**
     * 生成JSON消息
     * @param page
     * @return
     */
    public static String toJSON(Page page) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", page.getTotalPage());
        map.put("records", page.getTotalRecord());
        map.put("totalrows", page.getTotalRecord());
        map.put("rows", page.getResults());
        String resultStr = "";
        try {
            resultStr = JSONHelper.Object2JSON(map);
        } catch (Exception e) {
            return resultStr;
        }
        return resultStr;
    }

}
