package com.oywl.stream.entity;

/**
 * @description:
 * @author: oywl
 * @time: 2024-05-27 7:50
 */

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DateGrouping {

    // 辅助函数：获取年份
    public static int getYear(LocalDate date) {
        return date.getYear();
    }

    // 辅助函数：获取周数（ISO标准，周一为一周的第一天）
    public static int getWeekOfYear(LocalDate date) {
        return date.getDayOfWeek().getValue();
    }

    // 辅助函数：获取月日（用于同一天分组）
    public static String getMonthDay(LocalDate date) {
        return String.format("%02d%02d", date.getMonthValue(), date.getDayOfMonth());
    }

    public static void main(String[] args) {
        List<LocalDate> dates = Arrays.asList(
                // 添加一些示例日期
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 2),
                LocalDate.of(2023, 1, 8),
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2022, 12, 31)
        );

        // 分组：同一天
        Map<String, List<LocalDate>> bySameDay = dates.stream()
                .collect(Collectors.groupingBy(DateGrouping::getMonthDay));

        // 分组：同一周
        Map<Integer, List<LocalDate>> bySameWeek = dates.stream()
                .collect(Collectors.groupingBy(DateGrouping::getWeekOfYear));

        // 分组：同一年
        Map<Integer, List<LocalDate>> bySameYear = dates.stream()
                .collect(Collectors.groupingBy(DateGrouping::getYear));

        // 打印结果（这里只是简单打印，你可以根据需要进行处理）
        bySameDay.forEach((key, value) -> System.out.println("Same Day: " + key + " -> " + value));
        bySameWeek.forEach((key, value) -> System.out.println("Same Week: " + key + " -> " + value));
        bySameYear.forEach((key, value) -> System.out.println("Same Year: " + key + " -> " + value));
    }
}
