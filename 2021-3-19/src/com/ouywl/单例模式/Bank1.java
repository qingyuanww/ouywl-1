package com.ouywl.单例模式;

/**
 * @program: ouywl
 * @description: 饿汉式  线程安全
 * @author: ouyangwl
 * @create: 2021-04-06 19:34
 **/
public class Bank1 {
    //1、私有化其构造器
    private Bank1() {
    }
    //2、声明类的对象并赋值
    private static Bank1 instance = new Bank1();

    //3、提供一个共有的静态方法返回对象
    public static Bank1 getInstance(){
        return instance;
    }



}
