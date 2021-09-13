package com.bruce;

/**
 * Author: Qi Gao
 * Date:2021/6/15
 * Version:1.0.0
 */
public class Meidum5_LongestPalindrome {

    /*
    用dp思想解决这个问题
    状态: 0. 遍历字符index 1. 起始位置不同 又是一个不同状态
    选择: 0.若前后字符相同 判断如果 j - i < 3 即长度就是 j - i + 1。 1. 不同dp[i][j]往里面缩小 判断更小子串是否回文
    dp含义: dp[i][j] 以i开头 j 结尾 是否是最长回文子串
     */
    public static String longestPalind(String s) {
        int maxLen = 1;
        int begin = 0;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        // dp base case: 所有的自己子串为1
       /* for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }*/
        // 动态转移
        for (int j = 0; j < len; j++) {
            char s1 = s.charAt(j);
            for (int i = 0; i <= j; i++) {
                char s2 = s.charAt(i);
                if (s2 == s1) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    }
                    else dp[i][j] = dp[i + 1][j - 1];
                } // 不等的话就是false
                // 计算maxLen
                if (dp[i][j] && j - i + 1> maxLen) {
                    begin = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
