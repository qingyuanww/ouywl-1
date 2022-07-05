package com.ouywl.牛客;

/**
 * @description: 判定字符是否唯一  ,基本数据类型是用== 比较值是否相等
 * @author: oywl https://leetcode.cn/problems/is-unique-lcci/
 * @time: 2022-7-5 15:24
 */

public class is_unique_lcci {
    public static void main(String[] args) {
        is_unique_lcci lcci = new is_unique_lcci();
        System.out.println(lcci.isUnique("lete"));
    }
    public boolean isUnique(String astr) {
        int length = astr.length();
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length ; j++) {
                if(astr.charAt(i)==astr.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }
}
