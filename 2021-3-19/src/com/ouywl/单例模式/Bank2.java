package com.ouywl.单例模式;

/**
 * @program: ouywl
 * @description: 懒汉式 类加载就创建了对象，线程不安全
 * @author: ouyangwl
 * @create: 2021-04-06 19:36
 **/
public class Bank2 {
    //1、私有化其构造器
    private Bank2(){
    }
    //2、声明一个类的对象
    private static Bank2 instance=null;

    //3、提供一个共有的静态方法返回对象
    public static Bank2 getInstance(){
        if(instance==null){
            instance=new Bank2();
        }
        return instance;
    }
    //4、解决线程安全的方法一：简单加锁
    public synchronized static Bank2 getInstance1(){
        if(instance==null){
            instance=new Bank2();
        }
        return instance;
    }

    //4、解决线程安全的方法一：双重检查锁定
    public static Bank2 getInstance2(){
        if(instance==null){
            synchronized (Bank2.class){
                if(instance==null){
                    instance=new Bank2();
                }
            }
        }
        return instance;
    }

    //5、解决线程安全的方法三:静态内部类的方式
    private static class Bank3{
        private static final Bank2 bank2=new  Bank2();
    }
    public static Bank2 getInstance3(){
        return Bank3.bank2;
    }

    //6、枚举
    private enum Singleton{
        INSTANCE;


        private final Bank2 instance;

        Singleton(){
            instance = new Bank2();
        }

        private Bank2 getInstance(){
            return instance;
        }
    }

}

