package com.suypower.cloudx.module.system.service;

import com.suypower.cloudx.module.system.entity.Role;
import com.suypower.cloudx.support.exception.CloudxException;

import java.util.List;

/**
 * Created by Bingdor on 2015/11/17.
 */
public interface IRoleService {

    /**
     * 根据单位编号查询角色信息
     * @param orgNo
     * @return
     * @throws CloudxException
     */
    public List<Role> queryRoles(String orgNo) throws CloudxException;

    /**
     * 根据用户ID和单位编号查询角色信息
     * @param userID
     * @param orgNo
     * @return
     * @throws CloudxException
     */
    public List<Role> queryRoles(String userID,String orgNo) throws CloudxException;

    /**
     * 保存或更新角色信息
     * @param role
     * @return
     * @throws CloudxException
     */
    public Boolean saveOrUpdateRole(Role role) throws CloudxException;

    /**
     * 删除角色信息
     * @param roleID
     * @return
     * @throws CloudxException
     */
    public Boolean deleteRole(String roleID) throws CloudxException;

}
