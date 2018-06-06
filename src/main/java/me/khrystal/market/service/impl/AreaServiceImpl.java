package me.khrystal.market.service.impl;

import me.khrystal.market.dao.AreaDao;
import me.khrystal.market.entity.Area;
import me.khrystal.market.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kHRYSTAL on 18/6/6.
 */
@Service
public class AreaServiceImpl implements AreaService {

    // @Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，
    // 完成自动装配的工作。 通过 @Autowired的使用来消除 set ，get方法。
    // 在使用@Autowired之前，我们对一个bean配置起属性时，是这用用的
    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
