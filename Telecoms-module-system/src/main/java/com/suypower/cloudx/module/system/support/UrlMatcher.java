/**
 * url比较器接口
 * @author Fangyuan Chen
 * @date 2012-03-28
 * @version 1.0
 */
package com.suypower.cloudx.module.system.support;

public interface UrlMatcher {
	public Object compile(String paramString);
	
	public Boolean pathMatchesUrl(Object paramObject, String paramString);
	
	public String getUniversalMatchPattern();
	
	public Boolean requiresLowerCaseUrl();
}
