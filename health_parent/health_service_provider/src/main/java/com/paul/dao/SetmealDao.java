package com.paul.dao;

import com.github.pagehelper.Page;
import com.paul.pojo.Setmeal;

import java.util.HashMap;
import java.util.List;

public interface SetmealDao {


    Page<Setmeal> findPage(String queryString);

    void add(Setmeal setmeal);

    void setSetmealAndCheckgroup(HashMap<String, Integer> map);

    List<Setmeal> findAll();
}



