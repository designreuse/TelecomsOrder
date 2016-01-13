package com.suypower.cloudx.storage.core.entity;

import com.suypower.cloudx.storage.identify.entity.DataUser;

import java.util.Date;

/**
 * Created by Bingdor on 2015/12/3.
 */
public class DataToken {
    private String tokenID;
    private String token;
    private DataUser dataUser;
    private Date createTime;
    private Date useTime;
    private DataFile dataFile;

    public String getTokenID() {
        return tokenID;
    }

    public void setTokenID(String tokenID) {
        this.tokenID = tokenID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DataUser getDataUser() {
        return dataUser;
    }

    public void setDataUser(DataUser dataUser) {
        this.dataUser = dataUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public DataFile getDataFile() {
        return dataFile;
    }

    public void setDataFile(DataFile dataFile) {
        this.dataFile = dataFile;
    }
}
