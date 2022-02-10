package com.Long.dao;


import com.Long.entity.ItemStock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemStockMapper {

    int insert(ItemStock record);


    int insertSelective(ItemStock record);


    ItemStock selectByPrimaryKey(Integer id);

    // 根据id获取商品库存
    ItemStock selectByItemId(Integer itemId);

    // 减库存
    int decreaseStock(@Param("itemId") Integer itemId, @Param("amount") Integer amount);


    int updateByPrimaryKeySelective(ItemStock record);

}