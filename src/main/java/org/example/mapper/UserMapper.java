package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.User;
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
