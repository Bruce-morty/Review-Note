package com.bruce.slidingWindow;

/**
 * Author: bruce
 * Date:2021/2/6
 * Version:1.0.0
 */
/*
几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
你的点数就是你拿到手中的所有卡牌的点数之和。
给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
示例 1：
输入：cardPoints = [1,2,3,4,5,6,1], k = 3
输出：12
解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
示例 2：
输入：cardPoints = [2,2,2], k = 2
输出：4
解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
 */
public class Leetcode1423 {
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += cardPoints[i];
        }
        // 直接遍历比较前k个和后K个的值
        int max = sum;
        for (int i = 0; i < k; i++) {
            // 缩小窗口
            sum += cardPoints[len - i - 1];
            sum -= cardPoints[k - i -1];
            max = Math.max(sum, max);
        }
        return max;
    }
}
