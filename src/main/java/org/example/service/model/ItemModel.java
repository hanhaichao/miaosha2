package org.example.service.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Zhang Yifei
 * @date 2019/12/6 13:53
 * @description 商品领域模型
 */
public class ItemModel {

    private Integer id;

    @NotBlank(message = "商品名称不能为空")
    private String title;

    @NotNull(message = "商品价格不能为空")
    @Min(value = 0, message = "商品价格必须大于0")
    private BigDecimal price;

    @NotNull(message = "库存必须填写")
    private Integer stock;

    @NotNull(message = "商品描述不能为空")
    private String description;

    /**
     * 销量
     */
    private Integer sales;

    @NotNull(message = "商品图片信息不能为空")
    private String imgUrl;

    /**
     * 使用聚合模型，如果promoModel不为空，则表示其拥有还未结束的秒杀活动
     */
    private PromoModel promoModel;

    public PromoModel getPromoModel() {
        return promoModel;
    }

    public void setPromoModel(PromoModel promoModel) {
        this.promoModel = promoModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
//        BigDecimal(int)
//        创建一个具有参数所指定整数值的对象
//        BigDecimal(double)
//        创建一个具有参数所指定双精度值的对象
//        BigDecimal(long)
//        创建一个具有参数所指定长整数值的对象
//        BigDecimal(String)
//        创建一个具有参数所指定以字符串表示的数值的对象
        return price;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", sales=" + sales +
                ", imgUrl='" + imgUrl + '\'' +
                ", promoModel=" + promoModel +
                '}';
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
