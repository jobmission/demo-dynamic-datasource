package com.revengemission.demo.dynamicdatasource;

import com.revengemission.demo.dynamicdatasource.persistence.app.entity.DayStat;
import com.revengemission.demo.dynamicdatasource.persistence.app.entity.DayStatExample;
import com.revengemission.demo.dynamicdatasource.persistence.app.entity.DayTaskResult;
import com.revengemission.demo.dynamicdatasource.persistence.app.entity.DayTaskResultExample;
import com.revengemission.demo.dynamicdatasource.persistence.app.mapper.DayStatMapper;
import com.revengemission.demo.dynamicdatasource.persistence.app.mapper.DayTaskResultMapper;
import com.revengemission.demo.dynamicdatasource.persistence.sqlserver.entity.ValueTableFloat;
import com.revengemission.demo.dynamicdatasource.service.TagIndexService;
import com.revengemission.demo.dynamicdatasource.service.ValueTableFloatService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootContextTest {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DayStatMapper dayStatMapper;

    @Autowired
    TagIndexService tagIndexService;

    @Autowired
    ValueTableFloatService valueTableFloatService;

    @Autowired
    DayTaskResultMapper dayTaskResultMapper;

    static final List<String> dataSourceList = new LinkedList<>();

    static {
        dataSourceList.add("sqlserver1DataSource");
        dataSourceList.add("sqlserver2DataSource");
        dataSourceList.add("sqlserver3DataSource");
        dataSourceList.add("sqlserver4DataSource");
    }


    @Test
    @Ignore
    public void dynamicDataSourceTest() throws Exception {
        int a = tagIndexService.count("sqlserver1DataSource", "TagIndex");
        System.out.println("a:" + a);
        int b = tagIndexService.count("sqlserver2DataSource", "TagIndex");
        System.out.println("b:" + b);

    }

    @Test
    @Ignore
    public void importSqlserverDataTest() {

        String yesterday = yesterday();
        DayTaskResultExample dayTaskResultExample = new DayTaskResultExample();
        dayTaskResultExample.createCriteria().andDayEqualTo(yesterday);
        List<DayTaskResult> dayTaskResults = dayTaskResultMapper.selectByExample(dayTaskResultExample);
        if (dayTaskResults != null && dayTaskResults.size() > 0) {
            log.error("任务已经执行过，请清理数据后再执行！！！");
            return;
        }
        DayTaskResult dayTaskResult = new DayTaskResult();
        dayTaskResult.setDay(yesterday);
        dayTaskResult.setBeginTime(new Date());
        dayTaskResult.setResult(0);
        dayTaskResultMapper.insert(dayTaskResult);

        log.info("【"+yesterday+"】day task begin...");

        dataSourceList.forEach(dataSource -> {
            int totalIndex = 0;
            try {
                totalIndex = tagIndexService.count(dataSource, "TagIndex");
                int devices = totalIndex / 30;
                String tableName = "ValueTable_FLOAT_" + yesterday;
                for (int i = 0; i < devices; i++) {
                    List<ValueTableFloat> valueTableFloatListP = valueTableFloatService.list(dataSource, tableName, 13 + (i * 30));
                    valueTableFloatListP.forEach(valueTableFloat -> {
                        DayStatExample example = new DayStatExample();
                        example.createCriteria().andValueTimeEqualTo(valueTableFloat.getValueTime());
                        List<DayStat> dayStatPS = dayStatMapper.selectByExample(example);
                        if (dayStatPS == null || dayStatPS.size() == 0) {
                            DayStat dayStatP = new DayStat();
                            dayStatP.setVersion(1);
                            dayStatP.setValueTime(valueTableFloat.getValueTime());
                            dayStatP.setValue(new BigDecimal(valueTableFloat.getValue()));
                            dayStatMapper.insert(dayStatP);
                        } else {
                            DayStat dayStatP = dayStatPS.get(0);
                            dayStatP.setValue(dayStatP.getValue().add(new BigDecimal(valueTableFloat.getValue())));
                            dayStatP.setVersion(dayStatP.getVersion() + 1);
                            dayStatMapper.updateByPrimaryKey(dayStatP);
                        }
                    });
                }

                log.info("【" + dataSource + "】task complete");
            } catch (CannotGetJdbcConnectionException exception) {
                log.error("【" + dataSource + "】obtain database connection exception", exception);
                dayTaskResult.setResult(-1);
                dayTaskResult.setEndTime(new Date());
                dayTaskResult.setRemark(exception.getMessage());
                dayTaskResultMapper.updateByPrimaryKey(dayTaskResult);
                throw exception;
            } catch (Exception exception) {
                log.error("【" + dataSource + "】 exception", exception);
                dayTaskResult.setResult(-1);
                dayTaskResult.setEndTime(new Date());
                dayTaskResult.setRemark(exception.getMessage());
                dayTaskResultMapper.updateByPrimaryKey(dayTaskResult);
                throw exception;
            }
        });

        dayTaskResult.setResult(1);
        dayTaskResult.setEndTime(new Date());
        dayTaskResultMapper.updateByPrimaryKey(dayTaskResult);
        log.info("【"+yesterday+"】day task end");

    }

    private String yesterday() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");
        LocalDate localDate = LocalDate.now();
        LocalDate newDate = localDate.minusDays(1);
        return dateTimeFormatter.format(newDate);
    }
}
