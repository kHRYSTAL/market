package me.khrystal.market.service;

import me.khrystal.market.dto.ShopExecution;
import me.khrystal.market.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

/**
 * Created by kHRYSTAL on 18/7/2.
 */
public interface ShopService {


    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);
}
