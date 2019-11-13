package com.paul.dao;

import com.paul.pojo.User;

public interface UserDao {
    User findByUserName(String username);
}

