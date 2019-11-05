package com.paul.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.constant.MessageConstant;
import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.entity.Result;
import com.paul.pojo.CheckItem;
import com.paul.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


//@ResponseBody+Controller
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;


    //新增检查项
    @RequestMapping("/add.do")
    public Result add(@RequestBody CheckItem checkItem){

        try{
            checkItemService.add(checkItem);
        }catch (Exception e){
            //服务调用失败
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        //调用成功
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    //新增检查项
    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){

       return checkItemService.pageQuery(queryPageBean);
    }
}
