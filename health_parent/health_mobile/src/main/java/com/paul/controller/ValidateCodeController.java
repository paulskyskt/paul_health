package com.paul.controller;

import com.paul.constant.MessageConstant;
import com.paul.constant.RedisMessageConstant;
import com.paul.entity.Result;
import com.paul.utils.SMSUtils;
import com.paul.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/send4Order.do")
    public Result send4Order(String telephone){
        //生成4位验证码
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        try{
            //发送短信
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //存入redis
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_ORDER,5*60,code.toString());
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

    @RequestMapping("/send4Login.do")
    public Result send4Login(String telephone){
        //生成6位验证码
        Integer code = ValidateCodeUtils.generateValidateCode(6);
        try{
            //发送短信
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //存入redis
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_LOGIN,5*60,code.toString());
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

}
