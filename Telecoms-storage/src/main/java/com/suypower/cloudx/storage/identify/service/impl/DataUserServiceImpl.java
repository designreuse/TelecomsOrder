package com.suypower.cloudx.storage.identify.service.impl;

import com.suypower.cloudx.storage.exception.DataException;
import com.suypower.cloudx.storage.identify.dao.DataUserMapper;
import com.suypower.cloudx.storage.identify.entity.DataUser;
import com.suypower.cloudx.storage.identify.service.IDataUserService;
import com.suypower.cloudx.support.encrypt.DesEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Bingdor on 2015/12/3.
 */
@Service
public class DataUserServiceImpl implements IDataUserService {
    @Autowired
    private DataUserMapper dataUserMapper;
    public DataUser validateUser(String appKey,String username, String password) throws DataException {
        DataUser dataUser= dataUserMapper.queryDataUser(username);
        if(!dataUser.getDataApp().getAppKey().equals(appKey)){
            throw new RuntimeException("应用代码不存在");
        }
        if("02".equals(dataUser.getStatus())){
            throw new RuntimeException("用户不存在");
        }
        if(!dataUser.getPassword().equals(DesEncrypter.encrypt(password))){
            throw new RuntimeException("用户名或密码错误");
        }
        return dataUser;
    }
}
