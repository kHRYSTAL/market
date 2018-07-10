package me.khrystal.market.dao;

import me.khrystal.market.BaseTest;
import me.khrystal.market.dto.ShopExecution;
import me.khrystal.market.entity.Area;
import me.khrystal.market.entity.PersonInfo;
import me.khrystal.market.entity.Shop;
import me.khrystal.market.entity.ShopCategory;
import me.khrystal.market.exceptions.ShopOperationException;
import me.khrystal.market.service.ShopService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by kHRYSTAL on 18/6/7.
 */
public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Autowired
    private ShopService shopService;

    @Test
    public void testInsertShop() {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setArea(area);
        shop.setOwner(owner);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试店铺名称");
        shop.setShopName("测试店铺描述");
        shop.setShopAddr("测试店铺地址");
        shop.setPhone("测试店铺电话");
        shop.setShopImg("测试店铺图片");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setLastEditTime(new Date());
        shop.setAdvise("测试店铺建议");
        int effectNum = shopDao.insertShop(shop);
        assertEquals(effectNum, 1);
    }

    @Test
    public void testUpdateShop() {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvise("审核中");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);

    }

    @Test
    public void testQueryByShopId() {
        long shopId = 1L;
        Shop shop = shopDao.queryShopByShopId(shopId);
        System.out.println("areaId:" + shop.getArea().getAreaId());
        System.out.println("areaName:" + shop.getArea().getAreaName());
    }

    @Test
    @Ignore
    public void testModifyShop() throws ShopOperationException, FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("修改后的店铺名称");
        File shopImg = new File("/Users/khrystal/image/dabai.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution = null;
//      shopExecution = shopService.modifyShop(shop, )
        System.out.println("新的图片地址为:" + shopExecution.getShop().getShopImg());
    }

    @Test
    public void testQueryShopList() {
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shopCondition.setOwner(owner);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, 1, 5);
        System.out.println("店铺数量" + shopList.size());
    }

    @Test
    public void testQueryShopCount() {
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shopCondition.setOwner(owner);
        int count = shopDao.queryShopCount(shopCondition);
        System.out.println("店铺列表大小:" + count);
    }

    @Test
    public void testGetShopList() {
        Shop shopCondition = new Shop();
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(1L);
        shopCondition.setShopCategory(sc);
        ShopExecution se = shopService.getShopList(shopCondition, 0, 2);
        System.out.println("店铺列表数为:" +  se.getShopList().size());
        System.out.println("店铺总数为:" + se.getCount());

    }
}
