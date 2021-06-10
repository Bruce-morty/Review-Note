package com.bruce.easy;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Author: bruce
 * Date:2021/2/1
 * Version:1.0.0
 */
/*

爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量(一个人拥有的糖果总量是他们拥有的糖果棒大小的总和）
返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。
如果有多个答案，你可以返回其中任何一个。保证答案存在。
示例 1：
输入：A = [1,1], B = [2,2]
输出：[1,2]
示例 2：
输入：A = [1,2], B = [2,3]
输出：[1,2]
 */
public class Leetcode888 {

    public static int[] fairyCandySwap(int[] a, int[] b) {
        // 用哈希表来存數據，首先想出公式 sumA + x - y = sumB + y - x; x和y是两个数组分别要交换的值
        // 对于数组B来说 若有任意 y符合 x = (sumA - sumB) / 2 + y; 则求出在A中有对应的x可以交换
        int sumA = Arrays.stream(a).sum();
        int sumB = Arrays.stream(b).sum();
        int delta = (sumA - sumB) / 2;
        // 定义哈希表
        HashSet set = new HashSet();
        for(int numsA : a) {
            set.add(numsA);
        }
        int[] res = new int[2];
        for (int numB : b) {
            int x = delta + numB;
            if (set.contains(x)) {
                res[0] = x;
                res[1] = numB;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 5};
        int[] b = {2, 4};
        int[] res = fairyCandySwap(a, b);
        System.out.println(Arrays.toString(res));
    }
}
