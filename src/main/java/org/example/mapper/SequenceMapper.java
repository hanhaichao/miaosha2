package org.example.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Sequence;
import org.springframework.stereotype.Repository;


@Repository
public interface SequenceMapper {

    Sequence getSequenceByName(String name);

    int insert(Sequence record);

    int insertSelective(Sequence record);

    Sequence selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(Sequence record);

    int updateByPrimaryKey(Sequence record);
}