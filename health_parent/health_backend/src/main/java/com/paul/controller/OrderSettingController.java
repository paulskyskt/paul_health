package com.paul.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.entity.Result;
import com.paul.service.OrderSettingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/upload.do")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile){

        return null;
    }
}
