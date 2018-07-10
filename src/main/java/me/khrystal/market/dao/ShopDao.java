package me.khrystal.market.dao;

import me.khrystal.market.dto.ShopExecution;
import me.khrystal.market.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<Shop> queryShop();

    Shop queryShopByShopId(long shopId);

    /**
     * 分页查询店铺, 可输入的条件有: 店铺名(模糊), 店铺状态, 店铺类别, 区域id, ownerId
     * rowIndex 从第几个位置开始取
     * pagesize 返回的条数
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize);

    /**
     * 查询符合类目的商铺个数
     * @param shopCondition
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);
}
