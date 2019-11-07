package com.paul.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.constant.MessageConstant;
import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.entity.Result;
import com.paul.pojo.CheckGroup;
import com.paul.service.CheckGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    //添加检查组
    @RequestMapping("/add.do")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){

        try{
            checkGroupService.add(checkGroup,checkitemIds);
            return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }

    }

    //分页查询检查组

    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
         return checkGroupService.findPage(queryPageBean);
    }


}
