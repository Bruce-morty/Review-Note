package com.bruce.dp.stock;

/**
 * Author: Qi Gao
 * Date:2021/9/20
 * Version:1.0.0
 */
/*
给定一个数组，它的第  个元素是一支给定的股票在第  天的价格。设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。

注意： 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例：

输入: [3,3,5,0,0,3,1,4]
输出: 6
解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利
 */
public class Hard123_StockⅢ {

    /*
    想一下 这里有几个状态. 首先股票只能交易两次. 然后 买进之前手上必须没有股票.
    有五个阶段
    阶段	可交易次数	持股状态	可买入/卖出
    0	2	        不持有	可买入
    1	1	        持有	    可卖出
    2	1	        不持有	可买入
    3	0	        持有	    可卖出
    4	0	        不持有	可买入
    定义子问题: s0/1/2/3/4(k) 表示前k天结束的时候 处于各个阶段的最大利润数
    s0(k) = 0, 什么交易都不做
    s1(k) = max(s0(k - 1) - nums[k], s1(k - 1)) -> 取前一天的1阶段最大 和前一天的s0买入当前股票的比值
    第 k 天处于阶段 2，可能是前一天处于阶段 2，或者是前一天处于阶段 1，然后卖出了第 k 天的股票(利润增加)
    s2(k) = max(s1(k - 1) + nums[k], s2(k - 1)) -> 在k天卖出 和 k - 1天的2阶段最大 比值
    s3(k) = max(s2(k - 1) - nums[k], s3(k - 1))
    s4(k) = max(s3(k - 1) + nums[k], s4(k - 1))
     */
    public int maxProfit(int[] prices) {
        // 根据算法写代码 定义4个dp 状态数组 还要处理每个dp的 base case
        // 我们只需要处理s1(0) = MIN_VAL, s2(0) = 0, s3 s4同理
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        int[] s1 = new int[n+1];
        int[] s2 = new int[n+1];
        int[] s3 = new int[n+1];
        int[] s4 = new int[n+1];
        s1[0] = Integer.MIN_VALUE;
        s2[0] = 0;
        s3[0] = Integer.MIN_VALUE;
        s4[0] = 0;
        for (int k = 1; k <= n; k++) {
            s1[k] = Math.max(s1[k-1], -prices[k-1]);
            s2[k] = Math.max(s2[k-1], s1[k-1] + prices[k-1]);
            s3[k] = Math.max(s3[k-1], s2[k-1] - prices[k-1]);
            s4[k] = Math.max(s4[k-1], s3[k-1] + prices[k-1]);
        }
        // 还有可能最大的是0
        return Math.max(0, Math.max(s2[n], s4[n]));
    }

    /*
    可以对空间进行优化, s1(k)的值 取决于s1(k - 1) and prices(k - 1)
    s2(k) 取决于 s2(k - 1) and s1(k - 1), prices(k - 1)....s3 s4同理 我们不用创建数组
     */
    public int maxProfit2(int[] prices) {
        if (prices.length == 0) return 0;
        int s1 = Integer.MIN_VALUE, s2 = 0,
                s3 = Integer.MIN_VALUE, s4 = 0;
        for (int p : prices) {
            s1 = Math.max(s1, -p);
            s2 = Math.max(s2, s1 + p);
            s3 = Math.max(s3, s2 - p);
            s4 = Math.max(s4, s3 + p);
        }
        return Math.max(0, s4);
    }
}
