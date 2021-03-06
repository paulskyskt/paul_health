package com.paul.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.constant.MessageConstant;
import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.entity.Result;
import com.paul.pojo.CheckItem;
import com.paul.service.CheckItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

    //查询检查项
    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){

       return checkItemService.pageQuery(queryPageBean);
    }

    //删除检查项
    @RequestMapping("/delete.do")
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")//权限校验
    public Result delete(Integer id){
        try{
            checkItemService.delete(id);
        }catch (RuntimeException e){
            return new Result(false,e.getMessage());
        }catch (Exception e){
            return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    //查询一个检查项
    @RequestMapping("/findById.do")
    public Result findById(Integer id){
        try{
            CheckItem checkItem = checkItemService.findById(id);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
        }catch (RuntimeException e){
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }


    //编辑检查项
    @RequestMapping("/edit.do")
    public Result edit(@RequestBody CheckItem checkItem){
        try{
            checkItemService.edit(checkItem);
            return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
        }catch (Exception e){
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }


    //findAll.do
    @RequestMapping("/findAll.do")
    public Result findAll(){
        try{
           List<CheckItem> checkItemList = checkItemService.findAll();
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemList);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }


}
