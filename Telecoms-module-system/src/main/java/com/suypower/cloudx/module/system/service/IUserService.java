package com.suypower.cloudx.module.system.service;

import com.suypower.cloudx.module.system.dto.UserDto;
import com.suypower.cloudx.module.system.entity.Account;
import com.suypower.cloudx.support.exception.CloudxException;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Bingdor on 2015/11/13.
 */
public interface IUserService extends UserDetailsService{
    public Account findUser(UserDto userDto) throws CloudxException;
}
