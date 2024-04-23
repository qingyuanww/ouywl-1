package com.oy.test.reentrantLock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: condition 条件变量的使用，多个休息室waitSet
 * @author: oywl  也可以用来顺序打印A->B->C
 * @time: 2024-03-17 23:44
 */
@Slf4j
public class t4 {
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;
    static ReentrantLock ROOM = new ReentrantLock();
    // 等待烟的休息室
    static Condition waitCigaretteSet = ROOM.newCondition();
    // 等外卖的休息室
    static Condition waitTakeoutSet = ROOM.newCondition();

    @SneakyThrows
    public static void main(String[] args) {

        new Thread(() -> {
            ROOM.lock();
            try {
                while (!hasCigarette) {
                    log.debug("没烟，先歇会！");
                    try {
                        //是await方法，别写成了wait
                        waitCigaretteSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("可以开始干活了");
            } finally {
                ROOM.unlock();
            }
        }, "小南").start();
        new Thread(() -> {
            ROOM.lock();
            try {
                while (!hasTakeout) {
                    log.debug("没外卖，先歇会");
                    try {
                        waitTakeoutSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("外卖送到了,开始干活");
            } finally {
                ROOM.unlock();
            }
        },"小女").start();



        Thread.sleep(1000);

        new Thread(() -> {
            ROOM.lock();
            try {
                hasTakeout = true;
                waitTakeoutSet.signal();
            } finally {
                ROOM.unlock();
            }
        }, "送外卖的").start();
        Thread.sleep(1000);

        new Thread(() -> {
            ROOM.lock();
            try {
                hasCigarette = true;
                waitCigaretteSet.signal();
            } finally {
                ROOM.unlock();
            }
        }, "送烟的").start();

    }
}
