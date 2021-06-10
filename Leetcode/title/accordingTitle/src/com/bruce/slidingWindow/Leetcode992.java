package com.bruce.slidingWindow;

/**
 * Author: bruce
 * Date:2021/2/9
 * Version:1.0.0
 */
/*
给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
（例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）返回 A 中好子数组的数目。
示例 1：
输入：A = [1,2,1,2,3], K = 2
输出：7
解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
示例 2：
输入：A = [1,2,1,3,4], K = 3
输出：3
解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 */
public class Leetcode992 {
    /*
    把「恰好」改成「最多」就可以使用双指针一前一后交替向右的方法完成，这是因为 对于每一个确定的左边界，最多包含 K 种不同整数
    的右边界是唯一确定的，并且在左边界向右移动的过程中，右边界或者在原来的地方，或者右移。而「最多存在 K 个不同整数的子区间的
    个数」与「恰好存在 K 个不同整数的子区间的个数」的差恰好等于「最多存在 K - 1 个不同整数的子区间的个数」。
     */
    public static int subarraysWithKDistinct(int[] A, int K) {
        return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
    }

    /**
     * 求最多有K个不同整数的子区间的个数
     * @param arr
     * @param a
     * @return 最多包含K个不同整数的子区间的个数
     */
    private static int atMostKDistinct(int[] arr, int a) {
        // 例如[1,2,3,4] 找到有4个不同正数的子区间。固定1遍历到4时 不同整数的子区间: [1] [1,2] [1,2,3] [1,2,3,4]
        // 使用双指针来遍历数组 同时定义一个freq数组确定每个值在数组中重复次数 索引 1- len 左闭右闭
        int len = arr.length;
        int[] freq = new int[len + 1];
        int left = 0;
        int right = 0;
        // 定义返回的值
        int res = 0;
        // 定义数组中不同整数的最大
        int count = 0;
        while (right < len) {
            if (freq[arr[right]] == 0) {
                // 这个值在数组中第一次出现 无重复
                count++;
            }
            freq[arr[right]]++;
            right++;
            // 用while循环 直到count小于a 因为可能有很多重复的整数 需要用while
            while (count > a) {
                //移出左指针的元素频率
                freq[arr[left]]--;
                // 若在整个窗口中只出现一次 可以将count值降低
                if (freq[arr[left]] == 0) {
                    count--;
                }
                left++;
            }
            // 理解这一步重要！！退出内层循环-> count <= a 即求出最多有k个不同整数的子区间的个数
            res += right - left;
        }
        return 0;
    }

}
