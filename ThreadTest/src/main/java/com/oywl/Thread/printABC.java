package com.oywl.Thread;

/**
 * @description:
 * 对象的两个关于线程的方法
 * 对象.wait()    释放当前对象锁，休眠当前线程
 * 对象.notify()  释放当前对象锁，随机唤醒一个等待该对象锁的线程（注意，操作都是在锁代码块执行之后执行）
 * @author: oywl
 * @time: 2022-6-15 16:29
 */


/**
 *
 */
public class printABC {
    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        MyThreadPrint a1 = new MyThreadPrint("A", c, a);
        MyThreadPrint b1 = new MyThreadPrint("B", a, b);
        MyThreadPrint c1 = new MyThreadPrint("C", b, c);

        new Thread(a1).start();
        Thread.sleep(1000);//确保线程是顺序启动的
        new Thread(b1).start();
        Thread.sleep(1000);
        new Thread(c1).start();



    }
}

class MyThreadPrint implements Runnable{
    private String name;
    private Object prev;
    private Object self;

    public MyThreadPrint(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count =10 ;
        while (count>0){
            synchronized (prev){
                synchronized (self){
                    System.out.println(name+":print->"+count);
                    self.notify();//释放当前对象锁，放出CPU的控制权，随机唤醒一个等待该对象锁的线程，这里是下一个线程的锁
                }
                try {
                    prev.wait();//释放当前对象锁，休眠当前线程，注意是当前线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count--;
        }
    }
}
