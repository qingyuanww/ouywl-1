package com.oywl.stream;

import com.oywl.stream.entity.Person;
import jdk.nashorn.internal.runtime.options.Options;
import org.junit.Test;
import org.junit.internal.Throwables;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: oywl
 * @time: 2022-11-1 14:04
 */

public class StreamTest01 {


    /**
     * 静态方法创建流
     */
    @Test
    public void creatStream(){
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5);
        stream1.forEach(System.out::println);
        System.out.println("==========");
        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 3).limit(5);
        stream2.forEach(System.out::println);
        System.out.println("==========");
        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
        stream3.forEach(System.out::println);
    }

    /**
     * 遍历，匹配,是否存在
     */
    @Test
    public void test01(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7,8);
        list.stream().forEach(System.out::println);
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        Optional<Integer> finAny = list.stream().filter(x -> x > 6).findAny();
        //是否包含符合特殊条件元素
        boolean match = list.stream().anyMatch(x -> x > 6);
        System.out.println("匹配到的第一个值:"+findFirst.get());
        System.out.println("匹配到的任意值:"+finAny.get());
        System.out.println("是否存在大于6的值:"+match);
    }

    /**
     * 将符合条件的数据，提取到新的流中的操作
     */
    @Test
    public void test02(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7,8,9);
        list.stream().filter(x->x>7).forEach(System.out::println);
    }
    /**
     * 筛选出员工中工资高于8000的数据，并组成新的集合
     */
    @Test
    public void test03(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        List<String> collect = personList.stream().filter(x -> x.getSalary() > 8000).map(Person::getName).collect(Collectors.toList());
        System.out.println("薪资高于8000美元的员工:"+collect);
    }


    /**
     * 以下为聚合函数:max,min
     */
    @Test
    public void testMaxString(){
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        System.out.println(max.get());
    }

    @Test
    public void testMaxInteger(){
        List<Integer> list = Arrays.asList(7, 6, 9, 4, 11, 5);
        Optional<Integer> max = list.stream().max(Integer::max);
        //自定义排序规则,这两个是一样的哦
        Optional<Integer> max1 = list.stream().max((o1, o2) -> o2 - o1);
        Optional<Integer> max2 = list.stream().min((o1, o2) -> o1 - o2);
        System.out.println("自然排序的最大值:"+max.get());
        System.out.println("自定义排序的最大值:"+max1.get());
        System.out.println(max2.get());
        System.out.println(max1.get().equals(max2.get()));
    }

    /**
     * 获取员工薪资最高的人
     */
    @Test
    public void testMaxSalary(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));
        Optional<Person> max = personList.stream().max(Comparator.comparingInt(Person::getSalary))  ;
        System.out.println("员工中薪资最高的人:"+max.get().getSalary());
    }

    /**
     * 以下为映射 map,flatMap
     *
     * 英文数组全部改为大写，数字数组全部+6
     */
    @Test
    public void testMap(){
        String[] strArr = { "abcd", "bcdd", "defde", "fTr" };

        List<String> upperCase = Arrays.asList(strArr).stream().map(x -> x.toUpperCase()).collect(Collectors.toList());
//        List<String> upperCase = Arrays.asList(strArr).stream().map(String::toUpperCase).collect(Collectors.toList());
        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> integerList = intList.stream().map(x -> x + 5).collect(Collectors.toList());
        System.out.println("大写数组:"+upperCase);
        System.out.println("数字数组:"+integerList);
    }

    /**
     * 将员工薪资全部加10000
     */
    @Test
    public void testMap2(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        List<Person> personList1 = personList.stream().map(person -> {
            //这里是改变了原来的集合，如果不想改变原来的集合，那么在这里new Person()返回
            person.setSalary(person.getSalary() + 10000);
            return person;
        }).collect(Collectors.toList());

        System.out.println("改动前:"+personList.get(0).getName()+":"+personList.get(0).getSalary());
        System.out.println("改动后:"+personList1.get(0).getName()+":"+personList1.get(0).getSalary());
    }

    @Test
    public void testFlagMap(){
        List<String> list = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = list.stream().flatMap(s -> {
            // 将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        }).collect(Collectors.toList());

        System.out.println("处理前的集合：" + list);
        System.out.println("处理后的集合：" + listNew);
    }

    @Test
    public void testMatch(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        //||
        System.out.println(personList.stream().anyMatch(p -> p.getAge() > 23));
        //&&
        System.out.println(personList.stream().allMatch(p -> p.getAge() > 23));
    }

    /**
     * 归约，求和和乘积等操作
     * 把一个流缩减成一个值
     *
     * 求Integer集合的元素之和，乘积，和最大值
     */
    @Test
    public void testReduce(){
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
        Optional<Integer> sum1 = list.stream().reduce((x, y) -> x + y);
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        Integer sum3 = list.stream().reduce(0,Integer::sum);
        System.out.println(sum1.get());
        System.out.println(sum2.get());
        System.out.println(sum3);
        System.out.println(list.stream().reduce(Integer::max).get());
        System.out.println(list.stream().reduce(Integer::min).get());
    }

    /**
     * join 拼接
     */
    @Test
    public void testJoin(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        String names = personList.stream().map(Person::getName).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);
        List<String> list = Arrays.asList("A", "B", "C");
        String string = list.stream().collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + string);
    }

    /**
     * 排序,各种排序问题
     */
    @Test
    public void testSort(){
        String orderList="{orderNumber=20221104000002, superId=1000010000443719, phone=16621335586, fullName=先收费后免费, orderType=Service, orderStatus=301, vechileType=, license=, created=11/04/2022 09:19:17, vin=, update=11/04/2022 13:22:16, vehicleOrderNumber=SO20221104000011, relatedServiceNum=20221104000026, benefitList=[], totalAmount=0.00, totalBenefitsAmount=, payAmount=0.00, itemList={itemId=1588340071216857089, productid=1-5UPDX, productName=私桩勘测安装, serviceType=1, quantity=1, amount=0.00, points=0.00, srNumber=SZIMS052221100016, srAmonut=0.00, srStatus=进行中, srCancelTime=, srComments=, distributorId=100002056, distributorName=智己销售公司JD, benefitsCode=, writeOffCode=, benefitsCardSku=, benefitsAmount=, benefitsStatus=, benefitsTime=, lineNumber=1, discountAmount=, reductionAmount=, onlineAmount=, offlineAmount=, offlinePaymentTime=, paymentNumber=}, paymentList=[]}";
    }

    @Test
    public void testOption(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(-2);
        Optional<ArrayList<Integer>> integers1 = Optional.ofNullable(list).filter(integers -> {
            System.out.println(integers.get(0));
            return true;
        });
        System.out.println(integers1.get());
        System.out.println(Optional.ofNullable(null).orElse("000000000000"));
    }
    //商品类型
    private static final Map<String, String> GOODS_TYPE = new HashMap<>();//使用类型
    //是否
    private static final Map<String, String> IS_YES = new HashMap<>();//是否开票

    static {
        GOODS_TYPE.put("配件", "10301001");
        GOODS_TYPE.put("精品", "10301002");
        GOODS_TYPE.put("礼品", "10301003");
        IS_YES.put("是", "10041001");
        IS_YES.put("否", "10041002");
    }
    @Test
    public void testStringUtils(){
        Integer val =null;
        boolean empty = org.springframework.util.StringUtils.isEmpty(val);
        System.out.println(empty);
        HashMap add = new HashMap<>();
        String s1="礼品1";
        add.put("ss",Optional.ofNullable(s1).map(GOODS_TYPE::get).orElse(""));
        System.out.println(add.get("ss"));
    }
    @Test
    public void testFind(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
//        System.out.println(personList.stream().filter(p -> "male".equals(p.getSex())).findAny().orElse(new Person()).getName());
        System.out.println(personList.stream().filter(p -> "male".equals(p.getSex())).findAny().orElseGet(Person::new).getName());
        System.out.println(personList.stream().filter(p -> "male".equals(p.getSex())).findFirst().orElseGet(Person::new).getName());
    }

    /**
     * 收集器和分组
     */
    @Test
    public void testCountAndGroup(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        System.out.println(personList.stream().count());

        System.out.println(personList.stream().min(Comparator.comparing(Person::getAge)).get().getName());

        System.out.println(personList.stream().max(Comparator.comparing(Person::getAge)).get().getName());

        //复杂结果返回
        IntSummaryStatistics collect = personList.stream().collect(Collectors.summarizingInt(Person::getAge));
        System.out.println(collect);

        //分组

        Map<String, List<Person>> collect1 = personList.stream().collect(Collectors.groupingBy(Person::getSex));

        //先根据 性别分组，然后根据年龄分组
        Map<String, Map<Integer, List<Person>>> collect2 = personList.stream().collect(Collectors.groupingBy(
                Person::getSex, Collectors.groupingBy(Person::getAge)
        ));
        System.out.println(collect1);
        System.out.println(collect2);

    }




}
