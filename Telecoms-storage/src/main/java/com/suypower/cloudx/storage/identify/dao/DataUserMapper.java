package com.suypower.cloudx.storage.identify.dao;

import com.suypower.cloudx.storage.identify.entity.DataUser;

/**
 * Created by Bingdor on 2015/12/3.
 */
public interface DataUserMapper {
    public DataUser queryDataUser(String username);

    public DataUser queryDataUserByID(String userID);
}
