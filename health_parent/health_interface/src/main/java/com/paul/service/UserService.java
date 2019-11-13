package com.paul.service;

import com.paul.pojo.User;

public interface UserService {

    User findByUserName(String username);
}

