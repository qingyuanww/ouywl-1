package com.ouywl.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: oywl
 * @time: 2024-05-14 11:04
 */

public class FutureTest02 {
    public static void main(String[] args) {
        FutureTest02 test02 = new FutureTest02();
        System.out.println(test02.get());

        System.out.println(System.getProperty("java.home"));
    }

    public Integer get() {
        //初始值为0 为了保证多线程情况下的累加原子性 就是保证数据不累加错误
        //使用AtomicInteger
        AtomicInteger sum = new AtomicInteger(0);
        //并发调用三个接口
        CompletableFuture<Integer> a = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture<Integer> b = CompletableFuture.supplyAsync(() -> 3);
        CompletableFuture<Integer> c = CompletableFuture.supplyAsync(() -> 4);
        //阻塞3个线程 执行完毕才往下走  join()是阻塞
        CompletableFuture.allOf(a, b, c).join();
        try {
            System.out.println(a.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //把3个线程的结果 加起来
        a.thenAccept(sum::addAndGet);
        b.thenAccept(sum::addAndGet);
        c.thenAccept(sum::addAndGet);
        //最后返回累加的结果
        return sum.get();
    }
}
