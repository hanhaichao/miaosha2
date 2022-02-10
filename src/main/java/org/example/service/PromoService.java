package org.example.service;

import org.example.service.model.PromoModel;


public interface PromoService {
    /**
     * @param itemId 商品id
     * @return PromoModel 秒杀模型
     * @description 通过商品id查询秒杀信息
     */
    PromoModel getPromoByItemId(Integer itemId);
}
