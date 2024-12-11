package com.oy.JVM;

/**
 * @description:
 * @author: oywl
 * @time: 2022-8-13 15:56
 */

public class Math {
    public static int initData=666;
    public static User user =new User();

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
    public int cpmpute() {
        int a=1;
        int b=2;
        int c=(a+b)*10;
        return c;
    }
//
//    public static void main(String[] args) {
//        Math math = new Math();
//        math.cpmpute();
//    }
}

