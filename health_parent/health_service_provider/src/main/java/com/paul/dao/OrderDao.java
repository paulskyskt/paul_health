package com.paul.dao;

import com.paul.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    List<Order> findByCondition(Order order);

    void add(Order order);


    Map findById4Detai(Integer id);

    Integer findOrderCountByDate(String today);

    Integer findOrderCountAfterDate(String thisWeekMonday);

    Integer findVisitsCountByDate(String today);

    Integer findVisitsCountAfterDate(String thisWeekMonday);

    List<Map> findHotSetmeal();
}








