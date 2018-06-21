package com.revengemission.demo.dynamicdatasource.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by revenge mission on 18-6-17.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    public static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        if (log.isDebugEnabled()) {
            log.debug("determine当前数据源:【{}】", DataSourceContextHolder.getDB());
        }
        return DataSourceContextHolder.getDB();
    }

}
