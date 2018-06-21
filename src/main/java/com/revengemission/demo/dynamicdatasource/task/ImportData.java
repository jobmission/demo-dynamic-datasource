package com.revengemission.demo.dynamicdatasource.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by revenge mission on 18-6-17.
 */
@Component
public class ImportData {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "0 08 20 * * *")
    public void importSqlserverData() {
        log.info(LocalDateTime.now().toString());
    }
}
