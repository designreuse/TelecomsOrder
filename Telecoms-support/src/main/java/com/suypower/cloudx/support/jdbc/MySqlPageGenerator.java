/**
 * 文件名：MySqlPageGenerator.java
 *
 * 版本信息：
 * 日期：2014-09-03
 * Copyright © 2014 ,suypower.com All Rights Reserved
 *
 */
package com.suypower.cloudx.support.jdbc;

/** 
 * 类名称：MySqlPageGenerator
 * 类描述：MySql分页sql生成器
 * 创建人：Fangyuan Chen
 * 创建时间：2014-09-03 下午7:04:28
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public class MySqlPageGenerator implements PageSqlGenerator {

	public String getPageSql(Page<?> page, StringBuffer sqlBuffer) {
		 //计算第一条记录的位置，Mysql中记录的位置是从0开始的。
		int offset = (page.getPageNo() - 1) * page.getPageSize();
		sqlBuffer.append(" limit ").append(offset).append(",").append(page.getPageSize());
		return sqlBuffer.toString();
	}

}
