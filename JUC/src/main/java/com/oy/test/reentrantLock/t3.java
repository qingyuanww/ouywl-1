package com.oy.test.reentrantLock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 锁超时,
 * 获取不到锁后，从阻塞队列移除，不会一直等待
 * 解决死锁问题，比如A,B，两个锁，如果获取A锁后，再获取B锁，一定时间内获取不到B锁，A锁也释放掉
 * @author: oywl
 * @time: 2024-03-17 22:30
 */
@Slf4j
public class t3 {
    @SneakyThrows
    public static void main(String[] args) {
//        锁超时();

//        死锁样例();

        避免死锁问题();
    }

    private static void 避免死锁问题() throws InterruptedException {
        ReentrantLock l1 = new ReentrantLock();
        ReentrantLock l2 = new ReentrantLock();

        Thread t1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                log.debug("t1开始锁l1");
                l1.lock();
                log.debug("t1开始锁l1->success");
                Thread.sleep(1000);
                log.debug("t1开始锁l2");
                if(!l2.tryLock(3,TimeUnit.SECONDS)){
                    log.debug("线程t1获取l2锁失败,开始释放l1的锁");
                    l1.unlock();
                    return;
                }
                log.debug("t1开始锁l2->success");
                Thread.sleep(1000);
                l2.unlock();
                log.debug("t1解锁l2");
                l1.unlock();
                log.debug("t1解锁l1");
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                log.debug("t2开始锁l2");
                l2.lock();
                log.debug("t2开始锁l2->success");
                Thread.sleep(1000);
                log.debug("t2开始锁l1");
                if(!l1.tryLock(3,TimeUnit.SECONDS)){
                    log.debug("线程t2获取l1锁失败,开始释放l2的锁");
                    l2.unlock();
                    return;
                }
                log.debug("t2开始锁l1->success");
                Thread.sleep(1000);
                l1.unlock();
                log.debug("t2解锁l1");
                l2.unlock();
                log.debug("t2解锁l1");
            }
        },"t2");

        t1.start();
        t2.start();

        Thread.sleep(5000);
        log.debug("成功避免死锁问题");
    }

    private static void 死锁样例() {
        ReentrantLock l1 = new ReentrantLock();
        ReentrantLock l2 = new ReentrantLock();

        Thread t1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                log.debug("t1开始锁l1");
                l1.lock();
                log.debug("t1开始锁l1->success");
                Thread.sleep(1000);
                log.debug("t1开始锁l2");
                l2.lock();
                log.debug("t1开始锁l2->success");
                Thread.sleep(1000);
                l2.unlock();
                log.debug("t1解锁l2");
                l1.unlock();
                log.debug("t1解锁l1");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                log.debug("t2开始锁l2");
                l2.lock();
                log.debug("t2开始锁l2->success");
                Thread.sleep(1000);
                log.debug("t2开始锁l1");
                l1.lock();
                log.debug("t2开始锁l1->success");
                Thread.sleep(1000);
                l1.unlock();
                log.debug("t2解锁l1");
                l2.unlock();
                log.debug("t2解锁l1");
            }
        });

        t1.start();
        t2.start();
    }

    private static void 锁超时() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(!lock.tryLock(3, TimeUnit.SECONDS)){
                        log.debug("等待3秒后获取不到锁"); //这里可以用来释放其他的锁，避免死锁问题
                        return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.debug("获取不到锁");
                    return;
                }
                try {
                    log.debug("获取到锁");
                }finally {
                    lock.unlock();
                }
            }
        });
        t1.start();

        lock.lock();
        log.debug("主线程加锁");
        Thread.sleep(5000);
        log.debug("主线程解锁");
        lock.unlock();
    }
}
