package com.bruce.dp;

/**
 * Author: Qi Gao
 * Date:2021/9/3
 * Version:1.0.0
 */
/*
10. 正则表达式匹配
给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串
示例 1：
输入：s = "aa" p = "a"
输出：false
解释："a" 无法匹配 "aa" 整个字符串
示例 3：
输入：s = "ab" p = ".*"
输出：true
解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
示例 4：
输入：s = "aab" p = "c*a*b"
输出：true
解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
示例 5：
输入：s = "mississippi" p = "mis*is*p*."
输出：false
提示：

0 <= s.length <= 20
0 <= p.length <= 30
s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
保证每次出现字符 * 时，前面都匹配到有效的字符
 */
public class Hard10_ExpressionMatch {

    /*
    用dp来求解，思路 分情况讨论最重要. 定义dp，dp[i][j]：s的前i个字符是否和j的前j个字符匹配
    已知了 dp[i-1][j-1] 怎么求dp[i][j].
    最简单的情况开始讨论: 0.1 s[i] == p[j] && dp[i - 1][j - 1] == true -> dp[i][j] = true
    0.2 p[j] == "."  0.3 p[j] == "*"
    1. 若p[j] == "*" 要确保 *和前一个字符走 即 p[j-1]能匹配上s[i] * 才有用
    1.1 p[j - 1] == "." 1.2 p[j - 1] == s[i - 1] 则*就有用了
    第二个难想出来的点：怎么判断前面是否匹配
    dp[i][j] = dp[i-1][j] // 多个字符匹配的情况
    or dp[i][j] = dp[i][j-1] // 单个字符匹配的情况
    or dp[i][j] = dp[i][j-2] // 没有匹配的情况
    难点在这：dp[i][j] = dp[i - 1][[j] || dp[i][j-2] if s[i] == p[j-1]
    首先 dp[i - 1][j] 代表如果s的末尾与p的 * 前一个字符匹配，则移动i往前判断 i - 1与p[j] 是否match
        然后 相当于 s[i - 2] 与p[j - 1]判断是否相等
        dp[i][j - 2] 代表两个意思 if s[i] == p[j-1] 和 if s[i] != p[j-1]
        s[i] == p[j - 1] -> 因为*可以代表0 所以p[j-1] and p[j] 可能变成0 例: s: X p: XX*  还是匹配成功
        s[i] != p[j - 1] -> e.g s: X  p: XD* 可以把p的最后两个元素变成0 然后判断 s[i] == p[j - 2]
        所以还是dp[i][j] = dp[i - 1][[j] || dp[i][j-2] if s[i] == p[j-1]
     */
    public boolean isMatch(String s, String p) {
        int len1 = s.length();
        int len2 = p.length();
        // edge case: dp[0][0] = true
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        // 遍历的方向 是从后往前 还是从前往后
        for (int i = 0; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (p.charAt(j - 1) == '*') {
                    // 首先假设没有匹配上 则i不动 j往前移动2个继续判断
                    dp[i][j] = dp[i][j - 2];
                    // 判断 p[j - 2] s[i - 1]
                    if(match(s, p, i, j - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }else {
                    // p[j] 不是 "*"
                    // 判断 s[i - 1] p[j - 1]
                    if (match(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[len1][len2];
    }

    private boolean match(String s, String p, int i, int j) {
        // 判断 s[i] p[j]
        if (i == 0) return false;
        if (p.charAt(j - 1) == '.') return true;
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
