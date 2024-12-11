package com.oy.test.reentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 公平锁和非公平锁
 * @author: oywl
 * @time: 2024-03-17 22:22
 */
@Slf4j
public class t2 {
    public static void main(String[] args) {
//        公平锁();
        /**
         * 0,1,2,3,4,
         * 0,1,2,3,4
         */
        非公平锁();
        /**
         * 0,0;1,1;2,2;3,3;4,4
         * 为什么非公平锁是这么个玩法呢，因为线程0刚执行完一次，还处于活跃状态，其他线程处于阻塞状态，还需要被唤醒dd
         */
    }

    private static void 公平锁() {
        ReentrantLock lock = new ReentrantLock(true);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 2; j++) {
                        lock.lock();
                        try {
                            log.debug("线程:{},执行{}",Thread.currentThread().getName(),j);
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            lock.unlock();
                        }
                    }
                }
            }).start();
        }
    }
    private static void 非公平锁() {
        ReentrantLock lock = new ReentrantLock(false);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 2; j++) {
                        lock.lock();
                        try {
                            log.debug("线程:{},执行{}",Thread.currentThread().getName(),j);
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            lock.unlock();
                        }
                    }
                }
            }).start();
        }
    }
}
