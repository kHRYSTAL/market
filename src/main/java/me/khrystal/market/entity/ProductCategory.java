package me.khrystal.market.entity;

import java.util.Date;

/**
 * Created by kHRYSTAL on 18/6/4.
 */
public class ProductCategory {
    private Long productCategoryId;
    private Long shopId;
    private String productCategoryName;
    protected Integer priority;
    private Date createTime;

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public Long getShopId() {
        return shopId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public Integer getPriority() {
        return priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
