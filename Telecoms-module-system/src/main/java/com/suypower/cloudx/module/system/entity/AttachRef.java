package com.suypower.cloudx.module.system.entity;

import com.suypower.cloudx.storage.core.entity.DataFile;

/**
 * Created by mengcheng.yu on 2015/12/2.
 */
public class AttachRef
{
    private String attachRefId;
    private String attachId;
    private String belongId;
    private String url;
    private DataFile dataFile;

    public String getAttachRefId() {
        return attachRefId;
    }

    public void setAttachRefId(String attachRefId) {
        this.attachRefId = attachRefId;
    }

    public String getAttachId() {
        return attachId;
    }

    public void setAttachId(String attachId) {
        this.attachId = attachId;
    }

    public String getBelongId() {
        return belongId;
    }

    public void setBelongId(String belongId) {
        this.belongId = belongId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DataFile getDataFile() {
        return dataFile;
    }

    public void setDataFile(DataFile dataFile) {
        this.dataFile = dataFile;
    }

    @Override
    public String toString() {
        return "AttachRef{" +
                "attachRefId='" + attachRefId + '\'' +
                ", attachId='" + attachId + '\'' +
                ", belongId='" + belongId + '\'' +
                ", url='" + url + '\'' +
                ", dataFile=" + dataFile +
                '}';
    }
}
