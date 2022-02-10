package com.Long.dao;

import com.Long.entity.Promo;
import org.springframework.stereotype.Repository;

   /**
      * @autor Jack Chao
      * @version
      * @ClassName : PromoMapper
      * @date : 2022/2/8 10:26
      */

@Repository
public interface PromoMapper {

    int insert(Promo record);


    int insertSelective(Promo record);


    Promo selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(Promo record);


    int updateByPrimaryKey(Promo record);

    // 根据
    Promo selectByItemId(Integer itemId);
}