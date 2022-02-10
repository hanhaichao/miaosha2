package com.Long.dao;


import com.Long.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int insert(User record);


    int insertSelective(User record);


    User selectByPrimaryKey(Integer id);


    User selectByTelphone(String telphone);


    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}