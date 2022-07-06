package com.ouywl.牛客;

import java.util.HashMap;

/**
 * @description:
 * @author: oywl
 * @time: 2022-7-6 10:58
 */

public class isIsomorphic {

    public static void main(String[] args) {
        isIsomorphic isomorphic = new isIsomorphic();
        System.out.println(isomorphic.isIsomorphicHelper("zpaper").equals(isomorphic.isIsomorphicHelper("wtitle")));
    }

    private String isIsomorphicHelper(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (!hashMap.containsKey(c)) {
                hashMap.put(c,count);
                sb.append(count);
                count++;
            }else {
                sb.append(hashMap.get(c));
            }
        }
        return sb.toString();
    }
    public static void  sout(int[] nums){
        for (int num : nums) {
            System.out.println(num);
        }
    }

}
