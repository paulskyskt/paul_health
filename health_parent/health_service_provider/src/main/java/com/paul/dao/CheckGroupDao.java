package com.paul.dao;

import com.github.pagehelper.Page;
import com.paul.pojo.CheckGroup;

import java.util.HashMap;

public interface CheckGroupDao {

    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(HashMap<String, Integer> map);

    Page<CheckGroup> selectByCondition(String queryString);
}


