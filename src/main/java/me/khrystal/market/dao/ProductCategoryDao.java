package me.khrystal.market.dao;

import me.khrystal.market.entity.ProductCategory;

import java.util.List;

/**
 * Created by kHRYSTAL on 18/7/11.
 */
public interface ProductCategoryDao {
    /**
     * 通过shop id 查询店铺下所有商品类别
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryList(long shopId);
}
