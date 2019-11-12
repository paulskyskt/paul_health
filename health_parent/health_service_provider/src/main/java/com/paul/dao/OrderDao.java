package com.paul.dao;

import com.paul.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    List<Order> findByCondition(Order order);

    void add(Order order);


    Map findById4Detai(Integer id);
}



