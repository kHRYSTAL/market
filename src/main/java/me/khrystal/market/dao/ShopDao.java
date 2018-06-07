package me.khrystal.market.dao;

import me.khrystal.market.entity.Shop;

/**
 * Created by kHRYSTAL on 18/6/7.
 */
public interface ShopDao {

    /**
     * 新增店铺
     *  －1 插入失败 1 插入成功
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
