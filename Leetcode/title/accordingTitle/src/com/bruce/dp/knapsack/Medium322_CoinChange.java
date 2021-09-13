package com.bruce.dp.knapsack;

import java.util.Arrays;

/**
 * Author: Qi Gao
 * Date:2021/5/25
 * Version:1.0.0
 */
/*
给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数
如果没有任何一种硬币组合能组成总金额，返回 -1。
你可以认为每种硬币的数量是无限的。
示例 1：
输入：coins = [1, 2, 5], amount = 11
输出：3
解释：11 = 5 + 5 + 1
示例 2：
输入：coins = [2], amount = 3
输出：-1
示例 3：
输入：coins = [1], amount = 0
输出：0
 */
public class Medium322_CoinChange {
    // 思路：背包问题 状态：当前背包容量有多少 可选的物品无限（状态不变） 选择：选择硬币
    // dp[i] 以i为金额最少能凑的硬币数 dp[i] = 做选择 Math.min(dp[i], dp[i - coin] + 1)
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        // 用自底向下的方式
        int[] dp = new int[amount + 1];
        // 初始化的值应该尽可能大 因为后面是取最小
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            // 内循环判断遍历每一个coin
            for (int coin : coins) {
                if (i - coin < 0) continue;
                // 0.选择1 不选当前的硬币 1. 选当前的硬币
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

}
