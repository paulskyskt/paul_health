package com.paul.dao;

import com.github.pagehelper.Page;
import com.paul.pojo.Permission;
import com.paul.pojo.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    Set<Role> findByUserId(Integer id);

    Page<Role> findPage(String queryString);

    Permission findPermissionById(Integer id);

    List<Integer> find_M_permission_role(Integer id);
}



