package com.revengemission.demo.dynamicdatasource;

import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by revenge mission on 18-6-17.
 */
public class SimpleTest {

    @Ignore
    @Test
    public void localDateTest() throws Exception {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");
        LocalDate localDate = LocalDate.now();
        LocalDate newDate = localDate.minusDays(1);
        System.out.println("Yesterday  : " + newDate);

        String strMyDateTime = dateTimeFormatter.format(localDate);
        System.out.println("自定义格式化:" + strMyDateTime);

    }
}
