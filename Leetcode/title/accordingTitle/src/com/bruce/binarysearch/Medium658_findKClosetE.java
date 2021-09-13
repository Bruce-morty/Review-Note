package com.bruce.binarysearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/7/5
 * Version:1.0.0
 */
/*
658. 找到 K 个最接近的元素
给定一个排序好的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
整数 a 比整数 b 更接近 x 需要满足：
|a - x| < |b - x| 或者
|a - x| == |b - x| 且 a < b

示例 1：
输入：arr = [1,2,3,4,5], k = 4, x = 3
输出：[1,2,3,4]
示例 2：
输入：arr = [1,2,3,4,5], k = 4, x = -1
输出：[1,2,3,4]
 */
public class Medium658_findKClosetE {
    /*
    首先我们讨论左区间的取值范围，使用具体的例子，就很很清楚地找到规律：
    1、假设一共有 5 个数，不管 x 的值是多少，在 [0, 1, 2, 3, 4]，找 3 个数，左边界最多到 2；
    2、假设一共有 8 个数，不管 x 的值是多少，在 [0, 1, 2, 3, 4, 5, 6, 7]，找 5 个数，左边界最多到 3
    因此，「最优区间的左边界」的下标的搜索区间为 [0, size - k]。注意，这个区间的左右都是闭区间，都能取到
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 用二分法 找到这个k区间的左边界索引
        List<Integer> res = new ArrayList<>();
        int len = arr.length;
        int left = 0;
        int right = len - k;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            // 找到最符合x数的左端点之后 + k 就是区间内的数 怎么找左端点
            // 因为是有序的 x - arr[mid] 肯定大于 x - arr[mid + k]
            if (x - arr[mid] > arr[mid + k]  - x) {
                // 若大于 说明左端点的值离x太远 不是最优的左端点
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // left == right;
        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }
    /*
    time complexity: O(logN + K) N是数组的长度
    space complexity: O(1)
     */
}
