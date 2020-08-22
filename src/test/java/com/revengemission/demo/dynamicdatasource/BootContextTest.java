package com.revengemission.demo.dynamicdatasource;

import com.revengemission.demo.dynamicdatasource.service.MonthStatService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootContextTest {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MonthStatService monthStatService;

    static final List<String> dataSourceList = new LinkedList<>();

    static {
        dataSourceList.add("jdbc1Datasource");
        dataSourceList.add("jdbc2Datasource");
        dataSourceList.add("jdbc3Datasource");
        dataSourceList.add("jdbc4Datasource");
    }

    @Test
    @Ignore
    public void dynamicDataSourceTest() throws Exception {
        long a = monthStatService.count("jdbc3Datasource");
        System.out.println("a:" + a);
        long b = monthStatService.count("jdbc4Datasource");
        System.out.println("b:" + b);
        monthStatService.insert("jdbc3Datasource", "2020-08-08", System.currentTimeMillis() + "");
        long c = monthStatService.count("jdbc3Datasource");
        System.out.println("c:" + c);
    }
}
