package me.khrystal.market.dao.split;

import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Properties;

/**
 * Created by kHRYSTAL on 18/7/5.
 */
public class DynamicDataSourceInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 判断当前是不是事务
        boolean synchoronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
        if (!synchoronizationActive) {
            Object[] objects = invocation.getArgs();
            MappedStatement ms = (MappedStatement) objects[0];
            String lookupKey;
            // 判断是否是读方法
            if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                // 且selectKey 为自增id查询主键(SELECT LAST_INSERT_ID()) 方法 使用主库
                if (ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                    lookupKey = DynamicDataSourceHolder.DB_MASTER;
                } else {
                    // 获取sql语句
                    BoundSql boundSql = ms.getSqlSource().getBoundSql(objects[1]);
                    // 将制表符 回车都替换为空格
                    String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
                    // TODO: 18/7/5  
                }
            }
        }
        return null;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor)
            return Plugin.wrap(true, this);
        else
            return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
