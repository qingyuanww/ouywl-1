package com.ouywl.牛客;

/**
 * @description: 从a到c 汉诺塔
 * @author: oywl
 * @time: 2022-7-6 15:53
 */

public class Hnoi {
    static  int num =0;
    public static void main(String[] args) {
        hanoi(4,"A","B","C");
    }
    public static void print(int len, String x ,String y){
        System.out.println("第"+ (++num)+"次移动，把"+len+"号从"+x+"移动到"+y);
    }
    //A到C，B为辅助柱
    public static void hanoi(int n,String A,String B,String C ){
        if(n==1){
            Hnoi.print(n,A,C);
        }else {
            hanoi(n-1,A,C,B); //A到B
            print(n,A,C);//将A最低下的移动到C
            hanoi(n-1,B,A,C);//B到C
        }

        /**
         * 青蛙如果跳2,两种方法，跳1，一种方法
         * 跳3，第一跳1，剩下两级台阶有两种方法1*2=2
         * 第二条2，那么剩下一级台阶，有1中办发 1+1=1
         * 那么跳3 =2+1=3
         *
         * 跳4呢，跳4 ，= 跳3 +跳2 = 3 +2=5
         *
         * 汉诺塔
         *
         * 4个盘子从a到c，那么a到c之前的肯定有一个状态，a存在盘4，b存在盘123，c无盘， 因为按照规则，盘子必须从从低到高
         * 然后4盘从a到c，然后123盘从b到c，
         * 如何将123盘从b到c呢？
         * 中间肯定有一个状态,a盘上有12，b盘上有3，然后将3从b盘到c，然后将12从a到c；
         * 如何将12盘从a到c呢？
         * 借助b盘
         *
         * a->c b
         * 那么fn= f(n-1) a->b + a n ->c + f(n-1) b->c
         *
         * a->b c
         * f(n-1) =f(n-2) a-c + a (n-1)->b + f(n-2) c->b
         *
         * b-c a
         * f(n-1)= f(n-2) b-a + b (n-1)->c + f(n-2) a->c
         */
    }
    void t(int n,String a,String b,String c){
        if(n==1){
            move(a,c);
        }else {
            t(n-1,a,c,b); //a 到 b
            move(a,c);
            t(n-1,b,a,c); //b 到 c
        }
    }
    void move(String x,String y){

    }
}
