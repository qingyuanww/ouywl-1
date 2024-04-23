package com.oy.test.threadPool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: oywl
 * @time: 2024-03-18 22:54
 */

public class TestPool {

}

// 阻塞队列
class BlockQueue<T>{
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

}
