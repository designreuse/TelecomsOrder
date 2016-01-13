package com.suypower.cloudx.module.system.service.impl;

import com.suypower.cloudx.module.system.dao.SysCodeMapper;
import com.suypower.cloudx.module.system.entity.SysCode;
import com.suypower.cloudx.module.system.service.ISysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhengtao on 2015/12/2.
 */
@Service
public class SysCodeServiceImpl implements ISysCodeService{

    private SysCodeMapper sysCodeMapper;

    @Autowired
    public void setSysCodeMapper(SysCodeMapper sysCodeMapper) {
        this.sysCodeMapper = sysCodeMapper;
    }

    @Override
    public List<SysCode> querySysCodes(String codeSortId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("codeSortId", codeSortId);
        List<SysCode> sysCodes = sysCodeMapper.querySysCodes(params);
        if(sysCodes == null || sysCodes.size() == 0) {
            return Collections.emptyList();
        }
        return sysCodes;
    }

    @Override
    public List<SysCode> querySysCodesBySortName(String sortName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("codeSortName", sortName);
        List<SysCode> sysCodes = sysCodeMapper.querySysCodes(params);
        if(sysCodes == null || sysCodes.size() == 0) {
            return Collections.emptyList();
        }
        return sysCodes;
    }
}
