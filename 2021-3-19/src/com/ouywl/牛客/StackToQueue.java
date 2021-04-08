package com.ouywl.牛客;

import java.util.Stack;
/**
* @Description: 两个栈实现一个队列；基本方法看菜鸟教程的 Java数据结构
* @Param: 
* @return: 
* @Author: ouyangwl
* @Date: 2021-4-5 — 18:50
*/
public class StackToQueue {

    public static void main(String[] args) {
        StackToQueue stackToQueue = new StackToQueue();
        stackToQueue.push(1);
        stackToQueue.push(2);
        stackToQueue.push(3);
        System.out.println(stackToQueue.pop());
        System.out.println(stackToQueue.pop());
        stackToQueue.push(4);
        System.out.println(stackToQueue.pop());
        System.out.println(stackToQueue.pop());
    }

    private static Stack s1 = new Stack();
    private static Stack s2 = new Stack();

    public void push(Object val){
        s1.push(val);
    }

    public Object pop(){
        if(s1.empty()){
            return "队列为空";
        }
        while (!s1.empty()){
            s2.push(s1.pop());
        }

        Object res = s2.pop();

        while (!s2.empty()){
            s1.push(s2.pop());
        }
        return res;
    }

}