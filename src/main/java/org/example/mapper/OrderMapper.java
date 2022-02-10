package org.example.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Order;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderMapper {
    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}