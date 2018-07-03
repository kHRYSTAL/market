package me.khrystal.market.dao;

import me.khrystal.market.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by kHRYSTAL on 18/7/3.
 */
public interface ShopCategoryDao {
    // 接收参数为shopcategory筛选条件的参数 返回shopcategory
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
