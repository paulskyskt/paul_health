package com.paul.dao;

import com.github.pagehelper.Page;
import com.paul.pojo.CheckGroup;
import com.paul.pojo.CheckItem;

import java.util.HashMap;
import java.util.List;

public interface CheckGroupDao {

    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(HashMap<String, Integer> map);

    Page<CheckGroup> selectByCondition(String queryString);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void deleteAssociation(Integer id);

    void updateCheckItemForCheckGroup(HashMap<String, Integer> map);

    void update(CheckGroup checkGroup);

    List<CheckGroup> findAll();
}




