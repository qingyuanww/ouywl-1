package com.ouywl.牛客;

/**
 * @description:寻找数组的中心下标
 * @author: oywl https://leetcode.cn/problems/find-pivot-index/
 * @time: 2022-7-5 14:35
 */

public class find_pivot_index {

    public static void main(String[] args) {

    }

    class Solution {
        public int pivotIndex(int[] nums) {
            //先计算除了最左边的 后续所有值的和，然后循环比较
            int left=nums[0];
            int right =0;
            for (int i = 1; i < nums.length; i++) {
                right+=nums[i];
            }

            if(right==0) return 0; //最左侧视为0，如果右侧之和为0 ，那么返回0

            for (int i = 1; i < nums.length; i++) {
                if(left==right-nums[i]){
                    return i;
                }
                left+=nums[i];
                right-=nums[i];
            }
            return -1;
        }
        public int pivotIndex2(int[] nums) {
            //先计算综合，然后左边=total - num[i] - 左边 ，判断成立返回下标
            int left=nums[0];
            int total =0;
            for (int num : nums) {
                total += num;
            }
            for (int i = 0; i < nums.length; i++) {
                if(left==total-nums[i]-left){ //if(2*left==total-nums[i]){
                    return i;
                }
                left+=nums[i];
            }
            return -1;
        }
    }
}


