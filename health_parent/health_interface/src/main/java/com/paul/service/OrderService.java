package com.paul.service;

import com.paul.entity.Result;
import com.paul.pojo.Order;

import java.util.Map;

public interface OrderService {
    Result order(Map map) throws Exception;

    Map findById(Integer id);
}


