package com.oywl.Thread;

import ch.qos.logback.core.util.TimeUtil;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @description: https://blog.csdn.net/wangdong5678999/article/details/81837387
 * @author: oywl
 * @time: 2022-6-9 15:35
 */

public class mainTest {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public static void main(String[] args) throws ParseException {
        System.out.println("yyyy-MM-dd 41:23:12".substring(11,16));
//        SimpleFuture();
//        System.out.println("LSJE36092FG1790002".length());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2022-12-12 12:12"));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()));

        List<String> list = new ArrayList<>(Arrays.asList("90621007,90621008,90621010".split(",")));
        Collections.addAll(list,"da");
        System.out.println(86400000L/3600);
        System.out.println(new Date().getTime()/1000);
//        list.add("ss");

        BigDecimal b1 = new BigDecimal("0.00");
        BigDecimal b2 = new BigDecimal("0.00");
        BigDecimal b3 = new BigDecimal("0.00");
        BigDecimal b4 = new BigDecimal("0.00");
        System.out.println(b1.add(b2).subtract(b3).subtract(b4));
    }

    private static void SimpleFuture() {
        System.out.println("开始:" + simpleDateFormat.format(new Date()));
        ThreadServiceImpl service = new ThreadServiceImpl();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future<String> future = threadPool.submit(service::doSomething1);
        Future<String> future2 = threadPool.submit(() -> service.doSomethingElse(3000));
        System.out.println("执行:" + simpleDateFormat.format(new Date()));
//        System.out.println("睡2秒");
//        sleep();
//        System.out.println("睡2秒结束");
        System.out.println("时间1:" + simpleDateFormat.format(new Date()));
        try {
            //get操作会阻塞当前线程，直到返回结果
            System.out.println("结果:" + future.get());
//            future.get(1, TimeUnit.SECONDS);//这里设置最长等待时间
            System.out.println("结果:" + future2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("时间2:" + simpleDateFormat.format(new Date()));
        threadPool.shutdown();
    }

    private static void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
