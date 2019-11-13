package com.paul.dao;

import com.paul.pojo.User;

import java.util.List;

public interface UserDao {
    User findByUserName(String username);

    List<User> findUsersByRoleId(Integer id);
}

