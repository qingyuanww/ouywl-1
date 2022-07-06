package com.oywl.Thread;

import ch.qos.logback.core.util.TimeUtil;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @description: https://blog.csdn.net/wangdong5678999/article/details/81837387
 * @author: oywl
 * @time: 2022-6-9 15:35
 */

public class mainTest {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public static void main(String[] args) {
        SimpleFuture();
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
