package com.paul.service;

import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.pojo.CheckItem;

public interface CheckItemService {

    public void add(CheckItem checkItem);

    public PageResult pageQuery(QueryPageBean queryPageBean);
}
