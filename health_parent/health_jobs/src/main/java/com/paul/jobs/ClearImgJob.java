package com.paul.jobs;

import com.paul.constant.RedisConstant;
import com.paul.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){

        System.out.println("clear");

        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);

        if(set!=null){
            for (String name : set) {
                QiniuUtils.deleteFileFromQiniu(name);
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,name);
            }
        }
    }
}
