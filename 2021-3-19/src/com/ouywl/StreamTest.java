package com.ouywl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: oywl
 * @time: 2022-8-9 18:40
 */

public class StreamTest{
    public static final Integer BOOKDING_ORDER_STATUS_90571001 = 90571001;
    public static void main(String[] args) throws ParseException {
        List<User> list = new ArrayList<>();
        //定义三个用户对象
        User user1 = new User();
        user1.setUserName("zs");
        user1.setPassWord("123456");
        User user2 = new User();
        user2.setUserName("zs");
        user2.setPassWord("123456");
        User user3 = new User();
        user3.setUserName("ls");
        user3.setPassWord("12345");
        //添加用户到集合中
        list.add(user1);
        list.add(user2);
        list.add(user3);
        //在集合中查询用户名为huxiansen的集合
        List<User> userList = list.stream().filter(user -> {
            return true;
        }).collect(Collectors.toList());
        //在集合中查询出第一个用户密码为123456的用户
        Optional<User> user = list.stream().filter(userTemp -> "123456".equals(userTemp.getPassWord())).findFirst();
        System.out.println(userList);
        System.out.println(user);
        String s2="t1,t2,t3,t4,t5";
        HashMap map=new HashMap<String,String>(){
            {
               put("t1","1");
               put("t2","2");
               put("t3","3");
               put("t4","4");
               put("t5","5");
            }
        };
        String s1="9859311,9859373,9859374,9859375,9859377,9859378,9859379,9859387,9859388,9859512,9859541,9859542,9859543,9859545,9859547,9859549,9859679,9859680,9859731,9860222,9860236,9860237,9860238,9860245,9860246,9860247,9860252,9860276,9860277,9860278,9860279,9860280,9860291,9860292,9860322,9860326,9860328,9860334,9860338,9860342,9860345,9860346,9860378,9860381,9860389";
        System.out.println(Arrays.stream(s2.split(",")).map(
                id ->map.get(id) == null ? "" : map.get(id).toString()
        ).collect(Collectors.joining(",")));

        String sss="3121"+"dasda"+"fas";
        StringBuilder ss= new StringBuilder();
        ss.append("3231");
        ss.append("dsa");
        ss.append("dasda");

        System.out.println(BOOKDING_ORDER_STATUS_90571001.equals(new Integer(90571001)));
        System.out.println("SURVEY_PM SURVEY_PM_MOBILE".toLowerCase());

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-09-30" + " 00:00:00"));
    }
}


