package com.micro.web.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @desc 日期工具类  LocalDate
 * @date 2018-10-10
 */
public class LocalDateUtil {

    /**
     * 日期格式
     */
    static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static void main(String[] args) {
//        LocalDate localDate = LocalDate.parse("20181010", df);
//        printStringLocalDate(localDate);

        getLocalDate();
    }

    public static String getLocalDate(){
        String format = df.format(LocalDate.now());
        System.out.println("LocalDate ："+format);
        return  format;
    }




    public static void printStringLocalDate(LocalDate localDate ){
        String format = df.format(localDate);
        System.out.println("LocalDate ："+format);
    }

    public static LocalDate plusDays(){
        LocalDate localDate = LocalDate.parse("20181010", df);
        LocalDate nextDay = localDate.plusDays(1);
        printStringLocalDate(nextDay);
        return nextDay;
    }
    public static LocalDate plusWeeks(){
        LocalDate localDate = LocalDate.parse("20181010", df);
        LocalDate nextWeek = localDate.plusWeeks(1);
        printStringLocalDate(nextWeek);
        return nextWeek;
    }
    public static LocalDate plusMons(){
        LocalDate localDate = LocalDate.parse("20181010", df);
        LocalDate nextMon = localDate.plusMonths(1);
        printStringLocalDate(nextMon);
        return nextMon;
    }






}
