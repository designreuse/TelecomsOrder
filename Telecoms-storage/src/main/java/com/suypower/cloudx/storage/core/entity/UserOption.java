package com.suypower.cloudx.storage.core.entity;

/**
 * Created by Bingdor on 2015/12/3.
 */
public class UserOption {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 应用编码
     */
    private String appKey;

    public UserOption() {
    }

    public UserOption(String appKey,String username, String password) {
        this.appKey = appKey;
        this.username = username;
        this.password = password;
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

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
