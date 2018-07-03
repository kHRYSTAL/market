package me.khrystal.market.service;

import me.khrystal.market.entity.ShopCategory;

import java.util.List;

/**
 * Created by kHRYSTAL on 18/7/3.
 */
public interface ShopCategoryService {
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
