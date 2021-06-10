package com.bruce.dp;

/**
 * Author: bruce
 * Date:2021/2/23
 * Version:1.0.0
 */
/*
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例 1：

输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
示例 2
输入：nums = [1]
输出：1
示例 3：

输入：nums = [0]
输出：0
 */
public class EasyMaxSubarray {

    /*
    不一定是用滑动窗口或者双指针 主要是它没有窗口的限制。更新也就相当于dp的用空间记录当前位置！！！
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length < 2) return nums[0];
        // brute force
        int len = nums.length;
        /* int max = Integer.MIN_VALUE;
         for (int i = 0; i < len; i++) {
             int count = 0;
             for (int j = i; j < len; j++) {
                 count += nums[j];
                 max = Math.max(count, max);
             }
         }
         return max;*/
        // Dp: 要找到最大的连续子序和 用dp[i] 表示当前index的最大子序和
        // 推导 如果累加到当前的值小于当前的值 还不如不累加 让当前值成为新的起点() 继续往下遍历
        // 转移方程：dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        /* int max = Integer.MIN_VALUE;
         int[] dp = new int[nums.length];
         for (int i = 0; i < nums.length; i++) {
             if (i > 0) {
                 dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
             } else {
                 dp[i] = nums[i];
             }
         }
         for (int i = 0; i < dp.length; i++) {
             if (max < dp[i]) {
                 max = dp[i];
             }
         }
         return max;*/
        // 优化
        int res = nums[0];
        int sum = 0;
        for (int x : nums) {
            // 因为当前的值 只和i - 1有关 将 res定义成 dp[i - 1]; 第一个元素进来sum = x 往后遍历
            // sum <= 0 ==》 nums[i] + dp[i - 1] < nums[i] --> dp[i - 1] <= 0 说明更新当前位置
            if (sum > 0){
                sum += x;
            } else {
                sum = x;
            }
            res = Math.max(res, sum);

        }
        return res;
    }
}
