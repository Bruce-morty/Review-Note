package com.bruce.slidingWindow;

import java.util.Arrays;

/**
 * Author: bruce
 * Date:2021/2/10
 * Version:1.0.0
 */
/*
567. 字符串的排列
给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
换句话说，第一个字符串的排列之一是第二个字符串的子串。
示例1:
输入: s1 = "ab" s2 = "eidbaooo"
输出: True
解释: s2 包含 s1 的排列之一 ("ba").
示例2:
输入: s1= "ab" s2 = "eidboaoo"
输出: False
 */
public class Leetcode567_Permutation_in_String {

    public static boolean checkInclusion(String s1, String s2) {
        // 只要s2字符串中有包含s1就可以 不考虑字符串先后顺序。可以利用s1的长度作为窗口 定义两个频率数组判断数组的值是否相等
        // 若值都一样，说明s2当前窗口包含s1的值
        // 定义频率数组
        int len1 = s1.length();
        int len2 = s2.length();
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        for (int i = 0; i < len1; i++) {
            int index = s1.charAt(i) - 'a';
            freq1[index]++;
        }
        // 定义双指针去遍历字符串
        int left = 0;
        int right = 0;
        while (right < len2) {
            // 每次进来当前字符的频率++
            freq2[s2.charAt(right) - 'a']++;
            // 判断是否形成窗口
            if (right - left + 1 == len1) {
                if (Arrays.equals(freq1, freq2)) {
                    return true;
                }
                // 否则移动窗口 移除当前窗口的字符频率
                freq2[s2.charAt(left) - 'a']--;
                left++;
            }
            right++;
        }
        return false;
    }
}
