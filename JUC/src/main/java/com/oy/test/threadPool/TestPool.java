package com.oy.test.threadPool;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 自定义线程池-测试类
 * @author: oywl
 * @time: 2024-03-18 22:54
 */
@Slf4j(topic = "c.TestPool")
class TestPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2, 1000, TimeUnit.MILLISECONDS, 10);
        for (int i = 0; i < 5; i++) {
            int j = i;
            threadPool.execute(() -> {
                log.debug("{}", j);
            });
        }
    }
}

// 自定义线程池
@Slf4j(topic = "c.ThreadPool")
class ThreadPool{
    //任务队列
    private BlockingQueue<Runnable> taskQueue;

    //线程集合
    private final HashSet<Worker> workers = new HashSet<>();

    //核心线程数
    private int coreSize;

    //任务的超时时间
    private long timeout;

    //时间工具类
    private TimeUnit timeUnit;

    //执行任务
    public void execute(Runnable task){
        //1. 当任务数没有超过coreSize时，直接交给workder对象执行
        // 任务数超过coreSize时，加入任务队列暂存
        synchronized (workers){
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("新增workerThread:{}", task);
                workers.add(worker);
                worker.start();
            } else {
                log.debug("加入任务队列:{}", task);
                taskQueue.put(task);
            }
        }

    }


    //构造方法
    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit,int queueCapcity){
        this.coreSize = coreSize;
        this.timeUnit = timeUnit;
        taskQueue = new BlockingQueue<>(queueCapcity);
        this.timeout = timeout;
    }


    public final class Worker extends Thread{
        //这里的Runnable 接口对象，不是用来创建线程，是用来获取任务的，执行任务的业务逻辑
        // 这里使用的是生产者消费者模式，使用阻塞队列来存储任务
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行任务
            // 当task不为空/ task执行完毕，再从任务队列获取任务并执行
            while (task != null || (task = taskQueue.poll(timeout,timeUnit)) != null) {
                try {
                    log.debug("准备执行lambda表达式传入的业务逻辑...{}",task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (workers){
                log.debug("worker被移除:{}",this);
                workers.remove(this);
            }
        }
    }
}

// 阻塞队列
class BlockingQueue<T>{
    // 一个队列，存放任务
    private Deque<T> deque =new ArrayDeque<>();

    //锁
    private ReentrantLock lock =new ReentrantLock();

    //生产者条件变量
    private Condition producer = lock.newCondition();
    //消费者条件变量
    private Condition consumer = lock.newCondition();
    //容量
    private int capcity;

    public BlockingQueue(int queueCapcity) {
        this.capcity=queueCapcity;
    }



    //消费者 阻塞获取消息
    public T take(){
        lock.lock();
        try {
            while (deque.isEmpty()){
                try {
                    consumer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T peek = deque.removeFirst();
            //唤醒等待的生产者
            producer.signal();
            return peek;
        }finally {
            lock.unlock();
        }

    }

    //生产者 阻塞 存入消息
    public void put(T t){
        lock.lock();
        try {
            while (deque.size()==capcity){
                try {
                    producer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            deque.addLast(t);
            //唤醒等待的消费者
            consumer.signal();
        }finally {
            lock.unlock();
        }
    }

    //带超时的阻塞获取
    public T poll(long timeout, TimeUnit unit){
        lock.lock();
        try{
            long nanos = unit.toNanos(timeout);//将timeout时间统一转化为纳秒
            while(deque.isEmpty()){
                try {
                    //没等到直接返回
                    if(nanos<=0){
                        return null;
                    }
                    //返回的是剩余时间
                    nanos = consumer.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = deque.removeFirst();
            producer.signal();
            return t;
        }finally{
            lock.unlock();
        }
    }

}
