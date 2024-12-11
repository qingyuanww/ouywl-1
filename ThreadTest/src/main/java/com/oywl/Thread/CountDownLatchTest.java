package com.oywl.Thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: CountDownLatch 是 所有的其他线程结束后，才开始执行主线程, 常用与计数器 ，英雄联盟五个玩家准备好后才能开始游戏
 * @author: oywl
 * @time: 2022-6-10 10:36
 */

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long timeMillis = System.currentTimeMillis();
        System.out.println(timeMillis);
        int threadCount =5;
        AtomicInteger atomicCounter = new AtomicInteger(threadCount); // 使用AtomicInteger作为计数器
        CountDownLatch downLatch = new CountDownLatch(threadCount);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < downLatch.getCount(); i++) {
            Future future = executor.submit(new MyThread(downLatch,atomicCounter));
            futures.add(future);
        }
        System.out.println("正在等待所有玩家准备好");
        //开始让 当前线程 等待，直到downLatch的计数器为0
        downLatch.await();
        // 获取子线程的执行结果
        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }
        executor.shutdown();//关闭线程池
        System.out.println("准备好,开始游戏");
        System.out.println((System.currentTimeMillis()-timeMillis)/1000.0+"秒");
    }

    private static class MyThread implements Callable {
        private CountDownLatch downLatch;

        private AtomicInteger atomicInteger;

        public MyThread(CountDownLatch downLatch,AtomicInteger atomicInteger) {
            this.downLatch = downLatch;
            this.atomicInteger=atomicInteger;
        }

        @Override
        public Object call() throws Exception {
            Random rand = new Random();
            int randomNum = rand.nextInt((3000 - 1000) + 1) + 1000000;//产生1000到3000之间的随机整数
            try {
                Thread.sleep(randomNum);
                System.out.println(Thread.currentThread().getName()+" 已经准备好了, 所使用的时间为 "+((double)randomNum/1000.0)+"s");
                downLatch.countDown();
                atomicInteger.decrementAndGet();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return randomNum;
        }
    }
}
