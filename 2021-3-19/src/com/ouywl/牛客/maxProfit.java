package com.ouywl.牛客;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: oywl
 * @time: 2022-8-1 13:52
 */

public class maxProfit {
    public static void main(String[] args) {
        int[] ints = {1, 2};
        System.out.println(maxProfit(ints));
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        map.put("1","张三");
        map.put("2","李四");
    }

    class Solution {
        public int longestPalindrome(String s) {
            int[] count = new int[128];
            int length = s.length();
            for (int i = 0; i < length; ++i) {
                char c = s.charAt(i);
                count[c]++;
            }

            int ans = 0;
            for (int v: count) {
                ans += v / 2 * 2;
                if (v % 2 == 1 && ans % 2 == 0) {
                    ans++;
                }
            }
            return ans;
        }
    }


    public static int maxProfit(int[] prices) {
        if (prices.length <= 1)
            return 0;
        int min = prices[0],
                max = 0;
        for (int i = 1; i < prices.length; i++) {
            //求出每天卖出的最大获利
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }
}
