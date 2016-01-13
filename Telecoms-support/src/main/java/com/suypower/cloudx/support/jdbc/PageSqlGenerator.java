/**
 * 文件名：PageSqlGenerator.java
 *
 * 版本信息：
 * 日期：2014-09-03
 * Copyright © 2014 ,suypower.com All Rights Reserved
 *
 */
package com.suypower.cloudx.support.jdbc;

/** 
 * 类名称：PageSqlGenerator
 * 类描述：分页sql生成接口
 * 创建人：Fangyuan Chen
 * 创建时间：2014-09-03 下午7:04:28
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public interface PageSqlGenerator {
	
	public String getPageSql(Page<?> page, StringBuffer sqlBuffer);
	
}
