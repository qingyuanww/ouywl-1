package com.ouywl.牛客;

/**
 * @program: ouywl
 * @description:【Java】给定一个包含大写英文字母和数字的句子，找出这个句子所包含的最大的十六进制整数
 * @author: ouyangwl
 * @create: 2021-04-05 18:51
 **/
import java.util.*;


public class Solution {
    /**
     *
     * @param s string字符串
     * @return int整型
     */
    public int solve (String s) {
        // write code here
        int begin = 0;
        int end = 0;
        int result = 0;
        for(int i = 0; end < s.length(); i++) {
            if ( (Character.isDigit(s.charAt(i))) || (s.charAt(i) >= 'A' && s.charAt(i) <= 'F') ) {
                end++;
                result = Math.max(Integer.parseInt(s.substring(begin, end),16), result);
            } else {
                begin = i+1;
                end = begin;
            }
        }
        return result;
    }
}
