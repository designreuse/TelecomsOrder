package com.suypower.cloudx.module.system.dto;

/**
 * Created by Bingdor on 2015/11/18.
 */
public class UserDto {
    private String userID;
    private String username;
    private String orgNo;

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

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }
}
