package com.suypower.cloudx.module.system.dao;

import com.suypower.cloudx.module.system.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Bingdor on 2015/11/23.
 */
public interface RoleMapper {

    public List<Role> queryRoles(String orgNo);

    public List<Role> queryRoles(@Param("userID") String userID, @Param("orgNo")String orgNo);

    public Integer saveOrUpdateRole(Role role);

    public Boolean deleteRole(String roleID);

    public Role queryRoleByID(String roleID);
}
