package com.bruce.slidingWindow;

/**
 * Author: bruce
 * Date:2021/2/2
 * Version:1.0.0
 */
/*
给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。
在执行上述操作后，找到包含重复字母的最长子串的长度。
注意：字符串长度 和 k 不会超过 104。
示例 1：
输入：s = "ABAB", k = 2
输出：4
解释：用两个'A'替换为两个'B',反之亦然。
示例 2：
输入：s = "AABABBA", k = 1
输出：4
解释：
将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
子串 "BBBB" 有最长重复字母, 答案为 4。
 */
public class Leetcode424 {
    /*
    滑動窗口的实现，使用两个指针，右指针遍历数组 左指针是窗口的左起点
    用一个数组保存出现过字母的数量 是在窗口中存在的数量，在窗口外的要减少并且左指针++ 滑动窗口
     */
    public static int characterReplacement(String s, int k) {
        int[] map = new int[26];
        char[] chars = s.toCharArray();
        int len = chars.length;
        // 定义左右指针
        int left = 0;
        int right = 0;
        int historyMax = 0;
        while (right < len) {
            // 每遍历新字符更新map里的值
            int index = chars[right] - 'A';
            // 更新窗口中最大字符子串数量
            map[index]++;
            historyMax = Math.max(historyMax, map[index]);
            // 判断窗口长度和当前最多字符子串数量 + k的大小
            // 若大于则说明最大子串的值没有增加 减少当前窗口中字符子串的数量 窗口向右移动 左指针++
            // #caution 为什么当窗口弹出最多数量的子串时 history不更新 我的理解：history控制窗口是否扩大
            // 当前窗口里最大子串的字符减少了 但是由于最大子串在前面 可以把窗口里的所有字符替换成最大子串的字符
            // 所以更新history只需要考虑当最大字符数量变大才需要更新 变小了不需要考虑 因为窗口长度已经记录下来了 窗口不会缩小
            if (right - left + 1 > historyMax + k) {
                map[chars[left] - 'A']--;
                left++;
            }
            right++;
        }
        // 最后right = len 了 所以窗口大小就是right - left 不是 right - left + 1
        return right - left;
    }

    public static void main(String[] args) {
        String s1 = "AABABBA";
        int k1 = 1;
        String s2 = "ABAB";
        int k2 = 2;
        int res1 = characterReplacement(s1, k1);
        int res2 = characterReplacement(s2, k2);
        System.out.println(res1);
        System.out.println(res2);
    }
}
