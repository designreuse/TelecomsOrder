package com.suypower.cloudx.module.system.service.impl;

import com.suypower.cloudx.module.system.dao.MenuMapper;
import com.suypower.cloudx.module.system.entity.Menu;
import com.suypower.cloudx.module.system.service.IMenuService;
import com.suypower.cloudx.support.exception.CloudxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Bingdor on 2015/11/27.
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public List<Menu> findMenus(String orgNo) throws CloudxException {
        return menuMapper.queryMenus(orgNo);
    }
}
