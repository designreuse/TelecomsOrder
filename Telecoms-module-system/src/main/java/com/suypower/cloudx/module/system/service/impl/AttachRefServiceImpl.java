package com.suypower.cloudx.module.system.service.impl;

import com.suypower.cloudx.module.system.dao.AttachRefMapper;
import com.suypower.cloudx.module.system.entity.AttachRef;
import com.suypower.cloudx.module.system.service.IAttachRefService;
import com.suypower.cloudx.storage.client.AppSupportClient;
import com.suypower.cloudx.storage.core.entity.UserOption;
import com.suypower.cloudx.storage.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mengcheng.yu on 2015/12/8.
 */
@Service
public class AttachRefServiceImpl implements IAttachRefService
{
    @Autowired
    private AttachRefMapper attachRefMapper;

    @Autowired
    private AppSupportClient appSupportClient;

    /**
     * 新增文件
     * @param attachRefList
     * @return
     */
    @Override
    public List<AttachRef> insertAttachRef(List<AttachRef> attachRefList)
    {
        attachRefMapper.insertAttachRef(attachRefList);
        return attachRefList;
    }

    /**
     * 删除文件
     * @param belongId
     * @return
     */
    @Override
    public boolean deleteAttachRef(String belongId)
    {
        return 0 != attachRefMapper.deleteAttachRef(belongId);
    }

    /**
     * 通过主键删除文件关联关系
     * @param attachRefIdList attachIdList
     * @return
     */
    @Override
    public boolean deleteAttachRefByIds(List<String> attachRefIdList, List<String> attachIdList)
        throws DataException
    {
//        UserOption userOption = new UserOption("Cloudx","cloudx","123456");
//        appSupportClient.deleteDataFiles(userOption, attachIdList);
//        for (int i=0; i<attachIdList.size(); i++)
//        {
//            appSupportClient.deleteDataFile(userOption, attachIdList.get(i));
//        }
        return  0 != attachRefMapper.deleteAttachRefByIds(attachRefIdList);
    }
}
