/**    
 * 文件名：Organization.java    
 *    
 * 版本信息：    
 * 日期：2015年5月6日    
 * Copyright Bingdor Corporation 2015     
 * 版权所有    
 *    
 */
package com.suypower.cloudx.module.system.entity;

public class Organization {
    
    private String orgNo;
    private String name;
    private String address;
    private Integer order;
    private Organization superOrgNo;

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Organization getSuperOrgNo() {
        return superOrgNo;
    }

    public void setSuperOrgNo(Organization superOrgNo) {
        this.superOrgNo = superOrgNo;
    }
}
