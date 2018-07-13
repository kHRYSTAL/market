package me.khrystal.market.dto;

import me.khrystal.market.entity.ProductCategory;
import me.khrystal.market.enums.ProductCategoryStateEnum;

import java.util.List;

/**
 * Created by kHRYSTAL on 18/7/13.
 */
public class ProductCategoryExecution {
    // 结果状态
    private int state;
    // 状态标识
    private String stateInfo;

    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution() {}

    // 操作失败时使用
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // 操作成功时的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
