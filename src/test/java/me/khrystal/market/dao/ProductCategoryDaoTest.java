package me.khrystal.market.dao;

import me.khrystal.market.BaseTest;
import me.khrystal.market.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kHRYSTAL on 18/7/11.
 */
public class ProductCategoryDaoTest extends BaseTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testQueryByShopId() throws Exception {
        long shopId = 1;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println("该店铺自定义类别数为:" + productCategoryList.size());
    }

    @Test
    public void testBatchInsertProductCategory() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("商品类别1");
        productCategory.setPriority(1);
        productCategory.setCreateTime(new Date());
        productCategory.setShopId(32L);

        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setProductCategoryName("商品类别2");
        productCategory1.setPriority(2);
        productCategory1.setCreateTime(new Date());
        productCategory1.setShopId(32L);

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory1);
        int effectNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
        Assert.assertEquals(2, effectNum);
    }
}
