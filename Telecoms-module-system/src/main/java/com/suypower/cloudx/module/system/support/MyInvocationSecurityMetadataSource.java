/**
 * 权限资源管理器，构建角色与权限对应关系
 * @author Fangyuan Chen
 * @date 2014-10-14
 * @version 1.0
 */
package com.suypower.cloudx.module.system.support;

import bsh.EvalError;
import bsh.Interpreter;
import com.suypower.cloudx.module.system.entity.Account;
import com.suypower.cloudx.module.system.entity.Resource;
import com.suypower.cloudx.module.system.entity.Role;
import com.suypower.cloudx.module.system.service.IResourceService;
import com.suypower.cloudx.module.system.service.IRoleService;
import com.suypower.cloudx.support.exception.CloudxException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.*;

public class MyInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	private static Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
	private IResourceService resourceService;
	
	public MyInvocationSecurityMetadataSource(IRoleService roleService, IResourceService resourceService) {
		super();
		this.resourceService = resourceService;
	}

	public boolean access(String url, UserDetails user) {
		Account account = (Account) user;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("url",url);
		map.put("orgNo",account.getOrg().getOrgNo());
		Collection<ConfigAttribute> configAttributes = getAttributes(map);
		// url未设定权限
		if (configAttributes == null){
			return true;
		}
		// 权限判定
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig) ca).getAttribute();
			for (GrantedAuthority ga : account.getAuthorities()) {
				if (needRole.equals(ga.getAuthority())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean accessWithExpression(String url, UserDetails user)
	{
		String urlCopy = url;
		urlCopy = urlCopy.replace("(", "$");
		urlCopy = urlCopy.replace(")", "$");
		urlCopy = urlCopy.replace("!", "$");
		urlCopy = urlCopy.replace("&", "$");
		urlCopy = urlCopy.replace("|", "$");
		String[] strs = urlCopy.split("\\$");
		for(int i = 0;i<strs.length;i++)
		{
			String str = strs[i];
			if(str == null || str.equals(""))
			{
				continue;
			}
			url = url.replace(str, String.valueOf(access(str,user)));
		}
		boolean isSuccess = false; 
		Interpreter interpreter=new Interpreter();
		try {
			isSuccess = (Boolean) interpreter.eval(url);
		} catch (EvalError e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		Collection<ConfigAttribute> atts = null;
		if(object  instanceof Map){
			Map<String,Object> map = (Map<String, Object>) object;
			String url = String.valueOf(map.get("url"));
			String orgNo = String.valueOf(map.get("orgNo"));
			if(object != null){
				if(object instanceof String){
					url = (String) object;
				}else{
					url = ((FilterInvocation) object).getRequestUrl();
				}
				//[1]查询出所有权限
				atts = new ArrayList<ConfigAttribute>();
				List<Resource> resources = null;
				try {
					resources = resourceService.queryResources(orgNo);
				} catch (CloudxException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < resources.size(); i++) {
					Resource resource = resources.get(i);
					//[2]匹配权限url与当前访问url
					if (urlMatcher.pathMatchesUrl(resource.getSource(), url)) {
						//[3]找到数据库中的权限资源对应的所有角色
						List<Role> roles = null;
						try {
							roles = resourceService.queryResourceRoles(resource.getResourceID());
						} catch (CloudxException e) {
							e.printStackTrace();
						}
						//[4]组织结果返回
						for (int j = 0; j < roles.size(); j++) {
							Role role = roles.get(j);
							ConfigAttribute ca = new SecurityConfig(String.valueOf(role.getRoleID()));
							atts.add(ca);
						}
						//添加管理员访问权限
//					ConfigAttribute adminCa = new SecurityConfig(SysConst.SUPER_ADMIN_ROLE_ID);
//					atts.add(adminCa);
						break;
					}
				}
			}
		}
		return atts;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return configAttributes;
	}

}
