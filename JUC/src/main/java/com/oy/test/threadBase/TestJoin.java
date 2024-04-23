package com.oy.test.threadBase;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * @description: step-1
 *      1.join为阻塞当前线程，等待调用的线程执行结束 from test1()
 *      2.但是多个join，会同时启动，所以有两个线程对象join的话，最终消耗的时间为 其中执行时间最长的线程的执行时间
 *      from test2()
 *      3.有时效的等待 from test3() ,线程执行结束后，也会导致等待结束 from sleep(3000)
 * @author: oywl
 * @time: 2024-03-17 17:13
 */
@Slf4j
public class TestJoin {
    private static int r=0;
    public static void main(String[] args) throws InterruptedException{
//        test1();
//        test2();
        test3();
    }

    private static void test3() throws InterruptedException{
        Thread t1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                sleep(2000);
                r=10;
            }
        });
        long start = System.currentTimeMillis();
        t1.start();
        log.debug("join start");
        t1.join(1500);
//        t1.join(3000);
        log.debug("end,r={},消耗时间为:{}",r,System.currentTimeMillis()-start);



    }
    private static void test2 () throws InterruptedException{
        Thread t1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                log.debug("t1开始");
                sleep(1000);
                r=10;
                log.debug("t1结束");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                log.debug("t2开始");
                sleep(2000);
                r=20;
                log.debug("t2结束");
            }
        });
        t1.start();
        t2.start();

        long start = System.currentTimeMillis();
        log.debug("join bengin");

        t1.join();
        log.debug("t1 join end");

        t2.join();
        log.debug("t2 join end");







        long end = System.currentTimeMillis();


        log.debug("r为:{},结束时间为:{},",r,(end -start));

    }
    private static void test1() throws InterruptedException{
        log.debug("开始");
        Thread t1=new Thread(() -> {
            log.debug("开始");
            try {
                sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("结束");
            r=10;
        },"t1");
        t1.start();

        /**
         * 可以用sleep来实现，但是不大合适，不知道子线程执行多久
         * 用join方法来实现
         */
        t1.join();// 阻塞当前线程，等待调用该方法的线程执行结束
        log.debug("结果为:{}",r);
        log.debug("结束");

    }
}
