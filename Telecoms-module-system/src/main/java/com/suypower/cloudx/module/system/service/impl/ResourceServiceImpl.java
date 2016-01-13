package com.suypower.cloudx.module.system.service.impl;

import com.suypower.cloudx.module.system.entity.Resource;
import com.suypower.cloudx.module.system.entity.Role;
import com.suypower.cloudx.module.system.service.IResourceService;
import com.suypower.cloudx.support.exception.CloudxException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Bingdor on 2015/11/23.
 */
@Service("resourceService")
public class ResourceServiceImpl implements IResourceService {
    @Override
    public List<Resource> queryResources(String orgNo) throws CloudxException {
        return null;
    }

    @Override
    public Boolean saveOrUpdateResource(Resource resource) throws CloudxException {
        return null;
    }

    @Override
    public Boolean deleteResource(String resourceID) throws CloudxException {
        return null;
    }

    @Override
    public List<Role> queryResourceRoles(String resourceID) throws CloudxException {
        return null;
    }
}
