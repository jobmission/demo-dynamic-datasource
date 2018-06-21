package com.revengemission.demo.dynamicdatasource.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by revenge mission on 18-6-17.
 */
public class DataSourceContextHolder {
    public static final Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);

    /**
     * 默认数据源
     */
    public static final String DEFAULT_DS = "appDataSource";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
        if (log.isDebugEnabled()) {
            log.debug("set数据源【{}】...", dbType);
        }
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        if (contextHolder.get() == null) {
            return DEFAULT_DS;
        }
        return (contextHolder.get());
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }
}
