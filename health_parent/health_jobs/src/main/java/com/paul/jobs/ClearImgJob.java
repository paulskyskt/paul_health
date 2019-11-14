package com.paul.jobs;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.constant.RedisConstant;
import com.paul.service.MemberService;
import com.paul.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/*@Component
@Lazy(false)*/
public class ClearImgJob {

    /*@Autowired
    private JedisPool jedisPool;



    @Scheduled(cron = "0/10 * * * * ?")
    public void clearImg(){

        System.out.println("clear");



        *//*Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);

        if(set!=null){
            for (String name : set) {
                QiniuUtils.deleteFileFromQiniu(name);
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,name);
            }
        }*//*
    }*/
}
