/**
 * 文件名：MyBatisPageInterceptor.java
 *
 * 版本信息：
 * 日期：2014-09-03
 * Copyright © 2014 ,suypower.com All Rights Reserved
 *
 */
package com.suypower.cloudx.support.jdbc;

import com.suypower.cloudx.support.helper.ReflectHelper;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;


/**
 * 类名称：MyBatisPageInterceptor
 * 类描述：Mybatis分页拦截器
 * 创建人：Fangyuan Chen
 * 创建时间：2014-09-03 下午7:04:28
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @version 1.0
 */
@Intercepts({
        @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class})})
public class MyBatisPageInterceptor implements Interceptor {

    private String dialect;//数据库类型，不同的数据库有不同的分页方法

    /**
     * 拦截后要执行的方法
     */
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        //通过反射获取到当前RoutingStatementHandler对象的delegate属性  
        StatementHandler delegate = (StatementHandler) ReflectHelper.getFieldValue(handler, "delegate");
        //获取到当前StatementHandler的 boundSql，这里不管是调用handler.getBoundSql()还是直接调用delegate.getBoundSql()结果是一样的，因为之前已经说过了  
        //RoutingStatementHandler实现的所有StatementHandler接口方法里面都是调用的delegate对应的方法。  
        BoundSql boundSql = delegate.getBoundSql();
        //拿到当前绑定Sql的参数对象，就是我们在调用对应的Mapper映射语句时所传入的参数对象  
        Object obj = boundSql.getParameterObject();
        if (obj instanceof Page) {
            Page page = (Page) obj;
            MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getFieldValue(delegate, "mappedStatement");
            //拦截到的prepare方法参数是一个Connection对象  
            Connection connection = (Connection) invocation.getArgs()[0];
            //获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句  
            String sql = boundSql.getSql();
            //给当前的page参数对象设置总记录数  
            this.setTotalRecord(page, mappedStatement, connection);
            //获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句
            //获取分页Sql语句
            String pageSql = this.getPageSql(page, sql);
            //利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
            ReflectHelper.setFieldValue(boundSql, "sql", pageSql);
        }
        return invocation.proceed();
    }


    /**
     * 拦截器对应的封装原始对象的方法
     */
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 设置注册拦截器时设定的属性
     */
    public void setProperties(Properties properties) {
        this.dialect = properties.getProperty("dialect");
    }

    /**
     * 根据page对象获取对应的分页查询Sql语句，这里只做了两种数据库类型，Mysql和Oracle
     * 其它的数据库都 没有进行分页
     *
     * @param page 分页对象
     * @param sql  原sql语句
     * @return
     */
    private String getPageSql(Page<?> page, String sql) {
        StringBuffer sqlBuffer = new StringBuffer(sql);
        PageSqlGenerator generator = null;
        Class<?> clazz;
        try {
            clazz = Class.forName(this.getClass().getPackage().getName()+"." + dialect + "PageGenerator");
            generator = (PageSqlGenerator) clazz.newInstance();
            return generator.getPageSql(page, sqlBuffer);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 给当前的参数对象page设置总记录数
     *
     * @param page            Mapper映射语句对应的参数对象
     * @param mappedStatement Mapper映射语句
     * @param connection      当前的数据库连接
     */
    private void setTotalRecord(Page<?> page,
                                MappedStatement mappedStatement, Connection connection) {
        //获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。
        //delegate里面的boundSql也是通过mappedStatement.getBoundSql(paramObj)方法获取到的。
        BoundSql boundSql = mappedStatement.getBoundSql(page);
        //获取到我们自己写在Mapper映射语句中对应的Sql语句
        String sql = boundSql.getSql();
        //通过查询Sql语句获取到对应的计算总记录数的sql语句
        String countSql = this.getCountSql(sql);
        //通过BoundSql获取对应的参数映射
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        //利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);
        //通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);
        //通过connection建立一个countSql对应的PreparedStatement对象。
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(countSql);
            //通过parameterHandler给PreparedStatement对象设置参数
            parameterHandler.setParameters(pstmt);
            //之后就是执行获取总记录数的Sql语句和获取结果了。
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int totalRecord = rs.getInt(1);
                //给当前的参数page对象设置总记录数
                page.setTotalRecord(totalRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据原Sql语句获取对应的查询总记录数的Sql语句
     *
     * @param sql
     * @return
     */
    private String getCountSql(String sql) {
        return "SELECT COUNT(0) FROM (" + sql + ") AS a"+ UUID.randomUUID().toString().replaceAll("-","");
    }
}