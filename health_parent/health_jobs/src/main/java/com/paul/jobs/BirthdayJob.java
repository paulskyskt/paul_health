package com.paul.jobs;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.pojo.Member;
import com.paul.service.MemberService;
import com.paul.utils.SMSUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@Lazy(false)
public class BirthdayJob {

    @Reference
    private MemberService memberService;

    @Scheduled(cron = "0/10 * * * * ?")
    public void test() throws Exception {
        List<Member> memberList = memberService.findAll();

        if(memberList == null){
            return;
        }

        for (Member member : memberList) {
            //排除测试数据
            if(member.getName() == null){
                continue;
            }

            Calendar cal = Calendar.getInstance();

            cal.setTime(new Date());

            //获取当前 年 月 日
            int year=cal.get(Calendar.YEAR);
            int day=cal.get(Calendar.DATE);
            int month = cal.get(Calendar.MONTH)+1;

            //获取用户 生日
            Date birthday = member.getBirthday();

            if(birthday == null){
                continue;
            }
            int day_m = birthday.getDate();
            int month_m = birthday.getMonth()+1;
            int year_m = birthday.getYear();

            if(month_m == month){
                if(day_m == day){
                    String name = member.getName();
                    String message = "happy birthday to "+name;
                    System.out.println(message);
                    //SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,member.getPhoneNumber(),message);
                }
            }
        }

    }



}
