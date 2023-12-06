package com.ouywl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleTest {

    private AtomicInteger count= new AtomicInteger();

    private SimpleTest(){};

    private  static SimpleTest instance=new SimpleTest();

    public static SimpleTest getInstance() {
        return instance;
    }

    public void increment(){
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleTest instance = SimpleTest.instance;


        ArrayList<Thread> threads = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    instance.increment();
                }
                countDownLatch.countDown();
            });
            threads.add(thread);
        }


        for (Thread thread : threads) {
            thread.start();
        }
        countDownLatch.await();
//        Thread.currentThread().join();
        System.out.println(instance.getCount());

    }
}
