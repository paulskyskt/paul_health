package com.paul.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.constant.MessageConstant;
import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.entity.Result;
import com.paul.service.ISysLogService;
import com.paul.utils.DateUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/sysLog")
public class SysLogController {


    @Reference
    private ISysLogService sysLogService;

    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return sysLogService.findPage(queryPageBean);
    }

    @RequestMapping("/delete.do")
    public Result delete(Integer id){
        try{
            sysLogService.deleteById(id);
            return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/deleteByTime.do")
    public Result deleteByTime(String date) throws Exception {
        Date date1 = DateUtils.parseString2Date(date);
        try{
            sysLogService.deleteByTime(date1);
            return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }

    }
}
