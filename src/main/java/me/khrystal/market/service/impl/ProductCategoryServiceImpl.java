package me.khrystal.market.service.impl;

import me.khrystal.market.dao.ProductCategoryDao;
import me.khrystal.market.dto.ProductCategoryExecution;
import me.khrystal.market.entity.ProductCategory;
import me.khrystal.market.enums.ProductCategoryStateEnum;
import me.khrystal.market.exceptions.ProductCategoryOperationException;
import me.khrystal.market.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kHRYSTAL on 18/7/12.
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                int effectNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectNum < 0) {
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException("batchAddProductCategory error:" + e.getMessage());
            }
        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }
}
