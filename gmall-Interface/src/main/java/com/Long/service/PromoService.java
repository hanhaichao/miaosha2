package com.Long.service;


import com.Long.Error.BusinessException;
import com.Long.Model.PromoModel;
   /**
      * @autor Jack Chao
      * @version
      * @ClassName : PromoService
      * @date : 2022/2/9 20:55
      *@description: 秒杀活动业务逻辑接口
      */

public interface PromoService {
    /**
     * @description 通过商品id查询秒杀信息
     */
    PromoModel getPromoByItemId(Integer itemId);
    // 活动发布
    void publishPromo(Integer promoId) throws BusinessException;

    // 生成秒杀用的令牌
    String generateSecondKillToken(Integer promoId, Integer itemId, Integer userId) throws BusinessException;
}