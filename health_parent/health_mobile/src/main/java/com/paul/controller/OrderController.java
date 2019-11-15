package com.paul.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.constant.MessageConstant;
import com.paul.constant.RedisMessageConstant;
import com.paul.entity.Result;
import com.paul.pojo.Order;
import com.paul.service.OrderService;
import com.paul.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private OrderService orderService;

    @RequestMapping("/submit.do")
    public Result submit(@RequestBody Map map) {
        //redis 获取验证吗
        //判断验证码正确？
        //true调业务层
        //false返回

        //1
        //获取传入手机号
        String telephone = (String) map.get("telephone");
        //从缓存中获取对应验证码
        //String sys_code = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        //获取传入验证码
        String code = (String) map.get("validateCode");

        //测试用
        String sys_code="0000";
        //测试用

        //判断
        if ( sys_code == null || !sys_code.equals(code) ) {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        //调业务层
        Result result = null;
        try{
            //添加  微信预约
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            result = orderService.order(map);
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        //
        if(result.isFlag()){
            //短信通知预约成功信息
            String orderDate = (String) map.get("orderDate");
            //发送
           /* try{
                SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE,telephone,orderDate);
            }catch (Exception e){
                e.printStackTrace();
            }*/
        }
        return result;
    }


    @RequestMapping("/findById.do")
    public Result findById(Integer id){
        try{
            Map map = orderService.findById(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
}
