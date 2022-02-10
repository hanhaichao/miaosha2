package org.example.mapper;



import org.example.entity.Promo;
import org.springframework.stereotype.Repository;


@Repository
public interface PromoMapper {

    int insert(Promo record);

    int insertSelective(Promo record);

    Promo selectByPrimaryKey(Integer id);

    Promo selectByItemId(Integer itemId);

    int updateByPrimaryKeySelective(Promo record);

    int updateByPrimaryKey(Promo record);
}