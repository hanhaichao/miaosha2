package com.Long.dao;


import com.Long.entity.Item;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMapper {

    int insert(Item record);


    int insertSelective(Item record);


    Item selectByPrimaryKey(Integer id);
    
    // 查询商品列表
    List<Item> listItem();


    int updateByPrimaryKeySelective(Item record);


    int updateByPrimaryKey(Item record);

    // 增加销量
    int increaseSales(@Param("id") Integer id, @Param("amount") Integer amount);
}