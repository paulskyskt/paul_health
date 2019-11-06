package com.paul.dao;

import com.github.pagehelper.Page;
import com.paul.pojo.CheckItem;

public interface CheckItemDao {

    /**
     * 添加检查项
     * @param checkItem
     */
    public void add(CheckItem checkItem);

    /**
     * 分页/条件 查询检查项
     * @param queryString
     * @return
     */
    public Page<CheckItem> selectByCondition(String queryString);

    public void deleteById(Integer id);

    public long findCountByCheckItemId(Integer checkItemId);

    CheckItem findById(Integer id);

    void update(CheckItem checkItem);

}
