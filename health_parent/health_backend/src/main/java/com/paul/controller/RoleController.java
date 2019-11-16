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

    @RequestMapping("/findAside.do")
    public Result findAside(){
        /*
        * [
                {
                    "path": "1",
                    "title": "工作台",
                    "icon":"fa-dashboard",
                    "children": []
                },
                {
                    "path": "2",
                    "title": "会员管理",
                    "icon":"fa-user-md",
                    "children": [
                        {
                            "path": "/2-1",
                            "title": "会员档案",
                            "linkUrl":"member.html",
                            "children":[]
                        },
                        {
                            "path": "/2-2",
                            "title": "体检上传",
                            "children":[]
                        },
                        {
                            "path": "/2-3",
                            "title": "会员统计",
                            "linkUrl":"all-item-list.html",
                            "children":[]
                        },
                    ]
                }
            ]*/
       try{
           //roleService.findAside();
           return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS);
       }catch (Exception e){
           e.printStackTrace();
           return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
       }
    }
}
