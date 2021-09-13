package com.bruce.dp;

import java.util.Arrays;

/**
 * Author: Qi Gao
 * Date:2021/7/13
 * Version:1.0.0
 */
/*
132. 分割回文串 II
给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文 返回符合要求的 最少分割次数
示例 1：
输入：s = "aab"
输出：1
解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
示例 2：
输入：s = "a"
输出：0
示例 3：
输入：s = "ab"
输出：1
 */
public class Hard132_SplitPalidrome {
    // 用dp来做 首先我们要确定dp含义：dp[i] 以i 结尾的最小分割次数
    // 但是我们还不确定整个i要怎么转移，我们可以定义一个数组去判断 f[j ...i]是不是回文串
    // 如果是的话，dp[i] = dp[j - 1] + f[j...i] + 1 又 f[j..i]是回文 分割次数为0 dp[i] = dp[j - 1] + 1;
    /*
    我们定义 f[r]f为将 [1,r这一段字符分割为若干回文串的最小分割次数，那么最终答案为 f[n]
    不失一般性的考虑 f[r]如何转移：
    1. 从「起点字符」到「第 r 个字符」能形成回文串。那么最小分割次数为 0，此时有 f[r] = 0；
    2. 从「起点字符」到「第 r 个字符」不能形成回文串。此时我们需要枚举左端点 l，如果 [l,r] 这一段是回文串的话，
    那么有 f[r] = f[l - 1] + 1
    在 2 中满足回文要求的左端点位置 l 可能有很多个，我们在所有方案中取一个min 即可。
     */
    public int minCut(String s) {
        int n = s.length();
        char[] c = s.toCharArray();

        // g[l][r]代表 l...r这一段为回文串 长度为len + 1
        boolean[][] g = new boolean[n + 1][n + 1];
        for (int r = 0; r <= n; r++) {
            for (int l = r; l >= 1; l--) {
                if (l == r) g[l][r] = true;
                else {
                    if (c[l - 1] == c[r - 1]) {
                        // 判断g[l+1][r - 1]或者r-l长度等于1 头尾相同 中间只有一个元素
                        g[l][r] = g[l + 1][r - 1] || (r - l == 1);
                    }
                }
            }
        }

        // 我们得到了g数组判断每个位置是否是回文
        // dp[r] 代表将 [1eft,r] 这一段分割成若干回文子串所需要的最小分割次数
        int[] dp = new int[n + 1];
        for (int r = 1; r <= n; r++) {
            // 如果[1,r]满足回文 不需分割
            if (g[1][r]) {
                dp[r] = 0;
            }else {
                // 遍历所有的【1，r】的数组
                for (int l = 1; l <= r; l++) {
                    // [l,r] 是回文,前面不是回文 所以当前分割的次数 等于min(dp[r]，遍历[0, j - 1]的最小分割 + 1)
                    if (g[l][r]) dp[r] = Math.min(dp[r], dp[l - 1] + 1);
                }
            }
        }
        return dp[n - 1];
    }

    public int minCut2(String s) {
        if(s.length() < 2) return 0;
        char[] c = s.toCharArray();
        int len = c.length;
        // 定义dp数组 dp[i]为以i为结尾的在字符最小的分割次数
        // 要确保c[0, i]被分割成回文 找到最后一个回文的起始字符j c[j+1, i] 也为回文的话 次数加一
        boolean[][] g = new boolean[len][len];
        for(int i = 0; i < len; i++) {
            Arrays.fill(g[i], true);
        }
        // 判断每个位置的字符
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                g[i][j] = c[i] == c[j] && g[i + 1][j - 1];
            }
        }
        int[] dp = new int[len];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i= 0; i < len; i++) {
            // 判断这个位置是否是回文
            if (g[0][i]) {
                dp[i] = 0;
            }else {
                // 0 到i不是回文 需要判断分割
                for (int j = 0; j < i; j++) {
                    if (g[j + 1][i]) {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
        }
        return dp[len - 1];
    }
}
