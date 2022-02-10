package com.Long.dao;

import com.Long.entity.Sequence;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceMapper {

    int insert(Sequence record);


    int insertSelective(Sequence record);

    Sequence getSequenceByName(String name);

    void updateByPrimaryKeySelective(Sequence sequence);
}