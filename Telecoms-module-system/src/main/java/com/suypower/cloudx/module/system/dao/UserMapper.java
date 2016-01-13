package com.suypower.cloudx.module.system.dao;

import com.suypower.cloudx.module.system.dto.UserDto;
import com.suypower.cloudx.module.system.entity.Account;

import java.util.List;

/**
 * Created by Bingdor on 2015/11/13.
 */
public interface UserMapper {
    public List<Account> queryUsers(UserDto userDto);
}
