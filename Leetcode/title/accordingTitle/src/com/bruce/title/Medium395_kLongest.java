package com.bruce.title;

import java.util.HashMap;

/**
 * Author: Qi Gao
 * Date:2021/7/17
 * Version:1.0.0
 */
/*
395. 至少有 K 个重复字符的最长子串
给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度
示例 1：
输入：s = "aaabb", k = 3
输出：3
解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
示例 2：
输入：s = "ababbc", k = 2
输出：5
解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 */
public class Medium395_kLongest {
    public int longestSubstring(String s, int k) {
        // 用分治法 写一个辅助方法 递归地区求解符合条件的值
        if (s.length() < k) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        // 然后遍历map 找出不符合的字符位置
        int res = 0;
        for (char set : map.keySet()) {
            int val = map.get(set);
            if (val < k) {
                String[] sarr = s.split(String.valueOf(set));
                for (String temp : sarr) {
                    // 进入下一个递归区判断s
                    res = Math.max(res, longestSubstring(temp, k));
                }
                return res;
            }
        }
        return s.length();
        // int len = divide(s, k, 0, s.length() - 1);
        // return len;
    }

    public int divide(String s, int k, int left, int right) {
        // 判断字符的个数
        int[] ch = new int[26];
        for (int i = left; i <= right; i++) {
            ch[s.charAt(i) - 'a']++;
        }
        char split = 0;
        // 判断这个每个元素的个数
        for (int i = 0; i < 26; i++) {
            if (ch[i] >0 && ch[i] < k) {
                // 标记这个变量, 把它变成char
                split = (char)(i + 'a');
                break;
            }
        }
        // 所有的字符都等于k
        if (split == 0) {
            return right - left + 1;
        }
        int i = left;
        int res = 0;
        while (i <= right) {
            while (i <= right && s.charAt(i) == split) {
                i++;
            }
            if (i > right) {
                break;
            }
            int start = i;
            // 找到了不等于split的字符
            while (i <= right && s.charAt(i) != split) {
                i++;
            }
            // i > right
            int reLen = divide(s, k, start, i - 1);
            res = Math.max(res, reLen);
        }
        return res;
    }
}
