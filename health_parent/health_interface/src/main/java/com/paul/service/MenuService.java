package com.paul.service;

import com.paul.pojo.Aside;
import com.paul.pojo.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> findAside(String username);
}

