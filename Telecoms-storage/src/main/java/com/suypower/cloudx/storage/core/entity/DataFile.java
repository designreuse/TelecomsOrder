package com.suypower.cloudx.storage.core.entity;

import com.suypower.cloudx.storage.identify.entity.DataApp;
import com.suypower.cloudx.storage.identify.entity.DataUser;

import java.util.Date;

/**
 * Created by Bingdor on 2015/12/3.
 */
public class DataFile {
    private String dataID;
    private String fileName;
    private String path;
    private String fileType;
    private String shaCode;
    private Date uploadTime;
    private Long fileSize;
    private DataApp dataApp;
    private DataUser uploadUser;
    private String status;
    private String dataGroup;
    private String contentType;

    public String getDataID() {
        return dataID;
    }

    public void setDataID(String dataID) {
        this.dataID = dataID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getShaCode() {
        return shaCode;
    }

    public void setShaCode(String shaCode) {
        this.shaCode = shaCode;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public DataApp getDataApp() {
        return dataApp;
    }

    public void setDataApp(DataApp dataApp) {
        this.dataApp = dataApp;
    }

    public DataUser getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(DataUser uploadUser) {
        this.uploadUser = uploadUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataGroup() {
        return dataGroup;
    }

    public void setDataGroup(String dataGroup) {
        this.dataGroup = dataGroup;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
