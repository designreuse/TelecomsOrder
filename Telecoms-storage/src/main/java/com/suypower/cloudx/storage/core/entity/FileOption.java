package com.suypower.cloudx.storage.core.entity;

import java.io.InputStream;

/**
 * Created by Bingdor on 2015/12/3.
 */
public class FileOption {
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件类型(可选)
     */
    private String fileType;
    /**
     * 文件分组代码（不支持中文）
     */
    private String dataGroup;
    /**
     * 文件流
     */
    private InputStream fileStream;
    /**
     * 文件字节数组
     */
    private byte[] fileBytes;
    /**
     * 是否为临时文件（默认：否）
     */
    private Boolean tempFlag = false;

    public FileOption() {
    }

    public FileOption(String fileName, String fileType, InputStream fileStream) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileStream = fileStream;
    }

    public FileOption(String fileName, String fileType, byte[] fileBytes) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileBytes = fileBytes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public InputStream getFileStream() {
        return fileStream;
    }

    public void setFileStream(InputStream fileStream) {
        this.fileStream = fileStream;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public String getDataGroup() {
        return dataGroup;
    }

    public void setDataGroup(String dataGroup) {
        this.dataGroup = dataGroup;
    }

    public Boolean isTemp() {
        return tempFlag;
    }

    public void setTemp(Boolean temp) {
        tempFlag = temp;
    }
}
