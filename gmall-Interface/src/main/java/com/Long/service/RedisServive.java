package com.Long.service;

import java.util.concurrent.TimeUnit;

   /**
      * @autor Jack Chao
      * @version
      * @ClassName : RedisServive
      * @date : 2022/2/9 20:56
      * @description: 获取redis操作对象
      */

public interface RedisServive {
    void setKey(Object k, Object v);
    Object getKey(Object k);
    void expire(Object k, long v, TimeUnit unit);
    Long increment(Object k, long v);
    boolean hasKey(Object k);
    void delete(Object k);
    void deleteCash(String key);
    // 存cache
    void setCommonCache(String key, Object value);
    // 取cache
    Object getFromCommonCache(String key);
}
