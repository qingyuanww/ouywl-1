package com.oy.test.threadPool;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: ReentrantLock的简单使用 await和singnalAll
 * @author: oywl
 * @time: 2024-03-18 23:39
 */
@Slf4j
public class SpuriousWakeup{
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition hasApple = lock.newCondition();
    private static volatile int nApple;
    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();
            try {
                log.debug("萧炎抢锁成功");
                if (nApple == 0) {
                    log.debug("没苹果，我先休息会儿，苹果来了我再醒...");
                    hasApple.await();
                }
                nApple -= 1;
                log.debug("哇，苹果来了，我吃掉了...");
                log.debug("现在苹果还有 " + nApple + " 个...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.debug("萧炎解锁");
                lock.unlock();
            }
        }, "萧炎").start();
        new Thread(() -> {
            lock.lock();
            try {
                log.debug("唐三抢锁成功");
                if (nApple == 0) {
                    log.debug("没苹果，我先休息会儿，苹果来了我再醒...");
                    hasApple.await();
                }
                nApple -= 1;
                log.debug("哇，苹果来了，我吃掉了...");
                log.debug("现在苹果还有 " + nApple + " 个...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.debug("唐三解锁");
                lock.unlock();
            }
        }, "唐三").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            lock.lock();
            try {
                log.debug("我来送苹果了，但只有一个哦...");
                nApple = 1;
                hasApple.signalAll();
            } finally {
                lock.unlock();
            }
        }, "萧薰儿").start();
    }
}


