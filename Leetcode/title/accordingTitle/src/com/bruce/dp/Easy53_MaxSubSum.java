package com.bruce.dp;

/**
 * Author: Qi Gao
 * Date:2021/6/13
 * Version:1.0.0
 */
/*
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
示例 1：

输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
示例 2：

输入：nums = [1]
输出：1
示例 3：

输入：nums = [0]
输出：0
 */
public class Easy53_MaxSubSum {

    // 用dp思想，若要想找最大和 只需要判断加到nums[i]时加起来的sum比nums[i]大就值得加
    // 可知dp[i]：以nums[i]结尾的最大子数组的和 转移方程: dp[i] = max(nums[i], dp[i - 1] + nums[i])
    public static int max2(int[] nums) {
/*        int max = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        for (int i = 0; i < dp.length; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;*/
        // 优化 因为当前的值只与 i - 1有关 将res定义成 dp[i - 1]
        int res = nums[0];
        int sum = 0;
        for (int x : nums) {
            // 因为当前的值 只和i - 1有关 将 res定义成 dp[i - 1];
            // if (sum > 0){
            //     sum += x;
            // } else {
            //     sum = x;
            // }
            // res = Math.max(res, sum);
            // 要比较当前值和前i - 1的和 求出当前的和应该是多少 需不需要加这个sum
            sum = Math.max(sum + x, x);
            // 然后比较当前的sum和之前的dp值 相当于比较dp[0] dp[1] ... dp[len] 这一步 不用再写一个loop判断
            res = Math.max(sum, res);
        }
        return res;
    }
    // 暴力解法 两个循环解决
    public int maxSubArray(int[] nums) {
        // brute force: double loop to iterate the array.
        int len = nums.length;
        int max_res = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            int temp = 0;
            for (int j = i; j < len; j++) {
                // if nums[j]
                temp += nums[j];
                max_res = Math.max(max_res, temp);
            }
        }
        return max_res;
    }
}
