package com.bruce.backtrack;

import java.util.*;

/**
 * Author: Qi Gao
 * Date:2021/7/13
 * Version:1.0.0
 */
/*
131. 分割回文串
给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
回文串 是正着读和反着读都一样的字符串
示例 1：
输入：s = "aab"
输出：[["a","a","b"],["aa","b"]]
示例 2：
输入：s = "a"
输出：[["a"]]
 */
public class Medium131_SplitPal1 {

    /*
    我们需要回溯去判断后面的是否分割的是回文
    0.每一个结点表示剩余没有扫描到的字符串，产生分支是截取了剩余字符串的前缀；
    1. 产生前缀字符串的时候，判断前缀字符串是否是回文。
        如果前缀字符串是回文，则可以产生分支和结点；
        如果前缀字符串不是回文，则不产生分支和结点，这一步是剪枝操作。
    2. 在叶子结点是空字符串的时候结算，此时 从根结点到叶子结点的路径，就是结果集里的一个结果，使用深度优先遍历，
    记录下所有可能的结果。
    3. 使用一个路径变量 path 搜索，path 全局使用一个（注意结算的时候，要生成一个拷贝），因此在递归执行方法结束以后需要回溯，
    4. 即将递归之前添加进来的元素拿出去；
    path 的操作只在列表的末端，因此合适的数据结构是栈。
     */
    public List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0) return res;

        char[] ch = s.toCharArray();
        // 预处理字符串 判断dp[i][j] i..j是不是回文
        boolean[][] dp = new boolean[len][len];
        // 动态转移：s[i] == s[j] 时 dp[i][j] = dp[i+1][j-1]
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if (ch[i] == ch[j]) {
                    // 若长度小于2 或者子数组是回文 则当前为true
                    if (i - j < 2 || dp[j + 1][i - 1]) {
                        dp[j][i] = true;
                    }
                }
            }
        }
        Deque<String> stack = new ArrayDeque<>();
        dfs(s, 0, len, dp, stack, res);
        return res;
    }

    private void dfs(String s, int index, int len, boolean[][] dp, Deque<String> stack, List<List<String>> res) {
        if (index == len) {
            // 遍历到边界 添加当前路径
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = index; i < len; i++) {
            if (dp[index][i]) {
                stack.addLast(s.substring(index, i + 1));
                // 继续回溯
                dfs(s, i + 1, len, dp, stack, res);
                // 剪枝
                stack.removeLast();
            }
        }
    }

    List<List<String>> lists = new ArrayList<>();
    Deque<String> deque = new LinkedList<>();

    public List<List<String>> partition2(String s) {
        backTracking(s, 0);
        return lists;
    }

    private void backTracking(String s, int startIndex) {
        //如果起始位置大于s的大小，说明找到了一组分割方案
        if (startIndex >= s.length()) {
            lists.add(new ArrayList(deque));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            //如果是回文子串，则记录
            if (isPalindrome(s, startIndex, i)) {
                String str = s.substring(startIndex, i + 1);
                deque.addLast(str);
            } else {
                continue;
            }
            //起始位置后移，保证不重复
            backTracking(s, i + 1);
            deque.removeLast();
        }
    }

    //判断是否是回文串
    private boolean isPalindrome(String s, int startIndex, int end) {
        for (int i = startIndex, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
