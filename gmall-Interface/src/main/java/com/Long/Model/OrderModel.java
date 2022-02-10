package com.Long.Model;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
   /**
      * @autor Jack Chao
      * @version
      * @ClassName : OrderModel
      * @date : 2022/2/8 10:24
      * @description: 订单模块领域模型
      */

@Data
public class OrderModel implements Serializable {
    /**
     * 订单号采用String类型，保存的各个字段都有意义
     */
    private String id;
    private Integer userId;
    private Integer itemId;
    /**
     * 购买商品的单价
     */
    private BigDecimal itemPrice;
    /**
     * 购买数量
     */
    private Integer amount;
    /**
     * 总金额
     */
    private BigDecimal orderPrice;
    /**
     * 若非空，表示以秒杀商品方式下单
     */
    private Integer promoId;
}
