package com.oywl.Thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @description: CountDownLatch 是 所有的其他线程结束后，才开始执行主线程, 常用与计数器 ，英雄联盟五个玩家准备好后才能开始游戏
 * @author: oywl
 * @time: 2022-6-10 10:36
 */

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(5);

        for (int i = 0; i < downLatch.getCount(); i++) {
            new Thread(new MyThread(downLatch)).start();
        }
        System.out.println("正在等待所有玩家准备好");
        //开始让 当前线程 等待，直到downLatch的计数器为0
        downLatch.await();
        System.out.println("准备好,开始游戏");
    }

    private static class MyThread implements Runnable{
        private CountDownLatch downLatch;

        public MyThread(CountDownLatch downLatch) {
            this.downLatch = downLatch;
        }

        @Override
        public void run() {
            try {
                Random rand = new Random();
                int randomNum = rand.nextInt((3000 - 1000) + 1) + 1000;//产生1000到3000之间的随机整数
                Thread.sleep(randomNum);
                System.out.println(Thread.currentThread().getName()+" 已经准备好了, 所使用的时间为 "+((double)randomNum/1000)+"s");
                downLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
