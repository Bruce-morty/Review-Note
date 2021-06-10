package com.bruce.slidingWindow;

/**
 * Author: bruce
 * Date:2021/2/8
 * Version:1.0.0
 */
/*
当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。返回 A 的最大湍流子数组的长度。
示例 1：
输入：[9,4,2,10,7,8,8,1,9]
输出：5
解释：(A[1] > A[2] < A[3] > A[4] < A[5])
示例 2：
输入：[4,8,12,16]
输出：2
示例 3：
输入：[100]
输出：1
 */
public class Leetcode978 {
    // 用双指针解法 确定什么是湍流 如果 A[i] > A[i + 1] && A[i -1] < A[i] 移动右指针
    public static int maxTurbulence(int[] arr) {
        // 定义指针
        int left = 0;
        int right = 0;
        int len = arr.length;
        int maxLen = 1;
        // 滑动指针遍历这个数组 若有不符合湍流数组的需要移动左指针到右指针的位置 同时右指针++
        while (right < len - 1) {
            // 如果是同一个位置的元素 判断和下一个元素的值
            if (left == right) {
                if (arr[left] == arr[right + 1]) {
                    left++;
                }
                // 移动右指针以形成窗口 判断是否是湍流
                right++;
            } else {
                if (arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) {
                    right++;
                } else if (arr[right - 1] > arr[right] && arr[right] < arr[right + 1]) {
                    right++;
                } else {
                    // 都不满足 则移动左指针到right位置 要找到最大的子数组长度 有不符合的就丢弃这个窗口 开辟新窗口
                    left = right;
                }
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = {9,4,2,10,7,8,8,1,9};
        System.out.println(maxTurbulence(arr));
    }

    // 解法2：DP 若一个数组是湍流数组 则它的子数组也是湍流数组 求解子问题
    // 难点：它有两个状态 一个是 arr[i - 1] > arr[i] < arr[i+1]和 arr[i - 1] < arr[i] > arr[i+1]
    public static int dpMaxTurbulence(int[] arr) {
        // 子问题：定义 f(k)为数组 A[0..k) 中，以 A[k-1] 结尾的最长波形子数组。注意这里的附加条件以 A[k-1] 结尾，
        // 这是因为只有最右侧的波形子数组才可以和新加入的上升/下降段连接起来。
        // 定义两个状态 对应最后一段上升还是下降的数组
        int len = arr.length;
        if (len < 2) return len;

        // 以 arr[i] 结尾，并且 arr[i - 1] < arr[i] 的湍流子数组的长度
        int[] increased = new int[len];
        // 以 arr[i] 结尾，并且 arr[i - 1] > arr[i] 的湍流子数组的长度
        int[] decreased = new int[len];
        increased[0] = 1;
        decreased[0] = 1;
        int res = 1;
        // 状态转移: if arr[i-1] < arr[i] decrease[i] = 1 increase[i] = decrease[i - 1] + 1
        // 状态转移: if arr[i-1] > arr[i] increase[i] = 1 decrease[i] = increase[i - 1] + 1 最后是递减 前面递增
        for (int i = 1; i < len; i++) {
            if (arr[i - 1] < arr[i]) {
                increased[i] = decreased[i - 1] + 1;
                decreased[i] = 1;
            } else if (arr[i - 1] > arr[i]) {
                decreased[i] = increased[i - 1] + 1;
                increased[i] = 1;
            } else {
                decreased[i] = 1;
                increased[i] = 1;
            }
            res = Math.max(res, Math.max(increased[i], increased[i]));
        }
        return res;
    }

    /*
    动态规划的优化 由于 dp[i][0] 只和 i-1下标处的dp值有关
     */
    public static int maxTurbulenceSize(int[] arr) {
        int ret = 1;
        int n = arr.length;
        // dp0 代表以arr[i] 结尾 arr[i - 1] > arr[i]的湍流数组的最大长度 dp1...
        int dp0 = 1, dp1 = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] > arr[i]) {
                dp0 = dp1 + 1;
                dp1 = 1;
            } else if (arr[i - 1] < arr[i]) {
                dp1 = dp0 + 1;
                dp0 = 1;
            } else {
                dp0 = 1;
                dp1 = 1;
            }
            ret = Math.max(ret, dp0);
            ret = Math.max(ret, dp1);
        }
        return ret;
    }

}
