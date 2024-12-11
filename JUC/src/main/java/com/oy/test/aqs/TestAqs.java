package com.oy.test.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: oywl
 * @time: 2024-05-05 9:33
 */
@Slf4j(topic = "cn.AqsTest")
public class TestAqs {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(() -> {
            lock.lock();
            try {
                log.debug("locking....");
//                Thread.sleep(1000);
                LockSupport.park();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                log.debug("unlocking.....");
                lock.unlock();
            }

        }, "t1").start();
        new Thread(() -> {
            lock.lock();
            try {
                log.debug("locking....");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.debug("unlocking.....");
                lock.unlock();
            }

        }, "t2").start();
    }
}

//自定义锁 同步组件--> 简单的不可重入锁
class MyLock implements Lock {

    class MySync extends AbstractQueuedSynchronizer {
        //获取锁 和 尝试释放锁
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                //设置持有者为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            //写屏障之前的操作都会同步到主存中去
            setState(0);
            return true;
        }

        //是否持有 锁
        @Override
        protected boolean isHeldExclusively() {
            return super.isHeldExclusively();
        }

        //条件变量
        public Condition newCondition() {
            return new ConditionObject();
        }
    }

    private MySync sync = new MySync();

    @Override //加锁 （不成功，进入等待队列等待）
    public void lock() {
        this.sync.acquire(1);
    }

    @Override //加锁，可打断
    public void lockInterruptibly() throws InterruptedException {
        this.sync.acquireInterruptibly(1);
    }

    @Override //尝试加锁
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override//释放锁
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return this.sync.newCondition();
    }


}


