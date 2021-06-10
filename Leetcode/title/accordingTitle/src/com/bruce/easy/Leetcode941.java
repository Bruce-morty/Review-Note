package com.bruce.easy;

/**
 * Author: bruce
 * Date:2020/11/21
 * Version:1.0.0
 */
/*
有效的山脉数组：
    让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
A.length >= 3
在 0 < i < A.length - 1 条件下，存在 i 使得：
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[A.length - 1]
示例 1：
输入：[2,1]
输出：false
示例 2：

输入：[3,5,5]
输出：false
示例 3：

输入：[0,3,2,1]
输出：true
 */
public class Leetcode941 {

    public static boolean validMountainArray(int[] A) {
        // 首先定义索引，从左开始判断, 最后返回这个索引是否等于数组长度，等于说明符合遍历的条件 return true
        int len = A.length;
        int i = 0;
        // 从左开始判断
        while (i + 1 < len && A[i] < A[i + 1]) {
            i++;
        }
        // 在这里判断一下i的位置，要知道山脉数组最大值不能在开头和结尾
        if (i == 0 || i == len - 1) {
            return false;
        }
        // 判断右边
        while (i + 1 < len && A[i] > A[i + 1]) {
            i++;
        }
        // 遍历完数组，返回i 是否是最后一个索引值，如果是前面的条件都符合 前半部 A[i] < A[i+1] 后半 A[i] > A[i+1]
        return i == len - 1;
    }
}
