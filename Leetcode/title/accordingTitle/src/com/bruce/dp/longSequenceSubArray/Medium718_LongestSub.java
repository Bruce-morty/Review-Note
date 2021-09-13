package com.bruce.dp.longSequenceSubArray;

/**
 * Author: Qi Gao
 * Date:2021/7/8
 * Version:1.0.0
 */
/*
718. 最长重复子数组
给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
示例：

输入：
A: [1,2,3,2,1]
B: [3,2,1,4,7]
输出：3
解释：
长度最长的公共子数组是 [3, 2, 1] 。
 */
public class Medium718_LongestSub {

    /*
    用动规去解决 首先确定状态 遍历两个数组的元素
    定义dp含义, dp[i][j]: 以i结尾的 num1[:i] 以j结尾的num[:j] 公共的子数组长度
    状态转移：若num1[i] != num2[j] 说明末尾不等 则dp[i][j]的公共子数组只能为0 子数组长度要包括i j
               num1[i] == num2[j] 则再判断dp[i - 1][j - 1]的值
               若前面子数组的末尾相同（num1[i - 1] == num2[j - 1]）则 dp[i-1][j-1] + 1
               若不同 则dp[i - 1][j - 1] = 0. dp[i][j] = 0 + 1
     要找到最大子数组长度 每次得到dp[i][j] 后保存下来 和之前的max比较
     */
    public static int findLenth(int[] num1, int[] num2) {
            int len1 = num1.length;
            int len2 = num2.length;
            int[][] dp = new int[len1 + 1][len2 + 1];
            int res = 0;
            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (num1[i - 1] == num2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    res = Math.max(res, dp[i][j]);
                }
            }
            return res;
    }

    /*
    滑动窗口解法
    我们可以枚举 A 和 B 所有的对齐方式。对齐的方式有两类：第一类为 A 不变，B 的首元素与 A 中的某个元素对齐
    第二类为 B 不变，A 的首元素与 B 中的某个元素对齐。对于每一种对齐方式，我们计算它们相对位置相同的重复子数组即可
     */
    public int findLength(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n - i);
            int maxlen = maxLength(A, B, i, 0, len);
            ret = Math.max(ret, maxlen);
        }
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int maxlen = maxLength(A, B, 0, i, len);
            ret = Math.max(ret, maxlen);
        }
        return ret;
    }

    public int maxLength(int[] A, int[] B, int addA, int addB, int len) {
        int ret = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (A[addA + i] == B[addB + i]) {
                k++;
            } else {
                k = 0;
            }
            ret = Math.max(ret, k);
        }
        return ret;
    }
}
