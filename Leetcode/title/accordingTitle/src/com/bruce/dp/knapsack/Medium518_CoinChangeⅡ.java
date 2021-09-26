package com.bruce.dp.knapsack;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

=======
>>>>>>> 4d072317d15f53dfa5e27d0be179f26f6d491072
/**
 * Author: Qi Gao
 * Date:2021/5/26
 * Version:1.0.0
 */
/*
给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个
示例 1:
输入: amount = 5, coins = [1, 2, 5]
输出: 4
解释: 有四种方式可以凑成总金额:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
示例 2:
输入: amount = 3, coins = [2]
输出: 0
解释: 只用面额2的硬币不能凑成总金额3
 */
public class Medium518_CoinChangeⅡ {
<<<<<<< HEAD

    /*
    转化为是否可以用 coins 中的数组合成 amount，完全背包问题，并且为“不考虑排列顺序的完全背包问题”，
=======
    /*
    转化为是否可以用 coins 中的数组合和成 amount，完全背包问题，并且为“不考虑排列顺序的完全背包问题”，
>>>>>>> 4d072317d15f53dfa5e27d0be179f26f6d491072
    外层循环为选择池 coins，内层循环为 amount
    dp[i] 表示和为 i 的 coin 组合有 dp[i] 种。

    外层遍历 coins 每个 coin；
    内层遍历 amount。
    对于元素之和等于 i - coin 的每一种组合，在最后添加 coin 之后即可得到一个元素之和等于 i 的组合
<<<<<<< HEAD
    因此在计算 dp[i] 时，应该计算所有的 dp[i − coin] 之和。内循环会遍历coin 每一个coin相加得到i的值都会被算
    且除了dp[0] = 1 其他初始值都是0 不用担心
    e.g. dp[5] = dp[5] + dp[5-1]; dp[5] = dp[5] + dp[5-2]; dp[5] = dp[5]+dp[5-5];
=======
    因此在计算 dp[i] 时，应该计算所有的 dp[i − coin] 之和。
>>>>>>> 4d072317d15f53dfa5e27d0be179f26f6d491072
     */
    public int change(int amount, int[] coins) {
        // dp含义 i为金额时，选择coins中硬币可以凑成i金额的方式为dp[i]种
         int[] dp = new int[amount + 1];
         dp[0] = 1;
         // 状态 amount一直在变 选择：不同的硬币; 选不同硬币导致状态amount是变化的
         for (int coin : coins) {
             // 由于是可以无限选择当前的硬币 把遍历硬币放在外面, 小于coin的amount值直接为0
             for (int i = coin; i < dp.length; i++) {
                 // 转移方程可以根据式子推导出来 dp[x] = dp[x] + dp[x - coin]
                 dp[i] += dp[i - coin];
             }
         }
         return dp[amount];
        // 定义dp 可以想成背包问题 dp[i][j] : i种重量不相同的物品，j为背包大小 背包可以组成的组合数
       /* if (amount == 0) return 1;
        int[][] dp = new int[coins.length + 1][amount + 1];
        // base case : 当i = 0 dp[0][j] = 0 当j = 0 dp[i][j] = 1
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        // 动态转移：dp[i][j] = dp[i - 1][j - coins[i]] + dp[i - 1][j]
        // -> 前一个硬币数对于J重量的值 + 当前硬币数[j - 当前硬币重量]的值
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (j - coins[i - 1] < 0){
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[coins.length][amount];*/
    }
}
