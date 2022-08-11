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
    public boolean isSubsequence2(String s, String t){
        //考虑到  对第一个字符的处理 ，在t 之前一个空字符
        t=' '+t;

        //对t长字符串 做预处理
        int[][] dp = new int[t.length()][26];//存储每一个位置上  a--z的下一个字符出现的位置
        for (char c = 'a'; c <= 'z'; c++) {//依次对每个字符作处理
            int nextPos = -1;//表示接下来不会在出现该字符

            for (int i = t.length() - 1; i >= 0; i--) {//从最后一位开始处理
                dp[i][c - 'a'] = nextPos;//dp[i][c-'a']  加上外层循环  就是对每一个位置的a---z字符的处理了
                if (t.charAt(i) == c) {//表示当前位置有该字符  那么指向下一个该字符出现的位置就要被更新  为i
                    nextPos = i;
                }
            }
        }

        //数据的利用 ，开始匹配
        int index=0;
        for (char c:s.toCharArray()){
            index=dp[index][c-'a'];//因为加了' '，所以之后在处理第一个字符的时候  如果是在第一行，就会去第一行，不影响之后字符的判断
            if(index==-1){
                return false;
            }
        }
        return true;
    }
}
