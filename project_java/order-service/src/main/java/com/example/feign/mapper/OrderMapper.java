package com.example.feign.mapper;


import com.example.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM tb_order where id = #{id}")
    Order findById(@Param("id") Long orderId);

}
