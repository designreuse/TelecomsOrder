/**
 * 文件名：OraclePageGenerator.java
 *
 * 版本信息：
 * 日期：2014-09-03
 * Copyright © 2014 ,suypower.com All Rights Reserved
 *
 */
package com.suypower.cloudx.support.jdbc;


/** 
 * 类名称：OraclePageGenerator
 * 类描述：Oracle分页sql生成器
 * 创建人：Fangyuan Chen
 * 创建时间：2014-09-03 下午7:04:28
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public class OraclePageGenerator implements PageSqlGenerator {

	public String getPageSql(Page page, StringBuffer sqlBuffer) {
		//计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的
       int offset = (page.getPageNo() - 1) * page.getPageSize() + 1;
       sqlBuffer.insert(0, "select u.*, rownum r from (").append(") u where rownum < ").append(offset + page.getPageSize());
       sqlBuffer.insert(0, "select * from (").append(") where r >= ").append(offset);
	   //上面的Sql语句拼接之后大概是这个样子：
	   //select * from (select u.*, rownum r from (select * from t_user) u where rownum < 31) where r >= 16
       return sqlBuffer.toString();
	}

}
