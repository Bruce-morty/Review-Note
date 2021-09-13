package com.bruce.dp.knapsack;

/**
 * Author: Qi Gao
 * Date:2021/4/29
 * Version:1.0.0
 */
/*
Given an array of distinct integers nums and a target integer target,
return the number of possible combinations that add up to target.

The answer is guaranteed to fit in a 32-bit integer.
给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。

题目数据保证答案符合 32 位整数范围。

输入：nums = [1,2,3], target = 4
输出：7
解释：
所有可能的组合为：
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
请注意，顺序不同的序列被视作不同的组合。
 */
public class Medium377_CombinationⅣ {

    /*
    值得思考：这题是考察有重复序列的组合 我们用dp找到最多的等于target的排列数
    dp套路：base case -> 状态 -> 选择 -> 定义dp的含义
    base case: dp[0] = 1 不选取任何元素时 只有0符合target=0
    状态 : 1 <= i <= target 中有一个排列 且sum刚好等于i 排列中最后一个数num在数组中
    则 当前的排列数 dp[i] = dp[i] + dp[i - num] 代表之前的排列数 + 符合的dp
    因为若要求出dp[i] 求出这个数组中的target - num这个值的排列数 加上当前的数就等于dp[i]的排列数
    选择 ：
    dp：dp[i] 表示选取元素之和等于i的方案数目 目标是求dp[target]
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        // 遍历数组 找到dp中有没有符合 i - num 的排列数
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    // 这一步保证了每个数能down到base case : 1
                    // 若 3 在数组中出现 dp[3] = dp[0] = 1; 自己的和就是1 没在数组中的数就是0
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
