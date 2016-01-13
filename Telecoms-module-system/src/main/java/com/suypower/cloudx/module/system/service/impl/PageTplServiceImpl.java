package com.suypower.cloudx.module.system.service.impl;

import com.suypower.cloudx.module.system.dao.PageTemplateMapper;
import com.suypower.cloudx.module.system.entity.PageTemplate;
import com.suypower.cloudx.module.system.service.IPageTplService;
import com.suypower.cloudx.support.exception.CloudxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Bingdor on 2015/12/1.
 */
@Service
public class PageTplServiceImpl implements IPageTplService {
    @Autowired
    private PageTemplateMapper pageTemplateMapper;
    public PageTemplate findUserPageTpl(String userID) throws CloudxException {
        return pageTemplateMapper.queryUserPageTpl(userID);
    }
}
