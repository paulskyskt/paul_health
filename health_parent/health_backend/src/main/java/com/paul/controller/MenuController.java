package com.paul.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.constant.MessageConstant;
import com.paul.entity.Result;
import com.paul.pojo.Aside;
import com.paul.service.MenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Reference
    private MenuService menuService;


    @RequestMapping("/findAside.do")
    public Result findAside(){
        try{
            //测试-------------------------------------------------------------------------------------------------------
          /* List<Aside> asideList = new ArrayList<>();

           List<Children> childrenList = new ArrayList<>();
           Children c_1 = new Children("/2-1", "会员档案", "member.html");
           Children c_2 = new Children("/2-2", "体检上传", "member.html");
           Children c_3 = new Children("/2-3", "会员统计", "doc-example_map-example.html");
           childrenList.add(c_1);
           childrenList.add(c_2);
           childrenList.add(c_3);

           Aside aside = new Aside("2", "会员管理", "fa-user-md",childrenList );
           asideList.add(aside);*/
            //-----------------------------------------------------------------------------------------------------------


            List<Aside> asideList =  menuService.findAside();

            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,asideList);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
}
