package com.example.feign.pojo;


import lombok.Data;



@Data
public class Order {

    private Long orderId;
    private String name;
    private String price;

}
