package com.suypower.cloudx.module.system.dao;

import com.suypower.cloudx.module.system.entity.SysCode;

import java.util.List;
import java.util.Map;

/**
 * Created by Zhengtao on 2015/12/2.
 */
public interface SysCodeMapper {

    /**
     * 查询系统代码
     * @param params
     * @return
     */
    public List<SysCode> querySysCodes(Map<String, Object> params);
}
