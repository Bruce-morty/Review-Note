package com.bruce.dp.stock;

/**
 * Author: Qi Gao
 * Date:2021/9/21
 * Version:1.0.0
 */
/*
309. 最佳买卖股票时机含冷冻期
给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
示例:

输入: [1,2,3,0,2]
输出: 3
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]

 */
public class Medium309_FreezeTime {

    /*
    情况二：122
    由于具有相同的 k 值，因此情况五和情况二非常相似，不同之处在于情况五有「冷却时间」的限制，因此需要对状态转移方程进行一些修改
    情况二的状态转移方程如下：
    T[i][k][0] = max(T[i - 1][k][0], T[i - 1][k][1] + prices[i])
    T[i][k][1] = max(T[i - 1][k][1], T[i - 1][k][0] - prices[i])
    但是在有「冷却时间」的情况下，如果在第 i - 1 天卖出了股票，就不能在第 i 天买入股票。因此，如果要在第 i 天买入股票，
    第二个状态转移方程中就不能使用 T[i - 1][k][0]，而应该使用 T[i - 2][k][0]。状态转移方程中的别的项保持不变，
    新的状态转移方程如下：
    T[i][k][0] = max(T[i - 1][k][0], T[i - 1][k][1] + prices[i])
    T[i][k][1] = max(T[i - 1][k][1], T[i - 2][k][0] - prices[i])
    根据上述状态转移方程，可以写出时间复杂度为 O(n)O(n) 和空间复杂度为 O(n)O(n) 的解法
     */
    public int maxProfit(int[] prices) {
        if (prices ==  null || prices.length == 0) return 0;
        int len = prices.length;
        int[][]dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            // 在i天卖股票 i - 1天可以买 卖股票和冷冻期无关
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 如果要在第i天买 股票 i - 1天就不能有卖股票的操作. 用前两天的cash 去买今天的股票
            dp[i][1] = Math.max(dp[i - 1][1], (i >= 2 ? dp[i - 2][0]: 0) - prices[i]);
        }
        return dp[len - 1][0];
    }
    /*
    Space Complexity:O(n)
     */

    /*
    可以降到 O(1). 我发现持有股票的profit 取决于前一天持有stock的profit 和 前两天cash的profit
     */
    // e.g. Loop 0: preCash = 0, newC = 1, newS = 1; preP = preC = 0, preC = newC = 1, ...
    // Loop 1: newC = 2, newS = 2 = (preS = 1, preP = 0(前天的状态, 当前newS是状态2) - prices[i]);
    // 数字代表的是当前的level loop第0层的时候各个variables应该是什么状态
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 1) return 0;
        int len = prices.length;
        int preCash = 0;
        int preStock = -prices[0];
        int preProfit = 0;
        for (int i = 1; i < len; i++) {
            int newCash = Math.max(preCash, preStock + prices[i]);
            int newStock = Math.max(preStock, preProfit - prices[i]);
            // update stock profit cash, 先更新preProfit 这样就是前天的cash了
            preProfit = preCash;
            preCash = newCash;
            preStock = newStock;
        }
        return preCash;
    }

}
