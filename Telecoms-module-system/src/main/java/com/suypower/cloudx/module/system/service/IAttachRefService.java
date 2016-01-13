package com.suypower.cloudx.module.system.service;

import com.suypower.cloudx.module.system.entity.AttachRef;
import com.suypower.cloudx.storage.exception.DataException;

import java.util.List;

/**
 * Created by mengcheng.yu on 2015/12/8.
 */
public interface IAttachRefService
{
    /**
     * 新增文件
     * @param attachRefList
     * @return
     */
    List<AttachRef> insertAttachRef(List<AttachRef> attachRefList);

    /**
     * 删除文件
     * @param belongId
     * @return
     */
    boolean deleteAttachRef(String belongId);

    /**
     * 通过主键删除文件关联关系
     * @param attachRefIdList attachIdList
     * @return
     */
    boolean deleteAttachRefByIds(List<String> attachRefIdList, List<String> attachIdList) throws DataException;
}
