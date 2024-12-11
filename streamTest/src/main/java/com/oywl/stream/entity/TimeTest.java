package com.oywl.stream.entity;

import org.junit.Test;

import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * @description: LocalDateTime, LocalDate, LocalTime  当前时间
 *              ZoneDateTime 时区
 *              Instant 当前时间戳 纳秒，总纳秒
 *              DateTimeFormatter 线程安全的时间格式化类
 *              Period Duration 计算时间间隔，年月日，天时分秒纳秒
 *
 * @author: oywl
 * @time: 2024-05-27 6:57
 */

public class TimeTest {
    @Test
    public void t1() {
        System.out.println(TimeZone.getDefault().getID());
        Instant now = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));
        //输出秒数和毫秒数
        System.out.println(now.getEpochSecond());
        System.out.println(now.toEpochMilli());

        System.out.println(now);

        Instant instant1 = now.plusSeconds(20);
        Instant instant2 = now.plusMillis(20);
        Instant instant3 = now.plusNanos(20);
        System.out.println(instant1);
        System.out.println(instant2);
        System.out.println(instant3);
    }

    @Test
    public void t2(){
        LocalDate today = LocalDate.now();
        LocalDate dateOfBitrh = LocalDate.of(2018, 9, 8);
        System.out.println(dateOfBitrh.getMonth());
        System.out.println(dateOfBitrh.getDayOfMonth());
        MonthDay birthday = MonthDay.of(dateOfBitrh.getMonth(), dateOfBitrh.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);
        System.out.println(birthday);
        System.out.println(currentMonthDay);
        if (birthday.equals(currentMonthDay)) {
            System.out.println("Happy Birthday!");
        }else{
            System.out.println("Sorry ,today is not your birthday!");
        }
    }

    @Test
    public void t3(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd号 HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String format = formatter.format(now);
        System.out.println(format);
        System.out.println(now.format(formatter));

        LocalDateTime parse = LocalDateTime.parse(format, formatter);
        System.out.println(parse);
    }

    //Period 计算年月日间隔，Duration计算时分秒，纳秒间隔
    @Test
    public void t4(){
        LocalDate begin = LocalDate.of(2022, 5, 20);
        LocalDate end = LocalDate.of(2024, 6, 27);

        Period period = Period.between(begin, end);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());


    }
    @Test
    public void t5(){
//        LocalTime begin = LocalTime.of(10, 32, 11, 123);
//        LocalTime end = LocalTime.of(12, 39, 22, 233);
//
//        Duration duration = Duration.between(begin, end);
//        System.out.println(duration.toDays());
//        System.out.println(duration.toHours());
//        System.out.println(duration.toMinutes());
//        System.out.println(duration.getSeconds() );
//        System.out.println(duration.toMillis());

        LocalDateTime begin = LocalDateTime.of(2025, 10, 10, 12, 11, 23);
        LocalDateTime end = LocalDateTime.of(2025, 11, 14, 17, 13, 23);
        Duration duration = Duration.between(begin, end);
        System.out.println(duration.toDays());//间隔多少天
        System.out.println(duration.toHours());//间隔多少小时
        System.out.println(duration.toMinutes());//间隔多少分
        System.out.println(duration.getSeconds());//间隔多少秒
        System.out.println(duration.toMillis());//间隔多少毫秒
        System.out.println(duration.toNanos());//间隔多少纳秒
    }
}
