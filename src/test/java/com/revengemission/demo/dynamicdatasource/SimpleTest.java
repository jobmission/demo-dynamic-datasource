package com.revengemission.demo.dynamicdatasource;

import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by revenge mission on 18-6-17.
 */
public class SimpleTest {

    @Test
    public void luhnTest() throws Exception {
        LuhnCheckDigit luhn = new LuhnCheckDigit();
        List<String> input = new ArrayList<>();
        /*IntStream.iterate(1, n -> n + 1).limit(50).forEach(e -> {
            String temp = RandomStringUtils.randomNumeric(8);
            try {
                input.add(temp + luhn.calculate(temp));
            } catch (CheckDigitException e1) {
                e1.printStackTrace();
            }
        });*/
        input.add("123213213");
        input.add("44444");
        input.add("5432123456788881");
        input.forEach(e -> {
            boolean flag = luhn.isValid(e);
            System.out.println(e);
            System.out.println("flag:" + flag);
        });
    }

    @Test
    public void localDateTest() throws Exception {

        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy_MM_dd");
        LocalDate localDate = LocalDate.now();
        LocalDate newDate = localDate.minusDays(1);
        System.out.println("Yesterday  : " + newDate);

        String strMyDateTime= dateTimeFormatter.format(localDate);
        System.out.println("自定义格式化:"+strMyDateTime);

    }
}
