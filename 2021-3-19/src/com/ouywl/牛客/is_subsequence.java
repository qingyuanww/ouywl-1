package com.ouywl.牛客;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @description: 是否子序列 字符串是否为另外 字符串的子序列
 * 吸收：不需要操心吮吸问题，直接用双指针指向移动
 * 使用双指针！！
 * @author: oywl https://leetcode.cn/problems/is-subsequence/
 * @time: 2022-7-6 14:19
 */

public class is_subsequence {

    public static void main(String[] args) {
        System.out.println("12345".substring(0, 1));
    }

    public boolean isSubsequence(String s, String t) {
        int n = s.length();
        int m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i==n; //这里判断s全部顺序存在于t中，顺序存在，不需要操心顺序的问题
    }
}
