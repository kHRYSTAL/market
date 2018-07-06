package me.khrystal.market.dao.split;

import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Properties;

/**
 * Created by kHRYSTAL on 18/7/5.
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args={MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args={MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class DynamicDataSourceInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceInterceptor.class);

    // u0020 表示空格
    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String lookupKey = DynamicDataSourceHolder.DB_MASTER;
        // 判断当前是不是事务
        boolean synchoronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
        Object[] objects = invocation.getArgs();
        MappedStatement ms = (MappedStatement) objects[0];
        if (!synchoronizationActive) { // 不是事务 按照sql语句去区分
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

                    if (sql.matches(REGEX)) { // 写操作使用主库
                        lookupKey = DynamicDataSourceHolder.DB_MASTER;
                    } else { // 读操作使用从库
                        lookupKey = DynamicDataSourceHolder.DB_SLAVE;
                    }
                }
            }
        } else { // 事务操作 都是写操作 使用主库
            lookupKey = DynamicDataSourceHolder.DB_MASTER;
        }
        logger.debug("设置方法[{}] use [{}] Strategy, SqlCommanType[{}]..", ms.getId(), lookupKey, ms.getSqlCommandType().name());
        DynamicDataSourceHolder.setDbType(lookupKey);
        // 拦截器处理完毕 执行后续流程
        return invocation.proceed();
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
