package com.bruce.slidingWindow;

/**
 * Author: bruce
 * Date:2021/2/27
 * Version:1.0.0
 */
/*
找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
示例 1:
输入:
s = "aaabb", k = 3
输出:
3
最长子串为 "aaa" ，其中 'a' 重复了 3 次。
示例 2:
输入:
s = "ababbc", k = 2
输出:
5
最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 */
public class Medium396_KLongestSubStr {
    public int longestSubstring(String s, int k) {
        // 1. 理解题意 找到字符串中重复的字符 重复次数不小于K次 然后找到符合这个条件的子串
        // 算法：用freq[] 记录每个字符的次数 然后与k比较 大于或等于k就记录下来 但如果后面遇到重复的怎么办
        int len = s.length();
        if (len == 0 || k > len) return 0;
        if (k < 2) return len;
        int res = 0;
        // 用递归去做 1.定义两个指针 分别往中间扫描 2. 不符合的就继续扫描左右两边
        return count(s.toCharArray(), k, 0, len - 1);
    }

    public static int count(char[] ch, int c, int left, int right) {
        int[] freq = new int[26];
        for (int i = left; i <= right; i++) {
            freq[ch[i] - 'a']++;
        }
        // 判断字符的个数 如果字符的次数小于k则不可能在结果中
        while (right - left + 1 >= c && freq[ch[left] - 'a'] < c) {
            left++;
        }
        while (right - left + 1 >= c && freq[ch[right] - 'a'] < c) {
            right--;
        }
        // 判断是否满足大于k
        if (right - left + 1 < c) return 0;
        // 找到分割的地方 若有一个地方小于k
        // 得到临时子串 分别递递归处理
        for (int i = left; i <= right; i++) {
            if (freq[ch[i] - 'a'] < c) {
                return Math.max(count(ch, c, left, i - 1), count(ch, c, i+1, right));
            }
        }
        return right - left + 1;
    }
}
