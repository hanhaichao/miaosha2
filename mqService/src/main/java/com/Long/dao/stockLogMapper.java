package com.Long.dao;


import com.Long.entity.stockLog;
import org.springframework.stereotype.Repository;
   /**
      * @autor Jack Chao
      * @version
      * @ClassName : stockLogMapper
      * @date : 2022/2/8 10:25
      */

@Repository
public interface stockLogMapper {

    int insert(stockLog record);


    int insertSelective(stockLog record);


    stockLog selectByPrimaryKey(String stockLogId);


    int updateByPrimaryKeySelective(stockLog record);


    int updateByPrimaryKey(stockLog record);
}