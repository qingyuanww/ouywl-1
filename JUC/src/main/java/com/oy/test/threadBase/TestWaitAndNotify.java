package com.oy.test.threadBase;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: wait，notify,notifyAll
 *      wait分为无参和带参数的，一般用有参方法，不然无限制等待
 *      无限制等待，和有时限等待
 * @author: oywl
 * @time: 2024-03-17 18:27
 */
@Slf4j
public class TestWaitAndNotify {
    private final static Object lock = new Object();

    @SneakyThrows
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (lock){
                    log.debug("执行...");
                    lock.wait();
//                    lock.wait(1000);
                    log.debug("结束...");
                }
            }
        },"t1").start();
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {

                synchronized (lock){
                    log.debug("执行...");
                    lock.wait();
                    log.debug("结束...");
                }
            }
        },"t2").start();

        Thread.sleep(5000);

        synchronized (lock){
            lock.notify();
//            lock.notifyAll();
        }
    }
}
