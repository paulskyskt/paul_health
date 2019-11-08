package com.paul.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.constant.MessageConstant;
import com.paul.constant.RedisConstant;
import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.entity.Result;
import com.paul.pojo.Setmeal;
import com.paul.service.SetmealService;
import com.paul.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private SetmealService setmealService;

    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return setmealService.findPage(queryPageBean);
    }

    @RequestMapping("/upload.do")
    public Result upload(@RequestParam("imgFile")MultipartFile multipartFile){
        try{
            //获取原始文件名
            String originalFilename = multipartFile.getOriginalFilename();
            int lastIndexOf = originalFilename.lastIndexOf(".");
            //获取格式名
            String suffix = originalFilename.substring(lastIndexOf);
            //拼接文件名
            String fileName = UUID.randomUUID().toString() + suffix;
            QiniuUtils.upload2Qiniu(multipartFile.getBytes(),fileName);
            //存入大集合
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);

            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(true, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    @RequestMapping("/add.do")
    public Result add(@RequestBody Setmeal setmeal,Integer[] checkgroupIds){
        try{
            setmealService.add(setmeal,checkgroupIds);
            return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
    }
}
