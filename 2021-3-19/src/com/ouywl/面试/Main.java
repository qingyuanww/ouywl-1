package com.ouywl.面试;

/**
 * @description: 1.写一个方法要求把字符串（该字符串只包含一个‘-’和字母若干）的字符倒序输出，但是符号‘-’保持原位置。
 * （如：入参为“abc-defg”，方法输出为“gfe-dcba”）
 * @author: oywl
 * @time: 2022-9-14 19:55
 */

public class Main {
    public static void main(String[] args) {
        System.out.println(revert("abc-defg"));

    }

    public static String revert(String s1){
        int indexOf = s1.indexOf("-");
        StringBuffer reverse = new StringBuffer(s1.replace("-", "")).reverse();
        StringBuffer buffer = new StringBuffer(reverse.substring(0, indexOf)).append("-").append(reverse.substring(indexOf, reverse.length()));
        return buffer.toString();
    }
}
