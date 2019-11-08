package com.paul.dao;

import com.github.pagehelper.Page;
import com.paul.pojo.Setmeal;

public interface SetmealDao {


    Page<Setmeal> findPage(String queryString);
}

