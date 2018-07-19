package me.khrystal.market.service;

import me.khrystal.market.dto.ProductCategoryExecution;
import me.khrystal.market.entity.ProductCategory;
import me.khrystal.market.exceptions.ProductCategoryOperationException;

import java.util.List;

/**
 * Created by kHRYSTAL on 18/7/12.
 */
public interface ProductCategoryService {

    public List<ProductCategory> getByShopId(long shopId);

    /**
     * 查询指定某个店铺下的所有商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;

    /**
     *
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws RuntimeException
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId,
                                                   long shopId) throws RuntimeException;
}
