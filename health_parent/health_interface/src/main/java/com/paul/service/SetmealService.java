package com.paul.service;

import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.pojo.Setmeal;

public interface SetmealService {

    PageResult findPage(QueryPageBean queryPageBean);

    void add(Setmeal setmeal, Integer[] checkgroupIds);
}

