package com.oywl;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:merge 入参两个List<int> </int> 有序，升序;数组做一个合并，有序
 * @author: oywl
 * @time: 2022-9-28 20:13
 */

public class MainTestTwo {
    public static void main(String[] args) {
        ArrayList<Integer> i1 = new ArrayList<>();
        i1.add(1);
        i1.add(6);
        i1.add(9);
        ArrayList<Integer> i2 = new ArrayList<>();
        i2.add(4);
        i2.add(5);
        i2.add(6);

        List<Integer> integers = merge(i2, i1);
        for (Integer integer : integers) {
            System.out.println(integer);
        }

        HashMap<Object, Object> finalServiceAdvisorMap = new HashMap<>();
        finalServiceAdvisorMap.put("owner_code","我是聂打野");
        List<Map> excelData = new ArrayList<>();
        HashMap<String, String> add = new HashMap<>();
        add.put("owner_code","owner_code");
        excelData.add(add);
        System.out.println(excelData);

        excelData.stream().map(m -> {
            Arrays.asList("owner_code").forEach(x ->
                    m.put(x, finalServiceAdvisorMap.get(m.get(x))));
            return m;
        });
        System.out.println(excelData);
        Integer integer = new Integer(1);
        System.out.println(integer.doubleValue());

    }

    public static List<Integer> merge(List<Integer> list1, List<Integer> list2) {

        List<Integer> list = new ArrayList<Integer>();
        int size1 = list1.size();
        int size2 = list2.size();
        int i = 0, j = 0, k = 0;
        while (i < size1 && j < size2) {
            if (list1.get(i) <= list2.get(j)) {
                list.add(k++, list1.get(i++));
            } else {
                list.add(k++, list2.get(j++));
            }
        }
        while (i < size1) {
            list.add(k++, list1.get(i++));
        }
        while (j < size2) {
            list.add(k++, list2.get(j++));
        }
        return list;
    }
}
