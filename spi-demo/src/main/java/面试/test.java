package 面试;

import org.junit.Test;

/**
 * @description:
 * @author: oywl
 * @time: 2024-05-06 19:56
 */

public class test {
    public test() {
        System.out.println("e");
    }
    private test(Integer a){
        System.out.println("c");
    }
    {
        System.out.println("f");
    }
    static {
        System.out.println("b");
    }
    public static void main(String[] args) {
        test test = new test();
        System.out.println("a");
    }

    public static Integer count = 0;
    public void addCount(int i){
        count=count+i;
    }
    public Integer getCount(){
        return count;
    }

    @Test
    public void t1() {
        int i = 0;
        i = i++; // 先i++,此时i=1,然后i=0，最后 i=0;  后置递增符
        i = ++i;
        i += i;
        System.out.println(i);
    }

    @Test
    public void t2() { //Integer对象的缓存机制 -128-127
        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
        System.out.println(f1 == f2); // true
        System.out.println(f3 == f4);// false
    }

    @Test
    public void t3() {
        test t1 = new test();
        t1.addCount(10);
        test t2 = new test();
        t2.addCount(20);
        System.out.println(t1.getCount()); //都是30
        System.out.println(t2.getCount());
    }

    @Test
    public void t4() {//try catch finally
        int i = 0;
        try {
            int a = 100 / i;
            return;
        } catch (Exception e) {
            i = i + 1;
        } finally {
            i = i + 1;
        }
        System.out.println(i);
    }

    @Test
    public void t5() {
        Integer i = 128; Integer j = 128; System.out.print(i == j);
    }


}
