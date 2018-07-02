package me.khrystal.market.service.impl;

import me.khrystal.market.dao.ShopDao;
import me.khrystal.market.dto.ShopExecution;
import me.khrystal.market.entity.Shop;
import me.khrystal.market.enums.ShopStateEnum;
import me.khrystal.market.exceptions.ShopOperationException;
import me.khrystal.market.service.ShopService;
import me.khrystal.market.util.ImageUtil;
import me.khrystal.market.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.Date;

/**
 * Created by kHRYSTAL on 18/7/2.
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional // 该方法需要事务的支持
    public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) {
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

    private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
        // 获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
        shop.setShopImg(shopImgAddr);
    }
}
