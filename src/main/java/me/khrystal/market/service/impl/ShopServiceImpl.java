package me.khrystal.market.service.impl;

import me.khrystal.market.dao.ShopDao;
import me.khrystal.market.dto.ShopExecution;
import me.khrystal.market.entity.Shop;
import me.khrystal.market.enums.ShopStateEnum;
import me.khrystal.market.exceptions.ShopOperationException;
import me.khrystal.market.service.ShopService;
import me.khrystal.market.util.ImageUtil;
import me.khrystal.market.util.PageCalculator;
import me.khrystal.market.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by kHRYSTAL on 18/7/2.
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional // 该方法需要事务的支持
    public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) throws ShopOperationException {
        // 检查传入参数是否合法
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        // TODO: 18/7/2 对shopCategory和ShopArea也要进行非空判断
        try {
            // 给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            // 将店铺信息插入至数据库
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) { // 添加失败
                throw new ShopOperationException("店铺创建失败"); // 只有抛出RuntimeException 事务才会中断回滚
            } else {
                if (shopImg != null) {
                    try {
                        // 存储图片至内存对象
                        addShopImg(shop, shopImg);
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    // 更新数据库中店铺的图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("更新数据库图片地址失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

//    @Override
//    public List<Shop> getShopList() {
//        return shopDao.queryShop();
//    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryShopByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImgInputStream) throws ShopOperationException {

        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            //1. 判断是否需要处理图片
            try {
                Shop tempShop = shopDao.queryShopByShopId(shop.getShopId());
                if (tempShop.getShopImg() != null) {
                    ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                }
                addShopImg(shop, shopImgInputStream);
                //2. 更新店铺信息
                shop.setLastEditTime(new Date());
                int effectNum = shopDao.updateShop(shop);
                if (effectNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    shop = shopDao.queryShopByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ShopOperationException("modifyShopError:" + e.getMessage());
            }
        }
    }

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList != null) {
            se.setShopList(shopList);
            se.setCount(count);
        } else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
        // 获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
        shop.setShopImg(shopImgAddr);
    }
}
