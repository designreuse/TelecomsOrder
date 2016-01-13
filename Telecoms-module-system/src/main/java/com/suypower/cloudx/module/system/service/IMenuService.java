package com.suypower.cloudx.module.system.service;

import com.suypower.cloudx.module.system.entity.Menu;
import com.suypower.cloudx.support.exception.CloudxException;

import java.util.List;

/**
 * Created by Bingdor on 2015/11/27.
 */
public interface IMenuService {
    public List<Menu> findMenus(String orgNo) throws CloudxException;

}
