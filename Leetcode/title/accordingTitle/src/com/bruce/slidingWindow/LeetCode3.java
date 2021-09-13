package com.bruce.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: bruce
 * Date:2020/10/22
 * Version:1.0.0
 */
/*
3. Longest Substring Without Repeating Characters:
Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LeetCode3 {
    // Given a string s, find the length of the longest substring without repeating characters.
    public static int lengthOfLongestSubString(String s) {
        // 可以用滑动窗口来做，设for循环的i为窗口的右指针
        // 1.当遇到重复元素时，取当前位置和存储元素索引的最大，跳过重复元素, i代表窗口右指针
        int start = 0;
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                // +1 是为了跳过这个元素，这个元素重复了，踢出窗口
                // + 1 是为了跳过重复字符，用max方法 有可能窗口位置比存储的字符位置+1 还大
                // 例如：aabbabc 遍历到第三个a， start = 3(第二个b位置) i是4，取出第二个a + 1 = 2小于当前
                start = Math.max(start, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            // 有可能最大的没有现在的大 e.g: abccab 遍历到第二c，存下来的最大长度是3，但是start = 3, i = 3,
            // 3 - 3 + 1 = 1; max最大还是3
            maxLength = Math.max(maxLength, i - start + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "aabbabcdabcdabc";
        int length = lengthOfLongestSubString(s);
        System.out.println(length);
    }
}
