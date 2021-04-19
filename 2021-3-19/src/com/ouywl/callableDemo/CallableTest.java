package com.ouywl.callableDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: ouywl
 * @description:callable如何获取返回值;
 * 1、不同点，new 一个callable的实现类对象参数传值给FutureTask，再把futureTask参数传值给Thread类
 * ，因为FutureTask实现了RunnableFuture，它继承了Runnable接口
 * @author: ouyangwl
 * @create: 2021-04-19 17:07
 **/
class MyThread implements Callable<String>{
    private int ticket=10;
    @Override
    public String call() throws Exception {
        while (ticket>0){
            System.out.println(Thread.currentThread().getName()+"卖票："+ticket);
            ticket--;
        }
        for (int i = 0; i < 10; i++) {
        }
        return "卖票完毕";
    }
}
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask).start();
        System.out.println("线程返回数据为："+futureTask.get());
    }

}
