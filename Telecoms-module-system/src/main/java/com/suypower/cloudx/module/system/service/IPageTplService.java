package com.suypower.cloudx.module.system.service;

import com.suypower.cloudx.module.system.entity.PageTemplate;
import com.suypower.cloudx.support.exception.CloudxException;

/**
 * Created by Bingdor on 2015/12/1.
 */
public interface IPageTplService {
    public PageTemplate findUserPageTpl(String userID) throws CloudxException;
}
