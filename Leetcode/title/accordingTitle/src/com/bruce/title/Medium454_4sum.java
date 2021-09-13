package com.bruce.title;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: bruce
 * Date:2021/3/4
 * Version:1.0.0
 */
/*
给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0
为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，
最终结果不会超过 231 - 1
例如:
输入:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]
输出:
2
解释:
两个元组如下:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0

 */
public class Medium454_4sum {

    /*
    要解决类似的不同数组的相加问题：n个数组 首先可以找到前t 个 数组和 记录下来 在n - t中找到target
    总结，看到形如：A+B....+N=0的式子，要转换为(A+...T)=-((T+1)...+N)再计算，这个T的分割点一般是一半，
    特殊情况下需要自行判断。定T是解题的关键。
     */
    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        // 定义count值 找到有几个target
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                // 计算两个数字的和
                int sumAB = A[i] + B[j];
                if (map.containsKey(sumAB)) {
                    // 有多个相同的值 所以次数要++
                    map.put(sumAB, map.get(sumAB) + 1);
                }else {
                    map.put(sumAB, 1);
                }
            }
        }

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sumCD = -(C[i] + D[j]);
                if (map.containsKey(sumCD)) count += map.get(sumCD);
            }
        }
        return count;
    }

}
