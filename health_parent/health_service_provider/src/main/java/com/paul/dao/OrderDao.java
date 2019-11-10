package com.paul.dao;

import com.paul.pojo.Order;

import java.util.List;

public interface OrderDao {
    List<Order> findByCondition(Order order);

    void add(Order order);


}


