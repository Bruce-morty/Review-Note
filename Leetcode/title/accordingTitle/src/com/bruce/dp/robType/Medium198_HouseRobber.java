package com.bruce.dp.robType;

/**
 * Author: Qi Gao
 * Date:2021/5/19
 * Version:1.0.0
 */
/*
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed, the only constraint stopping you from robbing
each of them is that adjacent houses have security systems connected and it will automatically
contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house,
return the maximum amount of money you can rob tonight without alerting the police.
示例 1：

输入：[1,2,3,1]
输出：4
解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4
示例 2：
输入：[2,7,9,3,1]
输出：12
解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12
 */
public class Medium198_HouseRobber {
    // 最大值想到可以用DP，base case: 0.偷第一家 不偷第二家 1. 偷第二家不偷第一家
    // 状态：不能连续偷两家，0.偷第一家 不偷第二家 1. 偷第二家不偷第一家
    // 选择所以遍历到当前时 取(偷当前 + 前两个位置，前一个位置的值)最大
/*     定义dp : 遍历到当前屋子所可以偷到的最大钱
     分析：可知不能连续偷两个房间的钱，确保遍历完整个数组偷到的钱最大
     dp: base case：偷第一间屋子 或者偷第二间屋子 状态：比较当前偷当前比较多还是不偷当前比较合算
     dp含义：遍历到这间屋子可以偷到的最大金额*/
    public static int rob(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        if (nums.length < 2) return nums[0];
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        // 若dp[n] 是最大值 有没有可能
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[len - 1];
    }

    /*
    相当于Fibonacci
    打家劫舍的模板代码
     */
    public static int rob2(int[] nums) {
        // 用滚动数组 节省空间
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        // 由于定义的dp 当前的值只和前一个有关系
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}
