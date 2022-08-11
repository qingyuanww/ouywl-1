package com.ouywl.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @author: oywl
 * @time: 2022-7-22 16:19
 */

public class CompletableFutureTest {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("result1");
            return "result1";
        }, pool);
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture("result2");
        CompletableFuture<Object> future = new CompletableFuture<>();
        future.complete("success");
        CompletableFuture<String> cf3 = cf1.thenApply(result1 -> {
            System.out.println("result3");
            return "result3";
        });
        CompletableFuture<String> cf5 = cf2.thenApply(result1 -> {
            System.out.println("result5");
            return "result4";
        });

        CompletableFuture<String> cf4= cf1.thenCombine(cf2, (result1, result2) -> {
            //result1和result2分别为cf1和cf2的结果
            System.out.println("result4");
            return "result4";
        });


        AtomicReference<String> result3 = new AtomicReference<>();
        AtomicReference<String> result4 = new AtomicReference<>();
        AtomicReference<String> result5 = new AtomicReference<>();

        CompletableFuture<Void> cf6 = CompletableFuture.allOf(cf3, cf4, cf5);
        CompletableFuture<String> result = cf6.thenApply(v -> {
            //这里的join并不会阻塞，因为传给thenApply的函数是在CF3、CF4、CF5全部完成时，才会执行 。
            result3.set(cf3.join());
            result4.set(cf4.join());
            result5.set(cf5.join());
            //根据result3、result4、result5组装最终result;
            System.out.println("result6");
            return "result";
        });
    }
}
