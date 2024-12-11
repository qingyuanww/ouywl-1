package com.ouywl;

import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

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

    @Test
    public  void t3(){
        LinkedList<String> list = new LinkedList<>();
        list.push("1");
        list.add("2");
        //
        list.push("3");
        System.out.println(list.peek());
        System.out.println(list);
    }

    @Test
    public void longestCommonSubsequence(){
        //声明一个二维数组，xy都要加一哦，
        String text1="apple";
        String text2="people";

        //1. 获取到字符串长度
        int m = text1.length();
        int n = text2.length();

        //2. 声明二位数组
        int[][] lcs = new int[m+1][n+1];

        //3. 遍历字符串
        for(int i = 1;i <= m;i++){
            char c1 = text1.charAt(i - 1);
            for(int j = 1;j <= n;j++){
                char c2 = text2.charAt(j - 1);
                /**
                 *
                 * 特么的，是从结果倒推的，杀人的心都有了，搞反了，艹  看B站的一个视频
                 * 挨个去掉，去掉，去掉！！
                 * https://www.bilibili.com/video/BV1Yp4y1U7Lf/?spm_id_from=333.337.search-card.all.click&vd_source=a19347460f9cd58bb3a93b40cf4caa78
                 *  切换成局部问题，要考虑两种情况
                 *  1、如果c1=c2 ，那么上一个循环中 上两个数字做的比较所获得的lcs+1就是当前的lcs
                 *  2、如果c1!=c2，那么 比较 i-1,j和i,j-1的最长子序列，大的一方就是最长子序列
                 *
                 */
                if(c1 == c2){
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                }else{
                    lcs[i][j] = Math.max(lcs[i-1][j],lcs[i][j-1]);
                }
            }
        }
        //4. 返回最长公共子序列长度
        System.out.println(lcs[m][n]);

    }

    @Test
    public void ddd(){
        String str="123";
        changet("123");
        System.out.println(str);
        System.out.println(str.hashCode());
    }
    static void changet(String str){
        str= "hello";
        System.out.println(str.hashCode());

        User user = new User();
        user.setUserName("张三");
        changeUser(user);
        System.out.println(user.getUserName());
    }

    static void changeUser(User user){
        User user2=new User();
        user2.setUserName("李四");
        //这里 其实就是赋值一份临时变量嘛
        user=user2;
    }

    @Test
    public void jxxml(){
        String xml="";
    }


    @Test
    public void instantTest(){
        Instant now = Instant.now();
        System.out.println(now);
        System.out.println(new Date().toInstant());

//        Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));
        Instant millis = now.plusMillis(TimeUnit.HOURS.toMillis(8));

        System.out.println(millis.isAfter(now));
        System.out.println(millis.isBefore(now));

        System.out.println(now.until(millis, ChronoUnit.DAYS));
        System.out.println(now.until(millis, ChronoUnit.HOURS));
        System.out.println(now.until(millis, ChronoUnit.MINUTES));
        System.out.println(now.until(millis, ChronoUnit.SECONDS));

    }



    @Test
    public void test666(){
        String k2="http://10.180.4.184:9000/saic-ep/20230213/16db24047fdc4297a286b3465c4fd620.xlsx,,http://10.180.4.184:9000/saic-ep/20230223/725cb6f8c4994ec98f507e14f4696ffb.docx";
        String url="http://10.180.4.184:9000/saic-ep/20230213/16db24047fdc4297a286b3465c4fd620.xlsx";
        System.out.println(url.length());
        System.out.println(k2.trim().contains(url.trim()));
        AtomicBoolean status = new AtomicBoolean(false);

        System.out.println(status.get());
        status.set(false);
        System.out.println(status.get());

        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(test.class.getClassLoader());
        System.out.println(6666);
        long tem=(int)3.9;
        tem%=2;
        System.out.println(tem);
        System.out.println((int)3.9);

        System.out.println(String.valueOf(null));

    }


    @Test
    public  void testRax(){
        Double d=new Double(100000);
        int i=3;
        while (i>0){
            d=ttt(d);
            i--;
            System.out.println("===");
        }
        System.out.println(d);
    }


    public Double ttt(double value){
        return value+value*0.0285;
    }

    @Test
    public void t33() throws ClassNotFoundException {
        String s1="abc";
        String s2=new String("abc");
        HashMap<Object, Object> map = new HashMap<>();
        map.put(s1,2);
        map.put(s2,2);

        Class<?> aClass = Class.forName(User.class.getName());
        System.out.println(map
        );
    }

    @Test
    public void t7(){
        System.out.println(isPalindrome("abcdcba"));
    }
    public static boolean isPalindrome(String str) {
        // 堆栈一
        List<Character> stack1 = new ArrayList<Character>();
        // 堆栈二
        List<Character> stack2 = new ArrayList<Character>();
        // 字符串长度的一半
        int haflen = str.length() / 2;
        System.out.println(haflen);

        for (int i = 0; i < haflen; i++) {
            stack1.add(str.charAt(i));
            stack2.add(str.charAt(str.length()-1-i));
        }

        System.out.println(stack1);
        System.out.println(stack2);
        // 标识符
        boolean bFlag = true;
        // 出栈并比较
        for(int i=haflen-1;i>=0;i--){
            if(stack1.remove(i)!=stack2.remove(i)){
                bFlag=false;
                break;
            }
        }

        // 返回比对结果
        return bFlag;
    }

}
