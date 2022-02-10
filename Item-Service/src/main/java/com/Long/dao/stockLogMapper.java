package com.Long.dao;


import com.Long.entity.stockLog;
import org.springframework.stereotype.Repository;

@Repository
public interface stockLogMapper {

    int insert(stockLog record);


    int insertSelective(stockLog record);


    stockLog selectByPrimaryKey(String stockLogId);


    int updateByPrimaryKeySelective(stockLog record);

    int updateByPrimaryKey(stockLog record);
}