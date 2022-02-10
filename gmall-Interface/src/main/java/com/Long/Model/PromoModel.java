package com.Long.Model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;
   /**
      * @autor Jack Chao
      * @version
      * @ClassName : PromoModel
      * @date : 2022/2/8 10:24
      * @description: 秒杀模块的领域模型
      */

@Data
@NoArgsConstructor
public class PromoModel implements Serializable {
    private Integer id;

    /**
     * 名称
     */
    private String promoName;

    /**
     * 活动状态:1表示未开始，2表示进行中，3表示已结束
     */
    private Integer status;

    /**
     * 开始时间
     */
    private DateTime startTime;

    /**
     * 结束时间
     */
    private DateTime endTime;

    /**
     * 适用商品
     */
    private Integer itemId;

    /**
     * 商品价格
     */
    private BigDecimal promoItemPrice;

}
