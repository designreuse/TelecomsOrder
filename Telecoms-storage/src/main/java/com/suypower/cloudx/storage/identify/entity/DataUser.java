package com.suypower.cloudx.storage.identify.entity;

import java.util.Date;

/**
 * Created by Bingdor on 2015/12/3.
 */
public class DataUser {
    private String userID;
    private String username;
    private String password;
    private DataApp dataApp;
    private Date createTime;
    private String status;
    private String readOnly;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DataApp getDataApp() {
        return dataApp;
    }

    public void setDataApp(DataApp dataApp) {
        this.dataApp = dataApp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly;
    }
}
