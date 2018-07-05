package me.khrystal.market.dao.split;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by kHRYSTAL on 18/7/5.
 */

/**
 * 动态获取主从服务器类型
 */
public class DynamicDataSourceHolder {
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);
    // 保证线程安全
    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();
    public static final String DB_MASTER = "master";
    public static final String DB_SLAVE = "slave";

    /**
     * 获取数据源 主或从
     * @return
     */
    public static String getDbType() {
        String db = contextHolder.get();
        if (db == null) {
            db = DB_MASTER;
        }
        return db;
    }

    /**
     * 设置数据源 主或从
     * @param str
     */
    public static void setDbType(String str) {
        logger.debug("所使用的数据源为:" + str);
        contextHolder.set(str);
    }

    /**
     * 清洗数据源
     */
    public static void clearDBType() {
        contextHolder.remove();
    }

}
