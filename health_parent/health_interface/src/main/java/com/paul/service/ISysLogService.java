package com.paul.service;

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
}

