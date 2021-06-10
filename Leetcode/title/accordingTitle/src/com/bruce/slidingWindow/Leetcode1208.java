package com.bruce.slidingWindow;

/**
 * Author: bruce
 * Date:2021/2/5
 * Version:1.0.0
 */
/*
给你两个长度相同的字符串，s 和 t。

将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），
也就是两个字符的 ASCII 码值的差的绝对值。
用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。

示例 1：
输入：s = "abcd", t = "bcdf", cost = 3
输出：3
解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
示例 2：
输入：s = "abcd", t = "cdef", cost = 3
输出：1
解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
示例 3：
输入：s = "abcd", t = "acde", cost = 0
输出：1
解释：你无法作出任何改动，所以最大长度为 1。
 */
public class Leetcode1208 {

    /*
    可以由题意知道这是由两个字符串的差值和cost的比较 每个字符元素差值小于cost就可以形成一个窗口
    所以定义一个数组存储差值
     */
    public static int equalSubString(String s, String t, int maxCost) {
        int len = s.length();
        int[] sum = new int[len];
        for (int i = 0; i < len; i++) {
            // 将每个字符的差保存到sum
            sum[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        // 定义窗口变量和 返回长度 与cost比较消耗所剩的cost
        int left = 0;
        int right = 0;
        int res = 0;
        int total = 0;
        while (right < len) {
            // 每次窗口扩大 需要消耗cost 而消耗的值是最右新加入的指针的元素
            total += sum[right];
            if (total > maxCost) {
                total -= sum[left];
                left++;
            }
            // 窗口这时候++ 后面的right - left 就不用加 1 了 同时向后遍历下一个字符
            right++;
            res = Math.max(res, right - left);
        }
        return res;
    }
}
