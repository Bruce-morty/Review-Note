package com.bruce.greedyAlgorithm;

import java.util.Arrays;

/**
 * Author: bruce
 * Date:2021/2/10
 * Version:1.0.0
 */
/*
假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。
如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，
并输出这个最大数值。
示例 1:
输入: g = [1,2,3], s = [1,1]
输出: 1
解释:
你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
所以你应该输出1。
 */
public class Leetcode455_cookie {
    public static  int findContentChildren(int[] g, int[] s) {
        if (s == null || s.length == 0) return 0;
        // 思路：找到最小数组长度  判断s数组中有几个大于g数组的值
        int res = 0;
        int len1 = g.length;
        int len2 = s.length;
        Arrays.sort(g);
        Arrays.sort(s);
        // 用贪心算法
        // 两个循环 定义一个指针从s数组开始 比较与g数组的值
        // for (int i = 0, j = 0; i < len1 && j < len2; i++, j++) {
        //     // 判断s的元素是否大于s
        //     while (j < len2 && g[i] > s[j]) {
        //         j++;
        //     }
        //     //找到比s[i]大的数了 同时找到就把这个孩子满足了 i就++
        //         if(j < len2) {
        //             res++;
        //         }
        //     }
        // 定义两个指针 核心：在s中找到比当前g大的数字
        int sizeLen = 0;
        int greedyLen = 0;
        while (sizeLen < len2 && greedyLen < len1) {
            // 如果当前cookie size大于g的值 则可以遍历下一个greedy值了 greedy++
            if (s[sizeLen] >= g[greedyLen]) {
                // greedy就是s中所有比g中大的元素个数 因为排过序了
                greedyLen++;
            }
            sizeLen++;
        }

        return greedyLen;
    }
}
