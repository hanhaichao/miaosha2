package com.Long.dao;


import com.Long.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    int insert(Order record);


    int insertSelective(Order record);


    Order selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(Order record);


    int updateByPrimaryKey(Order record);
}