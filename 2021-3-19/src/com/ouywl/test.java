package com.ouywl;

import org.junit.Test;

/**
 * @program: ouywl
 * @description: 复习昨天的知识点
 * @author: ouyangwl
 * @create: 2021-03-19 07:28
 **/
public class test {
    public static void main(String[] args) {
        System.out.println(5/2);
    }
    @Test
    public  void test01(){//复习杨辉三角 每行首末元素为1，对于其他元素arr[i][j]=arr[i-1][j-1]+arr[i-1][j]
        int[][] arr = new int[10][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new int[i+1];//为每行数组定义大小
            arr[i][0]=arr[i][i]=1;
            if(i>1){
                for (int j = 1; j < arr[i].length-1; j++) {//去除首末元素，不然下标会越界0-1=-1；
                    arr[i][j]=arr[i-1][j-1]+arr[i-1][j];
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]+"\t");
            }
            System.out.println();
        }
    }
    @Test
    public void test02(){//二分查找。前提，查找的数组必须有序
        int[] arr = new int[]{-123,-95,-31,0,21,44};
        int a1=22;//要查找的元素
        int head=0;//首索引
        int end=arr.length-1;//末位索引
        boolean ifExist=true;//是否查找到
        while(head<=end){
            int middle=(head+end)/2;//中间，java中5/2=2
            if(a1==arr[middle]){
                ifExist=false;
                System.out.println("找到元素，索引为："+middle);
                break;//结束查找
            }else if(a1<arr[middle]){
                end=middle-1;//末位数 改为中间值 前一个
            }else if(a1>arr[middle]){
                head=middle+1;//首位数 改为中间值后一个
            }
        }
        if(ifExist){
            System.out.println("未找到元素");
        }

    }
}
