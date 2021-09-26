package com.bruce.dp.knapsack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: Qi Gao
 * Date:2021/5/25
 * Version:1.0.0
 */
/*
给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词
说明：
拆分时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。
示例 1：
输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
示例 2：
输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     注意你可以重复使用字典中的单词。
示例 3：
输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false
 */
public class Medium139_wordSplit {
    // 思路：典型的背包问题 并且是完全背包问题 wordDict相 当于coins 可以任意多地取
    /*
    状态：0.背包剩余的容量是多少
    选择：单词列表
    dp[i]: 以i结尾的string 是否能呗分成wordDict中的单词
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        // 构建set保存word中的单词
        Set<String> set = new HashSet<>(wordDict);
        int max = 0;
        for (String s1 : wordDict) {
            max = Math.max(s1.length(), max);
        }
        boolean[] dp = new boolean[s.length() + 1];
        // base case 没有单词结尾则是true 在字典中
        dp[0] = true;
        // 动态转移，要判断整个string是否合法 需要遍历[0, i-1]
        // 遍历到分割点j，判断0- j 是一个单词在word中，然后j - i 是一个单词 才能判断dp[i] 为true
        for (int i = 1; i < dp.length; i++) {
            // 从后往前遍历可以剪枝节省时间 若0 - j是合法的 则判断下个阶段 j - i若已经大于最大单词长度剪枝
            for (int j = i - 1; j >= 0; j--) {
                // 判断是否比最大的单词还要长
                if (i - j > max) break;
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    // 分割一次已经够了 当j再变小即使也匹配word中的单词也不care
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
