package me.khrystal.market.entity;

import java.util.Date;

/**
 * Created by kHRYSTAL on 18/6/4.
 */
public class ProductImg {
    private Long productImgId;
    private String imgAddr;
    private String imgDesc;
    private Integer priority;
    private Date createTime;
    private Long productId;

    public Long getProductImgId() {
        return productImgId;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public Integer getPriority() {
        return priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductImgId(Long productImgId) {
        this.productImgId = productImgId;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
