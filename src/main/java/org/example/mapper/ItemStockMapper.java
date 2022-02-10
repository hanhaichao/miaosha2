package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.entity.ItemStock;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemStockMapper {
    int insert(ItemStock record);

    int insertSelective(ItemStock record);

    ItemStock selectByPrimaryKey(Integer id);

    ItemStock selectByItemId(Integer itemId);

    int updateByPrimaryKeySelective(ItemStock record);

    int updateByPrimaryKey(ItemStock record);

    int decreaseStock(@Param("itemId") Integer itemId, @Param("amount") Integer amount);
}