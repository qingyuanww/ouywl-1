package com.oywl.Thread;

/**
 * @description:
 * @author: oywl
 * @time: 2022-6-9 15:38
 */

public class ThreadServiceImpl {
    public String doSomething1 () throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("我是doSomething1");
        return "doSomething1";
    }
    public String doSomething2 () throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("我是doSomething2");
        return "doSomething2";
    }
    public String doSomethingElse (long time) throws InterruptedException {
        Thread.sleep(time);
        System.out.println("我是doSomethingElse");
        return "doSomethingElse";
    }
}
