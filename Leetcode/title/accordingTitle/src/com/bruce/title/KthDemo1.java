package com.bruce.title;

/**
 * Author: bruce
 * Date:2020/11/3
 * Version:1.0.0
 */
/*
给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
示例 1：
输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2
示例 2：
输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 */
public class KthDemo1 {
    public static double middleElement(int[] arr1, int[] arr2) {
        // 写一个递归的方法，计算两个数组的中位数，时间复杂度为log(n)
        int start1 = 0;
        int start2 = 0;
        int end1 = arr1.length - 1;
        int end2 = arr2.length - 1;
        int len1 = arr1.length;
        int len2 = arr2.length;
        // 为了考虑奇偶，直接找两个中位数
        int middle1 = (len1 + len2) / 2 + 1;
        int middle2 = (len1 + len2) / 2;
        // 写一个方法
        int element1 = findKth(arr1, start1, end1, arr2, start2, end2, middle1);
        int element2 = findKth(arr1, start1, end1, arr2, start2, end2, middle2);
        if ((len1 + len2) % 2 == 1) {
            return element1;
        }else {
            return (element1 + element2) / 2.0;
        }
    }

    private static int findKth(int[] arr1, int start1, int end1, int[] arr2, int start2, int end2, int middle) {
        // 判断边界,不用单独判断，把所有长度小的数组放在到数组1
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        if (len1 > len2) {
            return findKth(arr2, start2, end2, arr1, start1, end2, middle);
        }
        if (len1 == 0) {
            return arr2[start2 + middle - 1];
        }
        if (middle == 1) {
            return Math.min(arr1[start1], arr2[start2]);
        }
        // 边界搞定，开始减小数组, 若数组长度过小，half大于长度会越界
        int half = middle / 2;
        int newStart1 = start1 + Math.min(half, len1) - 1;
        int newStart2 = start2 + Math.min(half, len2) - 1;
        if (arr1[newStart1] > arr2[newStart2]) {
            return findKth(arr1, start1, end1, arr2, newStart2 + 1, end2, middle - (newStart2 - start2 + 1));
        } else {
            return findKth(arr1, newStart1 + 1, end1, arr2, start2, end2, middle - (newStart1 - start1 + 1));
        }
    }

    /*public static double middle(int[] arr1, int[] arr2) {
        // 用loop解决, 需要用到排序
        int totalLength = arr1.length + arr2.length;
        // 判断奇偶
        if (totalLength % 2 == 1) {
            // 直接除以2
            int index = totalLength / 2;
            return middleSearch(arr1, arr2, index + 1);
        } else {
            int index1 = totalLength / 2;
            int index2 = totalLength / 2 - 1;
            return (middleSearch(arr1, arr2, index1 + 1) + middleSearch(arr1, arr2, index2 + 1)) / 2.0;
        }
    }

    private static double middleSearch(int[] arr1, int[] arr2, int middle) {
        int start1 = 0;
        int start2 = 0;
        int len1 = arr1.length;
        int len2 = arr2.length;
        while (true) {
            // 边界
            if (start1 == len1) return arr2[start2 + middle - 1];
            if (start2 == len2) return arr1[start1 + middle - 1];
            // 判断middle 最小了，比较两个数组中当前最小的是谁，返回
            if (middle == 1) return Math.min(arr1[start1], arr2[start2]);
            // 将middle进行缩小，比较每个数组的middle位置的值
            int half = middle / 2;
            // 判断当前元素, 有可能加了half会超过数组长度
            int newIndex1 = Math.min(start1 + half, len1) - 1;
            int newIndex2 = Math.min(start2 + half, len2) - 1;
            int pivot1 = arr1[newIndex1];
            int pivot2 = arr2[newIndex2];
            if (pivot1 <= pivot2) {
                // 将middle缩小, 要得到这个索引的长度 = 当前 - 开始 + 1
                middle -= (newIndex1 - start1 + 1);
                start1 = newIndex1 + 1;
            } else {
                middle -= (newIndex2 - start2 + 1);
                start2 = newIndex2 + 1;
            }
        }
    }*/

    public static void main(String[] args) {
        int[] arr1 = {1,3};
        int[] arr2 = {2};
        double value = middleElement(arr1, arr2);
        // double value2 = middle(arr1, arr2);
        System.out.println(value);
        // System.out.println(value2);
    }
}
