package org.example.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.example.entity.UserPassword;
import org.springframework.stereotype.Repository;


@Repository
public interface UserPasswordMapper {

    int insert(UserPassword record);

    int insertSelective(UserPassword record);

    UserPassword selectByPrimaryKey(Integer id);

    UserPassword selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(UserPassword record);

    int updateByPrimaryKey(UserPassword record);
}