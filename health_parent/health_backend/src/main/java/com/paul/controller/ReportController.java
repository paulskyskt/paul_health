package com.paul.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.constant.MessageConstant;
import com.paul.entity.Result;
import com.paul.pojo.MapData;
import com.paul.service.MemberService;
import com.paul.service.ReportService;
import com.paul.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Reference
    private MemberService memberService;
    @Reference
    private SetmealService setmealService;

    @Reference
    private ReportService reportService;

    @RequestMapping("/getMemberReport.do")
    public Result getMemberReport(){
        Map<String, Object> map = new HashMap<>();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-12);//现在时间往前12个月

        List<String> list = new ArrayList<>();
        for(int i=0;i<12;i++){
            calendar.add(Calendar.MONTH,1); //日历锁定日期往后推一个月
            list.add(new SimpleDateFormat("yyyy.MM").format(calendar.getTime()));
        }
        map.put("months",list);

        List<Integer>memberCount= memberService.findMemberCountByMonth(list);
        map.put("memberCount",memberCount);


        return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS,map);
    }


    @RequestMapping("/memberMap.do")
    public Result memberMap(){
        /*[
                        {name: '北京',value: 3},
                        {name: '天津',value:19},
                        {name: '上海',value:14},
                        {name: '重庆',value:1},
                        {name: '河北',value:2},
                        {name: '河南',value:5},
                        {name: '云南',value:10},
                        {name: '辽宁',value:9},
                        {name: '黑龙江',value:9},
                    ]*/
        //List<Map> list = memberService.findAddressAndCount();


       // ArrayList<MapData> list = new ArrayList<>();

    /*    MapData data = new MapData("北京", 3);
        MapData data1 = new MapData("重庆", 12);
        MapData data2 = new MapData("天津", 9);

        list.add(data);
        list.add(data1);
        list.add(data2);*/

        List<MapData> list = memberService.findAddressAndCount();

        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);

    }


    @RequestMapping("/getSetmealReport.do")
    public Result getSetmealReport(){
        /*
        {
            "setmealNames": ["套餐1", "套餐2", "套餐3"],
            "setmealCount": [
                  {"name": "套餐1","value": 10},
                  {"name": "套餐2","value": 30},
                  {"name": "套餐3","value": 25}
                ]
        }
        * */
        HashMap<String, Object> map = new HashMap<>();
        List<Map<String,Object>> list = setmealService.findSetmealCount();
        map.put("setmealCount",list);

        List<String> setmealNames = new ArrayList<>();

        for (Map<String, Object> objectMap : list) {
            String name = (String) objectMap.get("name");
            setmealNames.add(name);
        }

        map.put("setmealNames",setmealNames);


        return new Result(true,MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,map);
    }

    @RequestMapping("/getBusinessReportData.do")
    public Result getBusinessReportData(){
        try{
            Map<String,Object>result=reportService.getBusinessReport();
            return new Result(true,MessageConstant.GET_BUSINESS_REPORT_SUCCESS,result);
        }
        catch(Exception e){e.printStackTrace();
        return new Result(true,MessageConstant.GET_BUSINESS_REPORT_FAIL);}
    }


}
