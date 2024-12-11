package com.ouywl.面试;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: oywl
 * @time: 2022-9-14 21:54
 */

public class Test {
    public static void main(String[] args) {
//        ShapeCache.loadCache();
//        Circle circle = (Circle)ShapeCache.getShape("1");
//        System.out.println(circle.getType());
//        Rectangle rectangle = (Rectangle)ShapeCache.getShape("2");
//        System.out.println(rectangle.getType());
//        Square square = (Square)ShapeCache.getShape("3");
//        System.out.println(square.getType());
//
//
//        HashMap<Object, Object> map = new HashMap<>();
//        map.put(null,"1");
//        map.put(null,"1");
//        map.put("1",null);
//        System.out.println(map.size());
        int i=0;
        int a[]= {3,6,4,8,5,6};
        do {
            a[i]-=3;
        }while (a[i++]<4);
        for (int j = 0; j < 6; j++) {
            System.out.println(a[j]);
        }
        System.out.println("HH:mm".length());
        String substring = "HH:mm".substring(0, 5);
        System.out.println(substring);
        ArrayList<String> list = new ArrayList<>();
        list.add("abc");
        list.add("def");
        Optional<String> abc = list.stream().filter(it -> it.equals("abc")).collect(Collectors.toList()).stream().findFirst();
        System.out.println(abc.get());
    }
}
