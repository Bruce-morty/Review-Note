package com.bruce.slidingWindow;

/**
 * Author: Qi Gao
 * Date:2021/8/16
 * Version:1.0.0
 */
/*
76. 最小覆盖子串
给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串如果 s 中不存在涵盖 t 所有字符的子串则返回空字符串 ""
注意：
对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
如果 s 中存在这样的子串，我们保证它是唯一的答案。
示例 1：
输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
示例 2：
输入：s = "a", t = "a"
输出："a"
示例 3:
输入: s = "a", t = "aa"
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。
 */
public class Hard76_MinWindowSub {

    /*
    核心：记录两个指针 right指针到达边界(needC==0 t中所需的字符已凑齐在s中)，
    开始移动left指针 直到边界(left == right or 当前left字符在s中数量已经为0,说明这是最小的满足条件再缩小就失去了该字符)
    记录minLen 和 start
    将left指针继续移动 因为它还是符合最小window条件的 同时更改字符数组count值和 所需字符数量need值
     */
    public String minWindow(String s, String t) {
        // 用一个set存t
        int len1 = s.length();
        int len2 = t.length();
        if (len2 > len1) return "";
        // 把t character存入 arr
        int[] count = new int[128];
        for (int i = 0; i < len2; i++) {
            // 记录t中字符的数量
            count[t.charAt(i)]++;
        }
        // 所需字符数量need值 记录总字符量 当== 0时形成窗口
        int needC = len2;
        // 遍历s 记录最小长度 以及最小长度的start 和 end
        int left = 0;
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        while (right < len1) {
            char ch = s.charAt(right);
            // 意味着s中存在t中有的字符 ch
            if (count[ch] > 0) {
                needC--;
            }
            // 将这个元素加入窗口 count值要--
            count[s.charAt(right)]--;
            // 若 == 0
            if (needC == 0) {
                // first 移动左指针直到不满足条件 不能等于0 等于0说明刚好符合窗口进去的话会-- 导致还需要字符left
                while (left < right && count[s.charAt(left)] < 0) {
                    count[s.charAt(left)]++;
                    left++;
                }
                // 记录minLen
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }
                // caution!! 这里要移动left 使得不符合窗口进行下次判断
                count[s.charAt(left)]++;
                left++;
                needC++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
