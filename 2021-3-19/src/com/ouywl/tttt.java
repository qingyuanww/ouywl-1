package com.ouywl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: oywl
 * @time: 2023-1-31 21:01
 */

public class tttt {
    private static final String BASIC = "123456789qwertyuiopasdfghjklzxcvbnm";

    public static void main(String[] args) {
        char[] basicArray = BASIC.toCharArray();
        Random random = new Random();
        char[] result = new char[8];
        for(int i = 0;i<result.length;i++){
            int index = random.nextInt(100) % (basicArray.length);
            result[i] = basicArray[index];
        }
        System.out.println(new String(result));
        System.out.println(new Date());
        System.out.println(new Date().toLocaleString().length());
        System.out.println(LocalDateTime.now());

        ArrayList<Map> maps = new ArrayList<>();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("name","张三");

        HashMap<Object, Object> map2 = new HashMap<>();
        map2.put("name","李四");

        maps.add(map);
        maps.add(map2);

        String ids="  tt_dms_basedata\n" +
                "  tm_dms_defeat_reason\n" +
                "  tm_dms_potential_task\n" +
                "  tm_dms_tracking_task\n" +
                "  tm_dms_fail_model";
        System.out.println(ids.toUpperCase());
        System.out.println(Arrays.stream("A001,A002,A003".toString().split(",")).map(s -> s = "\"" + s + "\"").map(String::valueOf).collect(Collectors.joining(",")));

        System.out.println(Double.parseDouble("-0.3")>0);

    }

}
