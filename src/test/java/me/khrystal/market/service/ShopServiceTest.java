package me.khrystal.market.service;

import me.khrystal.market.BaseTest;
import me.khrystal.market.dto.ShopExecution;
import me.khrystal.market.entity.Area;
import me.khrystal.market.entity.PersonInfo;
import me.khrystal.market.entity.Shop;
import me.khrystal.market.entity.ShopCategory;
import me.khrystal.market.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by kHRYSTAL on 18/7/2.
 */
public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

//    @Test
//    public void testAddShop() {
//        Shop shop = new Shop();
//        PersonInfo owner = new PersonInfo();
//        Area area = new Area();
//        ShopCategory shopCategory = new ShopCategory();
//        owner.setUserId(1L);
//        area.setAreaId(2);
//        shopCategory.setShopCategoryId(1L);
//        shop.setOwner(owner);
//        shop.setArea(area);
//        shop.setShopCategory(shopCategory);
//        shop.setShopName("测试的店铺1");
//        shop.setShopName("test1");
//        shop.setShopAddr("test1");
//        shop.setPhone("test");
//        shop.setCreateTime(new Date());
//        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
//        shop.setAdvise("审核中");
//        File image = new File("/Users/kHRYSTAL/IdeaProjects/market/src/main/resources/watermark.png");
//        ShopExecution se = shopService.addShop(shop, image);
//        assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
//    }
}
