package com.paul.dao;

import com.paul.pojo.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> findByUserId(Integer id);
}
