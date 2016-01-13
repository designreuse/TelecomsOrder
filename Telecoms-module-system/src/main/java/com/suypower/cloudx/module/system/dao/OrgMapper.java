package com.suypower.cloudx.module.system.dao;

import com.suypower.cloudx.module.system.entity.Organization;

/**
 * Created by Bingdor on 2015/11/25.
 */
public interface OrgMapper {
    public Organization queryOrgByOgrNo(String orgNo);
}
