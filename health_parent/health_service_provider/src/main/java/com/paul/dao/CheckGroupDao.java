package com.paul.dao;

import com.paul.pojo.CheckGroup;

import java.util.HashMap;

public interface CheckGroupDao {

    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(HashMap<String, Integer> map);
}

