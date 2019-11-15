package com.paul.dao;

import com.github.pagehelper.Page;
import com.paul.pojo.Setmeal;
import com.paul.pojo.SysLog;

import java.util.Date;

public interface SysLogDao {
    void save(SysLog sysLog);

    Page<Setmeal> findPage(String queryString);

    void deleteById(Integer id);

    void deleteByTime(Date date1);
}





