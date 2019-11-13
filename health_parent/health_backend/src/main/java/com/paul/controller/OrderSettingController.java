package com.paul.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.constant.MessageConstant;
import com.paul.entity.Result;
import com.paul.pojo.OrderSetting;
import com.paul.service.OrderSettingService;
import com.paul.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/upload.do")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile){

        try{
            List<String[]> excel = POIUtils.readExcel(excelFile);
            if(excel !=null && excel.size() > 0){
                //转成List<OrderSetting>格式
                List<OrderSetting> orderSettingList = new ArrayList<>();
                for (String[] strings : excel) {
                    //获取日期
                    String date = strings[0];
                    //获取预约人数
                    String number = strings[1];

                    OrderSetting orderSetting = new OrderSetting(new Date(date),Integer.parseInt(number));
                    orderSettingList.add(orderSetting);
                }
                //调用service存入数据库
                orderSettingService.add(orderSettingList);
            }
            return new Result(true,MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }

    }


    @RequestMapping("/getOrderSettingByMonth.do")
    public Result getOrderSettingByMonth (String date){
        try{
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
            return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/editNumberByDate.do")
    public Result editNumberByDate (@RequestBody OrderSetting orderSetting){
        try{
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
    }
}
