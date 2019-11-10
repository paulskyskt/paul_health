package com.paul.dao;

import com.paul.pojo.OrderSetting;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface OrderSettingDao {
    long findCountByOrderDate(Date orderDate);

    void editNumberByOrderDate(OrderSetting orderSetting);

    void add(OrderSetting orderSetting);

    List<OrderSetting> getOrderSettingByMonth(HashMap<String, String> mapParams);

    OrderSetting findByOrderDate(Date date);

    void editReservationsByOrderDate(OrderSetting orderSetting);
}





