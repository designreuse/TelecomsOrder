package com.suypower.cloudx.module.system.dao;

import com.suypower.cloudx.module.system.entity.Department;

/**
 * Created by Bingdor on 2015/12/4.
 */
public interface DepartmentMapper {
    public Department queryDepartment(String deptID);
}
