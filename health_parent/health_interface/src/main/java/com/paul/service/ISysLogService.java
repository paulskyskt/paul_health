package com.paul.service;

import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.pojo.SysLog;

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
}


