package com.suypower.cloudx.web.entity;

import com.suypower.cloudx.module.system.entity.Organization;

/**
 * Created by Zhengtao on 2015/12/29.
 * 用户信息
 */
public class Consumer {

    private String consId;
    private String consNo;
    private String consName;
    private String consAddr;
    private String consSortCode;
    private String consSortName;
    private Organization org;

    public String getConsId() {
        return consId;
    }

    public void setConsId(String consId) {
        this.consId = consId;
    }

    public String getConsNo() {
        return consNo;
    }

    public void setConsNo(String consNo) {
        this.consNo = consNo;
    }

    public String getConsName() {
        return consName;
    }

    public void setConsName(String consName) {
        this.consName = consName;
    }

    public String getConsAddr() {
        return consAddr;
    }

    public void setConsAddr(String consAddr) {
        this.consAddr = consAddr;
    }

    public String getConsSortCode() {
        return consSortCode;
    }

    public void setConsSortCode(String consSortCode) {
        this.consSortCode = consSortCode;
    }

    public String getConsSortName() {
        return consSortName;
    }

    public void setConsSortName(String consSortName) {
        this.consSortName = consSortName;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "consId='" + consId + '\'' +
                ", consName='" + consName + '\'' +
                ", consAddr='" + consAddr + '\'' +
                ", consSortCode='" + consSortCode + '\'' +
                ", consSortName='" + consSortName + '\'' +
                ", org=" + org +
                '}';
    }

}
