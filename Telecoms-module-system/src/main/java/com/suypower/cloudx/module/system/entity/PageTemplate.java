package com.suypower.cloudx.module.system.entity;

import java.util.Date;

/**
 * Created by Bingdor on 2015/12/1.
 */
public class PageTemplate {
    private String tplID;
    private Role role;
    private String path;
    private String status;
    private String scope;
    private Date createTime;
    private Integer priority;

    public String getTplID() {
        return tplID;
    }

    public void setTplID(String tplID) {
        this.tplID = tplID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
