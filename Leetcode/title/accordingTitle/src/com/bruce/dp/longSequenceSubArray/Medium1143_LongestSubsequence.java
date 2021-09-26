package com.bruce.dp.longSequenceSubArray;

/**
 * Author: Qi Gao
 * Date:2021/4/4
 * Version:1.0.0
 */
/*
给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0
一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符
（也可以不删除任何字符）后组成的新字符串。
例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
示例 1：
输入：text1 = "abcde", text2 = "ace"
输出：3
解释：最长公共子序列是 "ace" ，它的长度为 3 。

输入：text1 = "abc", text2 = "abc"
输出：3
解释：最长公共子序列是 "abc" ，它的长度为 3 。
 */
public class Medium1143_LongestSubsequence {
    // 最值问题要想到dp，需要穷举所有的值 找出最优值
    /*
    text1 的长度: [0,i]. text2 : [0, j]. 构建二维dp[m][n]: 意思是text1 m位置和text2 n位置的最长公共子序列
    1. 边界条件：如果 i = 0或者 j = 0. dp[i][0] = 0 dp[0][j] = 0
    2. 动态转移方程: if 当前元素相等 dp[i][j] = dp[i - 1][j - 1] + 1;
    不等：longest = text1的前一个元素和当前text2元素 和 text1的当前元素和text2的前一个元素的子序列最大值
    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j])
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            // 固定text1的某个元素 从text2的头元素开始遍历
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 考虑 text1[0, i - 1] 和 text2[0,j]的最长子序列
                    // 考虑 text1[0,i] 和 text2[0, j - 1]的最长子序列
                    // 根据dp，text1[0,i-1]和text2[0,j]的最长子序列是 dp[i - 1][j] and dp[i][j - 1] so on
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }

}
