package com.paul.service;

import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {

     List<CheckItem> findAll();

    public void add(CheckItem checkItem);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public void delete(Integer id);


    CheckItem findById(Integer id);

    void edit(CheckItem checkItem);

}
