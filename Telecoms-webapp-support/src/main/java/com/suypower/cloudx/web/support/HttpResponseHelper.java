/**
 * 文件名：HttpMessageResponser.java
 *
 * 版本信息：
 * 日期：2013-11-14
 * Copyright © 2013 ,suypower.com All Rights Reserved
 *
 */
package com.suypower.cloudx.web.support;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/** 
 * 类名称：HttpMessageResponser
 * 类描述：Http消息返回助手
 * 创建人：Fangyuan Chen
 * 创建时间：2013-11-14 上午9:36:44
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public class HttpResponseHelper {

	/**
	 * 返回消息
	 * @param response
	 * @param CONTENT_TYPE
	 * @param outstring
	 * @throws IOException
	 */
	public static void response(HttpServletResponse response, String json) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(json.getBytes("UTF-8"));
		outputStream.flush();
		outputStream.close();
	}
}
