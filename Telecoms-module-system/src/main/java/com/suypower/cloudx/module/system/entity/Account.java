package com.suypower.cloudx.module.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

public class Account extends User implements UserDetails {

	public Account(String username, String password, boolean enabled,
				   boolean accountNonExpired, boolean credentialsNonExpired,
				   boolean accountNonLocked,
				   Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}

	public Account(String username, String password){
		super(username, password, true, true, true,
				true, new HashSet<GrantedAuthority>());
	}
	
	/**
	 * 创建用户实例
	 * newInstance(这里用一句话描述这个方法的作用)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 */
	public static Account newInstance(String username,String password){
		 Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		return new Account(username, password, true,true,true,true,authorities);
	}

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	private String accountID;
	/**
	 * 用户姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * EMAIL
	 */
	private String email;
	/**
	 * 联系电话
	 */
	private String tel;
	/**
	 * 员工编号
	 */
	private String empNo;

	/**
	 * 组织编号
	 */
	private  Organization org;
	
	/**
	 * 部门
	 */
	private Department department;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 锁定原因
	 */
	private String lockReason;

	private Date lockTime;

	/**
	 * 绑定IP
	 */
	private String ip;
	/**
	 * 绑定MAC
	 */
	private String mac;
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 创建人
	 */
	private Long createAccount;
	
	/**
	 * 解锁时间
	 */
	private Date unlockTime;
	
	/**
	 * 解锁人员
	 */
	private Account unlockAccount;
	
	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	
	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmpNo() {
		return empNo;
	}

	public Organization getOrg() {
		return org;
	}
	public void setOrg(Organization org) {
		this.org = org;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Department getDepartment() {
		return department;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLockReason() {
		return lockReason;
	}

	public void setLockReason(String lockReason) {
		this.lockReason = lockReason;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateAccount() {
		return createAccount;
	}

	public void setCreateAccount(Long createAccount) {
		this.createAccount = createAccount;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getUnlockTime() {
		return unlockTime;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public void setUnlockTime(Date unlockTime) {
		this.unlockTime = unlockTime;
	}

	public Account getUnlockAccount() {
		return unlockAccount;
	}

	public void setUnlockAccount(Account unlockAccount) {
		this.unlockAccount = unlockAccount;
	}

//	public static Account newInstance(Map<String,Object> map) {
//		Account account = newInstance(String.valueOf(map.get("SYS_USER_NAME")),String.valueOf(map.get("PWD")));
//		account.setName(String.valueOf(map.get("USER_NAME")));
//		account.setAccountID(String.valueOf(map.get("ACCOUNT_ID")));
////		account.setDepartment(account.getDepartment());
////		account.setEmail(account.getEmail());
////		account.setSex(account.getSex());
////		account.setEmpNo(account.getEmpNo());
////		account.setIp(account.getIp());
////		account.setMac(account.getMac());
//		account.setOrg(map.get("ORG_NO"));
////		account.setTel(account.getTel());
////		account.setCreateTime(account.getCreateTime());
////		account.setLockReason(account.getLockReason());
////		account.setUnlockAccount(account.getUnlockAccount());
////		account.setUnlockTime(account.getUnlockTime());
//		return account;
//	}
}
