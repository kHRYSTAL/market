package me.khrystal.market.dao;

import me.khrystal.market.entity.Area;

import java.util.List;

/**
 * Created by kHRYSTAL on 18/6/6.
 */
public interface AreaDao {
    // 列出区域列表
    List<Area> queryArea();
}
