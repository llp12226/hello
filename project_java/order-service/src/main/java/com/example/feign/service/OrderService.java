package com.example.feign.service;


import com.example.feign.mapper.OrderMapper;
import com.example.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderMapper mapper;

    public Order findOrderById(Long orderId){
        return mapper.findById(orderId);
    }
}

