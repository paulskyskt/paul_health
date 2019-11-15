package com.paul.dao;

import com.github.pagehelper.Page;
import com.paul.pojo.Setmeal;
import com.paul.pojo.SysLog;

public interface SysLogDao {
    void save(SysLog sysLog);

    Page<Setmeal> findPage(String queryString);
}



