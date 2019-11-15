package com.paul.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.service.ISysLogService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysLog")
public class SysLogController {


    @Reference
    private ISysLogService sysLogService;

    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return sysLogService.findPage(queryPageBean);
    }
}
