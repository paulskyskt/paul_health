package com.paul.service;

import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;

public interface SetmealService {

    PageResult findPage(QueryPageBean queryPageBean);

}
