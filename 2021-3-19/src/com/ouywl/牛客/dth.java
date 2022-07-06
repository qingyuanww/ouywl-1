package com.ouywl.牛客;

/**
 * @description:一维数组的动态和
 * @author: oywl https://leetcode.cn/problems/running-sum-of-1d-array/
 * @time: 2022-7-5 14:20
 */

public class dth {
    public static void main(String[] args) {
        dth dth = new dth();
        int[] ints = new int[]{3,2,4,4,6,21};
        dth.sout(dth.runningSum(ints));
    }
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i]=nums[i]+nums[i-1];
//            nums[i]+=nums[i-1];
        }
        return nums;
    }

    public void  sout(int[] nums){
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
