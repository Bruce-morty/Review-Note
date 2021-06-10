package com.bruce.easy;

import java.util.Arrays;

/**
 * Author: bruce
 * Date:2021/2/1
 * Version:1.0.0
 */
/*
给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。

如果不能形成任何面积不为零的三角形，返回 0。
示例 1：

输入：[2,1,2]
输出：5

 */
public class Leetcode976 {
    // 不失一般性，我们假设三角形的边长满足 a <= b <= c，那么这三条边组成面积不为零的三角形的充分必要条件为
    // a + b > c
    //基于此，我们可以选择枚举三角形的最长边 c，而从贪心的角度考虑，我们一定是选「小于 c 的最大的两个数」作为边长 a和b
    // 此时最有可能满足 a + b > c，使得三条边能够组成一个三角形，且此时的三角形的周长是最大的。

    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public static int largestPerimeter(int[] a) {
        // 排序
        Arrays.sort(a);
        for (int i = a.length - 1; i >= 2; i--) {
            // 要找出最长的周长 即找到 小于最长边 c 的 最长两条边
            if (a[i] < a[i - 1] + a[i - 2]) {
                return a[i] + a[i - 1] + a[i - 2];
            }
        }
        return 0;
    }
}
