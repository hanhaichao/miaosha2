package org.example.service;


import org.example.controller.error.BusinessException;
import org.example.service.model.ItemModel;

import java.util.List;


public interface ItemService {

    /**
     * @param itemModel:商品模型
     * @return ItemModel:商品模型
     * @description 创建商品
     */
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    /**
     * @param
     * @return List<ItemModel>:商品List
     * @description 列出商品
     */
    List<ItemModel> listItem();

    /**
     * @param id：商品id
     * @return ItemModel
     * @description 通过id获取商品详情
     */
    ItemModel getItemById(Integer id);

    /**
     * @param itemId
     * @param amount
     * @return boolean
     * @description 扣减库存
     */
    boolean decreaseStock(Integer itemId,Integer amount) throws BusinessException;

    /**
     * @description 增加商品销量
     */
    void increaseSales(Integer itemId,Integer amount) throws BusinessException;

}
