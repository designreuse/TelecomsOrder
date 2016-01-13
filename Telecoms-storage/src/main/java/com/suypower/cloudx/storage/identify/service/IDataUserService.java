package com.suypower.cloudx.storage.identify.service;

import com.suypower.cloudx.storage.exception.DataException;
import com.suypower.cloudx.storage.identify.entity.DataUser;

/**
 * Created by Bingdor on 2015/12/3.
 */
public interface IDataUserService {
    public DataUser validateUser(String appKey,String username,String password) throws DataException;
}
