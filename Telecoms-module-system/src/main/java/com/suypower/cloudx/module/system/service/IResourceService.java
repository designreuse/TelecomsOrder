package com.suypower.cloudx.module.system.service;

import com.suypower.cloudx.module.system.entity.Resource;
import com.suypower.cloudx.module.system.entity.Role;
import com.suypower.cloudx.support.exception.CloudxException;

import java.util.List;

/**
 * Created by Bingdor on 2015/11/17.
 */
public interface IResourceService {
    /**
     * 根据单位编号查询权限信息
     * @param orgNo
     * @return
     * @throws CloudxException
     */
    public List<Resource> queryResources(String orgNo) throws CloudxException;

    /**
     * 保存或更新权限信息
     * @param resource
     * @return
     * @throws CloudxException
     */
    public Boolean saveOrUpdateResource(Resource resource) throws CloudxException;

    /**
     * 删除权限信息
     * @param resourceID
     * @return
     * @throws CloudxException
     */
    public Boolean deleteResource(String resourceID) throws CloudxException;

    /**
     * 查询拥有该项权限的角色信息
     * @param resourceID
     * @return
     * @throws CloudxException
     */
    public List<Role> queryResourceRoles(String resourceID) throws CloudxException;
}
