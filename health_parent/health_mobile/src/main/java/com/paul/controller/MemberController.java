package com.paul.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.paul.constant.MessageConstant;
import com.paul.constant.RedisMessageConstant;
import com.paul.entity.Result;
import com.paul.pojo.Member;
import com.paul.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @author Think
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Reference
    private MemberService memberService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/login.do")
    public Result login(HttpServletResponse response, @RequestBody Map map){
        //获取传入参数
        String telephone = (String) map.get("telephone");
        String validateCode = (String) map.get("validateCode");
        //从Redis中获取缓存的验证码
        //String code = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        String code = "000000";
        if(code==null || !code.equals(validateCode)){
            //验证码错误
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        //判断是不是会员
        Member member = memberService.findByTelephone(telephone);
        if(member == null){
            //添加为会员
            Member m = new Member();
            m.setPhoneNumber(telephone);
            m.setRegTime(new Date());
            memberService.add(m);
        }

        //写入Cookie，跟踪用户
        Cookie cookie = new Cookie("login_member_telephone",telephone);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24*30);
        response.addCookie(cookie);

        //存入redis
        String json = JSON.toJSON(member).toString();
        jedisPool.getResource().setex(telephone,60*30,json);
        return new Result(true,MessageConstant.LOGIN_SUCCESS);
    }
}
