/**
 * 文件名：JSONHelper.java
 *
 * 版本信息：
 * 日期：2013-10-29
 * Copyright © 2013 ,suypower.com All Rights Reserved
 *
 */
package com.suypower.cloudx.support.helper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 类名称：JSONHelper 
 * 类描述： JOSN助手
 * 创建人：Fangyuan Chen 
 * 创建时间：2013-10-29 下午2:07:38 
 * 修改人： 
 * 修改时间： 
 * 修改备注：
 * @version 1.0
 */
public class JSONHelper {
	private static final String DEFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";  
    private static  ObjectMapper mapper;  
	static {  
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);  
        mapper = new ObjectMapper();  
        mapper.setDateFormat(dateFormat);  
    }  

	/**
	 * 将Object转为JSON
	 * @param obj
	 * @return
	 * @throws RuntimeException
     */
	public static String Object2JSON(Object obj) {
		if(obj == null){
			return null;
		}
		StringWriter writer = new StringWriter();
		try {
			writer.write(mapper.writeValueAsString(obj));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
	}
	
	/**
	 * 将JOSN转为Object
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJSON(String json, Class<T> clazz) {
		if(json == null){
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 从JOSN流中转为Object
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJSON(InputStream json, Class<T> clazz) {
		if(json == null){
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把JSON转为List
	 * @param jstr
	 * @param li
	 * @return
	 */
	public static List<String> getJsonList(String jstr, List<String> li) {
		if(jstr == null){
			return null;
		}
		char[] cstr = jstr.toCharArray();
		boolean bend = false;
		int istart = 0;
		int iend = 0;
		for (int i = 0; i < cstr.length; i++) {
			if ((cstr[i] == '{') && !bend) {
				istart = i;
			}
			if (cstr[i] == '}' && !bend) {
				iend = i;
				bend = true;
			}
		}

		if (istart != 0) {
			String substr = jstr.substring(istart, iend + 1);
			jstr = jstr.substring(0, istart - 1)
					+ jstr.substring(iend + 1, jstr.length());
			substr = substr.replace(",\"children\":", "");
			substr = substr.replace("]", "");
			substr = substr.replace("[", "");
			li.add(substr);
			getJsonList(jstr, li);
		}
		return li;
	}

}
