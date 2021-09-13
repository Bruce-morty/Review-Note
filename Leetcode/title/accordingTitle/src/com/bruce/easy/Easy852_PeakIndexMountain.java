package com.bruce.easy;

/**
 * Author: Qi Gao
 * Date:2021/6/15
 * Version:1.0.0
 */
/*
符合下列属性的数组 arr 称为 山脉数组 ：
arr.length >= 3
存在 i（0 < i < arr.length - 1）使得：
arr[0] < arr[1] < ... arr[i-1] < arr[i]
arr[i] > arr[i+1] > ... > arr[arr.length - 1]
给你由整数组成的山脉数组 arr
返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i
示例 1：
输入：arr = [0,1,0]
输出：1
示例 2：

输入：arr = [0,2,1,0]
输出：1
 */
public class Easy852_PeakIndexMountain {
    public int peakIndexInMountainArray(int[] arr) {
        // 首先要知道什么是山脉 0.若 arr[i - 1] < arr[i] 且 arr[i] > arr[length - 1]
        // 1. 可知第一个位置不能大于第二个 最后一个不能小于倒数第二 且最大不能在最右 最小不能在最左
        int len = arr.length;
        int i = 0;
        while (i < len - 1 && arr[i] < arr[i + 1]) {
            i++;
        }
        if (i == len - 1) return -1;
        if (i < len - 1 && arr[i] > arr[i + 1]) {
            if (i == 0) {
                return - 1;
            } else {return i;}
        }
        return -1;
    }
}
