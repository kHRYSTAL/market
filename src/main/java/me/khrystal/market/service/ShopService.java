package me.khrystal.market.service;

import me.khrystal.market.dto.ShopExecution;
import me.khrystal.market.entity.Area;
import me.khrystal.market.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.List;

/**
 * Created by kHRYSTAL on 18/7/2.
 */
public interface ShopService {

    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);

    public List<Shop> getShopList();
}
