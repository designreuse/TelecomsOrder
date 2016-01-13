package com.suypower.cloudx.module.system.dao;

import com.suypower.cloudx.module.system.entity.Menu;
import com.suypower.cloudx.support.jdbc.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by Bingdor on 2015/11/27.
 */
public interface MenuMapper {
    public List<Menu> queryMenus(String orgNo);

    public List<Menu> querySubMenus(Map<String,Object> params);

    public List<Menu> queryMenus(Page<?> page);
}
