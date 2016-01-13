package com.suypower.cloudx.module.system.service;

import com.suypower.cloudx.module.system.entity.SysCode;

import java.util.List;

/**
 * Created by Zhengtao on 2015/12/2.
 */
public interface ISysCodeService {

    /**
     * 查询系统代码
     * @param codeSortId
     * @return
     */
    public List<SysCode> querySysCodes(String codeSortId);

    /**
     * 查询系统代码
     * @param sortName
     * @return
     */
    public List<SysCode> querySysCodesBySortName(String sortName);

}
