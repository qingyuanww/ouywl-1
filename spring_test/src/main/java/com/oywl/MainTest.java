package com.oywl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 方法, 统计list<String>出现的次数
 * 回参map，key为string，value为int（次数）
 * @author: oywl
 * @time: 2022-9-28 20:08
 */

public class MainTest {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        System.out.println(list.stream().filter(s -> {
            return true;
        }).collect(Collectors.toList()));
        String a ="a0364";
        System.out.println(a.substring(1));
    }

    public Map<String, Integer> count(ArrayList<String> list) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (map.containsKey(s)) {
                Integer cs = map.get(s);
                map.put(s, ++cs);
            } else {
                map.put(s, new Integer(1));
            }
        }
        return map;
    }
}
