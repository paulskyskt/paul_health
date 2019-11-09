package com.paul.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.paul.dao.OrderSettingDao;
import com.paul.pojo.OrderSetting;
import com.paul.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> orderSettingList) {
        if ( orderSettingList != null && orderSettingList.size() > 0 ) {
            for (OrderSetting orderSetting : orderSettingList) {
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if ( count > 0 ) {
                    //已经存在 执行更新操作
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                }else {
                    //不存在 添加操作
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        //传入的格式  2019-11
        //返回的格式 { date: 1, number: 120, reservations: 1 },

        String begin = date+"-1";//2019-11-1
        String end = date + "-31";//2019-11-31

        //map集合用于参数的传递
        HashMap<String, String> mapParams = new HashMap<>();
        mapParams.put("begin",begin);
        mapParams.put("end",end);
        List<OrderSetting> orderSettingList = orderSettingDao.getOrderSettingByMonth(mapParams);

        //转换为前台需要的json格式数据
        List<Map> result = new ArrayList<>(); //需要的
        for (OrderSetting orderSetting : orderSettingList) {
            Map hashMap = new HashMap();

            int number = orderSetting.getNumber();
            Date orderDate = orderSetting.getOrderDate();
            int reservations = orderSetting.getReservations();

            hashMap.put("date",orderDate.getDate());//获取几号！！！！
            hashMap.put("number",number);
            hashMap.put("reservations",reservations);

            result.add(hashMap);

        }
        System.out.println(result);
        return result;
    }
}
