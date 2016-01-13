package com.suypower.cloudx.module.system.support;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAccessDeniedHandler implements AccessDeniedHandler {
	private String ajaxExpiredUrl;
	private String errorPage;  
	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		 Boolean isAjax = isAjaxRequest(request);  
	        if(isAjax){  
	        	RequestDispatcher dispatcher = request.getRequestDispatcher(ajaxExpiredUrl);
                dispatcher.forward(request, response);  
                return;
	        }else if (!response.isCommitted()) {  
	            if (errorPage != null) {  
	                // Put exception into request scope (perhaps of use to a view)  
	                request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);  
	  
	                // Set the 403 status code.  
	                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	  
	                // forward to error page.  
	                RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
	                dispatcher.forward(request, response);  
	            } else {  
	                response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
	            }  
	        }  

	}
	
	private Boolean isAjaxRequest(HttpServletRequest request){
		return request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equalsIgnoreCase(
						"XMLHttpRequest");
	}

	public String getAjaxExpiredUrl() {
		return ajaxExpiredUrl;
	}

	public void setAjaxExpiredUrl(String ajaxExpiredUrl) {
		this.ajaxExpiredUrl = ajaxExpiredUrl;
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

}
