package com.Long.service;

   /**
      * @autor Jack Chao
      * @version
      * @ClassName : mqService
      * @date : 2022/2/9 20:55
      * @description: mq模块接口
      */

public interface mqService {
    //事务型同步库存扣减消息
    boolean transactionAsyncReduceStock(Integer userId, Integer itemId, Integer promoId, Integer amount, String stockLogId);

    boolean asyncReduceStock(Integer itemId, Integer amount);
}
