package com.suypower.cloudx.module.system.dao;

import com.suypower.cloudx.module.system.entity.AttachRef;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mengcheng.yu on 2015/11/24.
 */
public interface AttachRefMapper
{
    /**
     * 根据belongId查询文件关联关系
     * @param belongId
     * @return
     */
    List<AttachRef> queryAttachRef(String belongId);

    /**
     * 新增文件关联关系
     * @param attachRefList
     * @return
     */
    Integer insertAttachRef(@Param(value = "attachRefList")List<AttachRef> attachRefList);

    /**
     * 通过业务id删除文件关联关系
     * @param belongId
     * @return
     */
    Integer deleteAttachRef(String belongId);

    /**
     * 通过主键删除文件关联关系
     * @param attachRefIdList
     * @return
     */
    Integer deleteAttachRefByIds(@Param(value = "attachRefIdList")List<String> attachRefIdList);
}
