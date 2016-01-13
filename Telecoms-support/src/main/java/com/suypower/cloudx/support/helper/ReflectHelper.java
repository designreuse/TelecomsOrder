/**
 * 文件名：ReflectHelper.java
 *
 * 版本信息：
 * 日期：2014-4-11
 * Copyright © 2014 ,suypower.com All Rights Reserved
 *
 */
package com.suypower.cloudx.support.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 类名称：ReflectHelper
 * 类描述：反射工具类
 * 创建人：Fangyuan Chen
 * 创建时间：2014-09-03 下午7:04:28
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0
 */
public class ReflectHelper {

	private Method method;

	private ReflectHelper(Method method) {
		this.method = method;
	}

	/**
	 * 创建反射实例
	 * 
	 * @param clazz
	 * @param method
	 * @param parameterTypes
	 * @return
	 * @throws Exception
	 */
	public static ReflectHelper instance(Class<?> clazz, String method,
			Class<?>... parameterTypes) throws Exception {
		Method targetMethod = clazz.getDeclaredMethod(method, parameterTypes);
		return new ReflectHelper(targetMethod);
	}

	/**
	 * 调用方法
	 * 
	 * @param targetBean
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public Object invoke(Object targetBean, Object... args) throws Exception {
		return method.invoke(targetBean, args);
	}
	
	public static Object invokeMethod(Object targetBean,Method method, Object... args) throws Exception{
		return method.invoke(targetBean, args);
	}
	
	public static Method instanceMethod(Object targetBean,String methodName,Class<?>... parameterTypes) throws Exception{
		Method targetMethod = targetBean.getClass().getDeclaredMethod(methodName, parameterTypes);
		return targetMethod;
	}

	public void invokeVoid(Object targetBean, Object... args) throws Exception {
		method.invoke(targetBean, args);
	}

	public static void main(String[] args) throws Exception {
		String str = new String("123456");
		Class<?> clazz = str.getClass();
		System.out.println(ReflectHelper.instance(clazz, "indexOf",
				String.class).invoke(str, "3"));
	}

	public static Object getFieldValue(Object obj, String fieldName) {
		Object result = null;
		Field field = getField(obj, fieldName);
		if (field != null) {
			field.setAccessible(true);
			try {
				result = field.get(obj);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 利用反射获取指定对象里面的指定属性
	 * 
	 * @param obj
	 *            目标对象
	 * @param fieldName
	 *            目标属性
	 * @return 目标字段
	 */
	private static Field getField(Object obj, String fieldName) {
		Field field = null;
		for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz
				.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName);
				break;
			} catch (NoSuchFieldException e) {
				// 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
			}
		}
		return field;
	}

	/**
	 * 利用反射设置指定对象的指定属性为指定的值
	 * 
	 * @param obj
	 *            目标对象
	 * @param fieldName
	 *            目标属性
	 * @param fieldValue
	 *            目标值
	 */
	public static void setFieldValue(Object obj, String fieldName,
			String fieldValue) {
		Field field = getField(obj, fieldName);
		if (field != null) {
			try {
				field.setAccessible(true);
				field.set(obj, fieldValue);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
