package com.paul.service;

import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.pojo.SysLog;

import java.util.Date;

/**
 * @author Think
 */
public interface ISysLogService {

    /**
     * 保存切面类封装信息
     * @param sysLog
     */
    void save(SysLog sysLog);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 根据id删除日志记录
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据时间删除日志记录
     * @param date1
     */
    void deleteByTime(Date date1);
}



