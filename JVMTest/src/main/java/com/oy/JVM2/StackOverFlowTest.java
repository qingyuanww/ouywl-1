package com.oy.JVM2;

/**
 * @description: -Xss128k (默认1M),一个栈的大小
 * @author: oywl
 * @time: 2022-8-18 13:59
 */

public class StackOverFlowTest {
    static int count =0;
    static void redo(){
        count++;
        redo();
    }
    public static void main(String[] args) {
        try {
            redo();
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println("次数为:"+count);
        }
    }
}
