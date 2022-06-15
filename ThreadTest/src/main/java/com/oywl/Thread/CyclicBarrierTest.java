package com.oywl.Thread;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * @description: CyclicBarrier 多个线程等待同步，然后再开始下一步操作。比如公司团建，必须所有任（线程）结束第一个项目，才能开始第二个项目
 * 完成第一步后，必须等待其他线程也完成第一步，才能开始下一步
 * @author: oywl
 * @time: 2022-6-10 10:58
 */

public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(5);
        for (int i = 0; i < barrier.getParties(); i++) {
            new Thread(new MyThread(barrier)).start();
        }
        System.out.println("调用线程结束");
    }
    private static class MyThread implements Runnable{
        private CyclicBarrier cyclicBarrier;

        public MyThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                Random rand = new Random();
                int randomNum = rand.nextInt((3000 - 1000) + 1) + 1000;//产生1000到3000之间的随机整数
                Thread.sleep(randomNum);
                System.out.println(Thread.currentThread().getName() + ", 通过了第"+(i+1)+"个障碍物, 使用了 "+((double)randomNum/1000)+"s");
                this.cyclicBarrier.await();
                System.out.println("等待:"+i);
            }


//            Random rand = new Random();
//            int randomNum = rand.nextInt((3000 - 1000) + 1) + 1000;//产生1000到3000之间的随机整数
//            Thread.sleep(randomNum);
//            System.out.println(Thread.currentThread().getName() + ", 通过了第"+(1)+"个障碍物, 使用了 "+((double)randomNum/1000)+"s");
//            this.cyclicBarrier.await();
//            System.out.println("等待:"+1);
//
//            Thread.sleep(randomNum);
//            System.out.println(Thread.currentThread().getName() + ", 通过了第"+(2)+"个障碍物, 使用了 "+((double)randomNum/1000)+"s");
//            this.cyclicBarrier.await();
//            System.out.println("等待:"+2);
        }
    }
}
