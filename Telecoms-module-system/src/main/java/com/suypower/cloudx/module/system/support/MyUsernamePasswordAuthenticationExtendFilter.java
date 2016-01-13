package com.suypower.cloudx.module.system.support;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyUsernamePasswordAuthenticationExtendFilter extends
		UsernamePasswordAuthenticationFilter {

	// 验证码字段
	private String validateCodeParameter = "flute_validateCode";
	// 是否开启验证码功能
	private boolean validateCodeEnable = true;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		// 只接受POST方式传递的数据
//		if (!"POST".equals(request.getMethod()))
//			throw new AuthenticationCancelledException("不支持非POST方式的请求!");

		// 开启验证码功能的情况
		if (isValidateCodeEnable()){
			checkValidateCode(request);
		}

		// 获取Username和Password
		String username = obtainUsername(request);
		String password = obtainPassword(request);

		// UsernamePasswordAuthenticationToken实现Authentication校验
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);

		// 允许子类设置详细属性
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	// 匹对验证码的正确性
	public void checkValidateCode(HttpServletRequest request) {

//		String jcaptchaCode = obtainValidateCodeParameter(request);
//		if (null == jcaptchaCode)
//			throw new BadValidateCodeException("验证码超时,请重新获取.");
//		boolean bool = false;
//		String code = request.getSession().getAttribute(SysConst.SYS_VALIDATE_COBadValidateCodeExceptionDE) == null ? "":(String)request.getSession().getAttribute(SysConst.SYS_VALIDATE_CODE);
//		bool = code.equalsIgnoreCase(jcaptchaCode);
//		if (!bool){
//			throw new BadValidateCodeException("您填写的验证码不正确");
//		}
	}
	
	public String obtainValidateCodeParameter(HttpServletRequest request) {
		Object obj = request.getParameter(getValidateCodeParameter());
		return null == obj ? "" : obj.toString().trim();
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(getUsernameParameter());
		return null == obj ? "" : obj.toString().trim();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(getPasswordParameter());
		return null == obj ? "" : obj.toString().trim();
	}

	public String getValidateCodeParameter() {
		return validateCodeParameter;
	}

	public void setValidateCodeParameter(String validateCodeParameter) {
		this.validateCodeParameter = validateCodeParameter;
	}

	public boolean isValidateCodeEnable() {
		return validateCodeEnable;
	}

	public void setValidateCodeEnable(boolean validateCodeEnable) {
		this.validateCodeEnable = validateCodeEnable;
	}

}