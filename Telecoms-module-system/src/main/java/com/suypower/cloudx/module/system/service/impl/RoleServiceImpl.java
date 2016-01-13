package com.suypower.cloudx.module.system.service.impl;

import com.suypower.cloudx.module.system.dao.RoleMapper;
import com.suypower.cloudx.module.system.entity.Role;
import com.suypower.cloudx.module.system.service.IRoleService;
import com.suypower.cloudx.support.exception.CloudxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Bingdor on 2015/11/23.
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> queryRoles(String orgNo) throws CloudxException {
        return null;
    }

    @Override
    public List<Role> queryRoles(String userID, String orgNo) throws CloudxException {
        return roleMapper.queryRoles(userID,orgNo);
    }

    @Override
    public Boolean saveOrUpdateRole(Role role) throws CloudxException {
        return null;
    }

    @Override
    public Boolean deleteRole(String roleID) throws CloudxException {
        return null;
    }
}
