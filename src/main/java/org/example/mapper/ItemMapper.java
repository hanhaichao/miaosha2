package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Item;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ItemMapper {

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Integer id);

    List<Item> listItem();

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    int increaseSales(@Param("id") Integer id, @Param("amount")Integer amount);

}