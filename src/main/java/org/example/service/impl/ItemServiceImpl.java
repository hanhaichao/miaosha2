package org.example.service.impl;

import org.example.controller.Validation.ValidationResult;
import org.example.controller.Validation.ValidatorImpl;
import org.example.controller.error.BusinessException;
import org.example.controller.error.EmBusinessError;
import org.example.entity.Item;
import org.example.entity.ItemStock;
import org.example.mapper.ItemMapper;
import org.example.mapper.ItemStockMapper;
import org.example.service.ItemService;
import org.example.service.PromoService;
import org.example.service.model.ItemModel;
import org.example.service.model.PromoModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description ItemService的实现类
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemStockMapper itemStockMapper;

    @Autowired
    private PromoService promoService;

    /**
     * @param itemModel:商品模型
     * @return ItemModel:商品模型
     * @description 创建商品
     */
    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        //校验入参
        ValidationResult result = validator.validate(itemModel);
        if (result.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, result.getErrMsg());
        }
        //转化model
        Item item = this.convertItemFromItemModel(itemModel);
        //写入数据库
        itemMapper.insertSelective(item);
        itemModel.setId(item.getId());
        ItemStock itemStock = this.convertItemStockFromItemModel(itemModel);
        itemStockMapper.insertSelective(itemStock);
        //返回对象
        return this.getItemById(itemModel.getId());
    }

    /**
     * @param id：商品id
     * @return ItemModel
     * @description 通过id获取商品详情
     */
    @Override
    public ItemModel getItemById(Integer id) {
        Item item = itemMapper.selectByPrimaryKey(id);
        if (item == null) {
            return null;
        }
        ItemStock itemStock = itemStockMapper.selectByItemId(item.getId());
        ItemModel itemModel = convertFromEntity(item, itemStock);

        PromoModel promoModel = promoService.getPromoByItemId(itemModel.getId());
        if (promoModel != null && promoModel.getStatus().intValue() != 3) {
            itemModel.setPromoModel(promoModel);
        }
        return itemModel;
    }

    /**
     * @return List<ItemModel>:商品List
     * @description 列出商品
     */
    @Override
    public List<ItemModel> listItem() {
        List<Item> itemList = itemMapper.listItem();
        List<ItemModel> itemModelList = itemList.stream().map(item -> {
            ItemStock itemStock = itemStockMapper.selectByItemId(item.getId());
            ItemModel itemModel = this.convertFromEntity(item, itemStock);
            return itemModel;
        }).collect(Collectors.toList());
        return itemModelList;
    }

    /**
     * @param itemId
     * @param amount
     * @return boolean
     * @description 扣减库存
     */
    @Override
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount) throws BusinessException {
        int affectedRow = itemStockMapper.decreaseStock(itemId, amount);
        if (affectedRow > 0) {
            //更新库存成功
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param
     * @return
     * @description 增加商品销量
     */
    @Override
    @Transactional
    public void increaseSales(Integer itemId, Integer amount) throws BusinessException {
        itemMapper.increaseSales(itemId, amount);
    }

    /**
     * @param itemModel
     * @return Item
     * @description 将itemModel转换为Item
     */
    private Item convertItemFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        Item item = new Item();
        BeanUtils.copyProperties(itemModel, item);
        //数据库中price是double类型的，ItemModel中是BigDecimal，避免类型转化时出现精度丢失
        item.setPrice(itemModel.getPrice().doubleValue());
        return item;
    }

    /**
     * @param itemModel
     * @return ItemStock：商品库存
     * @description 将itemModel转化为ItemStock
     */
    private ItemStock convertItemStockFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemStock itemStock = new ItemStock();
        itemStock.setItemId(itemModel.getId());
        itemStock.setStock(itemModel.getStock());
        return itemStock;
    }

    /**
     * @param item
     * @param itemStock
     * @return ItemModel
     * @description 将item和itemStock转换为ItemModel
     */
    private ItemModel convertFromEntity(Item item, ItemStock itemStock) {
        if (item == null) {
            return null;
        }
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(item, itemModel);

        //从数据库data向item model转的时候也要注意price的类型问题
        itemModel.setPrice(new BigDecimal(item.getPrice()));
        itemModel.setStock(itemStock.getStock());

        return itemModel;
    }
}
