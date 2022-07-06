package com.ouywl.牛客;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 数组中两数之和等于 传入传入值，返回下标
 * @author: oywl
 * @time: 2022-6-13 10:01
 */

public class TwoSubmit {
    public static void main(String[] args) {
        TwoSubmit twoSubmit = new TwoSubmit();
        int[] num ={1,3,5,12,13};
        int[] ints = twoSubmit.twoSumHash(num, 13);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
    public int[] twoSumHash(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            int result = target - nums[i];
            if (map.containsKey(result)) {
                return new int[] {map.get(result), i};
            }
            map.put(nums[i], i);
        }
        return new int[] {-1, -1};
    }
}
