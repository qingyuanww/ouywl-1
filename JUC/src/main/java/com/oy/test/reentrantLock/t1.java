package com.oy.test.reentrantLock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 可重入 一同synchronized
 *      可中断
 * @author: oywl
 * @time: 2024-03-17 21:45
 */
@Slf4j
public class t1 {

    static ReentrantLock lock=new ReentrantLock();

    @SneakyThrows
    public static void main(String[] args) {
        可重入();
        可打断();
    }

    private static void 可打断() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.debug("尝试获取锁");
                    lock.lockInterruptibly();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.debug("t1被打断");
                    return;
                }
                try {
                    log.debug("t1线程获取了锁");
                } finally {
                    lock.unlock();
                }
            }
        }, "T1");

        //主线程获取锁,这里下面两行代码的顺序无所谓（不影响结果），因为创建线程+启动的时间，肯定大于主线程执行一行代码的时间
        t1.start();
        lock.lock();
        Thread.sleep(1000);
        log.debug("其他线程打断inerrupt...t1");
        t1.interrupt();
    }

    private static void 可重入() {
        lock.lock();
        try {
            log.debug("main do");
            m1();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    static void m1(){
        lock.lock();
        try {
            log.debug("m1 do");
            m2();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    static void m2(){
        lock.lock();
        try {
            log.debug("m2 do");
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

}
