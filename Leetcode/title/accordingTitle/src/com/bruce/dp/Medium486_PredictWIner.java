package com.bruce.dp;

import java.util.Arrays;

/**
 * Author: Qi Gao
 * Date:2021/6/18
 * Version:1.0.0
 */
/*
给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿
……。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜
给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
示例 1：
输入：[1, 5, 2]
输出：False
解释：一开始，玩家1可以从1和2中进行选择。
如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5
那么玩家 1 则只剩下 1（或者 2 ）可选。
所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5
因此，玩家 1 永远不会成为赢家，返回 False
 */
public class Medium486_PredictWIner {

    /*
    博弈问题，同时子问题没有后效性 考虑用dp
    dp[i][j]: 在i...j区间内 先手获得的最大净胜分是多少，以此预测第一个人拿到的在i，j区间内取得的值，
    大于0的话 说明可以赢 否则为false
    从左边或右边开始拿，推导出转移----->
    转移方程: dp[i][j] = max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
     */
    // 状态转移方程：dp[i][j] = max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1])
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];

        // dp[i][j]：作为先手，在区间 nums[i..j] 里进行选择可以获得的相对分数
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] >= 0;
    }


    // 记忆化递归的写法，构建memo数组保存曾经出现过的值
    public boolean PredictTheWinner2(int[] nums) {
        int len = nums.length;
        int[][] memo = new int[len][len];

        for (int i = 0; i < len; i++) {
            Arrays.fill(memo[i], Integer.MIN_VALUE);
        }
        return dfs(nums, 0, len - 1, memo) >= 0;
    }

    private int dfs(int[] nums, int i, int j, int[][] memo) {
        if (i > j) {
            return 0;
        }

        if (memo[i][j] != Integer.MIN_VALUE) {
            return memo[i][j];
        }
        int chooseLeft = nums[i] - dfs(nums, i + 1, j, memo);
        int chooseRight = nums[j] - dfs(nums, i, j - 1, memo);
        memo[i][j] = Math.max(chooseLeft, chooseRight);
        return memo[i][j];
    }
}
