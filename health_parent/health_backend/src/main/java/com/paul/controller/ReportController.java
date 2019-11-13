package com.paul.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.constant.MessageConstant;
import com.paul.entity.Result;
import com.paul.service.MemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Reference
    private MemberService memberService;

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
}
