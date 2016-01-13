package com.suypower.cloudx.module.system.service.impl;

import com.suypower.cloudx.module.system.dao.UserMapper;
import com.suypower.cloudx.module.system.dto.UserDto;
import com.suypower.cloudx.module.system.entity.Account;
import com.suypower.cloudx.module.system.entity.Role;
import com.suypower.cloudx.module.system.service.IRoleService;
import com.suypower.cloudx.module.system.service.IUserService;
import com.suypower.cloudx.support.encrypt.DesEncrypter;
import com.suypower.cloudx.support.exception.CloudxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Bingdor on 2015/11/13.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IRoleService roleService;

    public Account findUser(UserDto userDto) throws CloudxException {
        Account user = null;
        try {
            List<Account> list = userMapper.queryUsers(userDto);
            if(list != null && !list.isEmpty()){
                user = list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CloudxException(e.getCause());
        }
        return user;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (logger.isDebugEnabled()) {
            logger.debug("---------->根据用户名查询用户信息 开始");
        }
        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        List<Account> list = null;
        try {
            list = userMapper.queryUsers(userDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (list == null || list.isEmpty()) {
            String message = "用户" + username + "不存在";
            logger.error(message);
            throw new UsernameNotFoundException(message);
        }
        Account account = list.get(0);
        String password = DesEncrypter.decrypt(account.getPassword());
        try {
            List<Role> roles = roleService.queryRoles(account.getAccountID(), account.getOrg().getOrgNo());
            if (roles == null || roles.isEmpty()) {
                throw new DisabledException("用户未分配角色，登录被拒绝。");
            }
            for (int i = 0; i < roles.size(); i++) {
                Role role = roles.get(i);
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleID());
                auths.add(authority);
            }
            String userStatus = String.valueOf(account.getStatus());
            if ("02".equals(userStatus)) { //删除标志
                throw new AccountExpiredException("用户不存在。");
            } else if ("03".equals(userStatus)) {//锁定标志
                throw new LockedException("用户被锁定，锁定原因：" + account.getLockReason());
            }
            if (logger.isDebugEnabled()) {
                logger.debug("---------->根据用户名查询用户信息 结束");
            }
            Account user = new Account(account.getUsername(),password);
            user.setAccountID(account.getAccountID());
            user.setOrg(account.getOrg());
            user.setEmpNo(account.getEmpNo());
            user.setSex(account.getSex());
            user.setLockReason(account.getLockReason());
            user.setEmail(account.getEmail());
            user.setIp(account.getIp());
            user.setTel(account.getTel());
            user.setMac(account.getMac());
            user.setStatus(account.getStatus());
            user.setUnlockTime(account.getUnlockTime());
            user.setDepartment(account.getDepartment());
            user.setName(account.getName());
            return user;
        } catch (CloudxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
