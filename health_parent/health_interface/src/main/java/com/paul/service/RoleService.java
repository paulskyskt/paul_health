package com.paul.service;

import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.pojo.Permission;

import java.util.List;

public interface RoleService {
    PageResult findPage(QueryPageBean queryPageBean);

    List<Permission> findPermissionByRoleId(Integer id);
}


