package com.paul.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.constant.MessageConstant;
import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.entity.Result;
import com.paul.pojo.Permission;
import com.paul.service.RoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Reference
    private RoleService roleService;
    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return roleService.findPage(queryPageBean);
    }


    @RequestMapping("/findPermission")
    public Result findPermission(Integer id){
        List<Permission> list = roleService.findPermissionByRoleId(id);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
    }
}
