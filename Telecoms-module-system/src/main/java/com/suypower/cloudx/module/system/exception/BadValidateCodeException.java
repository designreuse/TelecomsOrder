/**
 * 文件名：BadValidateCodeException.java
 *
 * 版本信息：
 * 日期：2015-6-17
 * Copyright © 2015 ,suypower.com All Rights Reserved
 *
 */
package com.suypower.cloudx.module.system.exception;

import org.springframework.security.core.AuthenticationException;

/** 
 * 类名称：BadValidateCodeException
 * 类描述：
 * 创建人：Tao Zheng
 * 创建时间：2015-6-17 下午6:49:04
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public class BadValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public BadValidateCodeException(String message, Throwable cause) {
	super(message, cause);
    }

    public BadValidateCodeException(String message) {
	super(message);
    }


}
