package com.bruce;

/**
 * Author: bruce
 * Date:2020/10/23
 * Version:1.0.0
 */
/*
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

Follow up: The overall run time complexity should be O(log (m+n)).
 */
public class Leetcode4 {

    // find the median = find the smallest k
    public static double search(int[] arr1, int[] arr2) {
        //写一个方法，返回那个索引
        int len1 = arr1.length;
        int len2 = arr2.length;
        int totalLength = len1 + len2;
        // 判断奇偶

        if (totalLength % 2 == 1) {
            int middle1 = totalLength / 2;
            // middle 代表的不是索引,是所在位置,第几大的元素
            double elements = middleElement(arr1, arr2, middle1 + 1);
            return elements;
        } else {
            int middle1 = totalLength / 2 - 1;
            int middle2 = totalLength / 2;
            double elements = (middleElement(arr1, arr2, middle1 + 1) + middleElement(arr1, arr2, middle2 + 1)) / 2.0;
            return elements;
        }
    }

    private static int middleElement(int[] arr1, int[] arr2, int middle) {
        // 首先定义两个变量，两个数组索引的起始值
        int start1 = 0;
        int start2 = 0;
        int len1 = arr1.length;
        int len2 = arr2.length;
        while (true) {
            if (start1 == len1) {
                return arr2[start2 + middle - 1];
            }
            if (start2 == len2){
                return arr1[start1 + middle - 1];
            }
            // 最后将middle减小到1， 因为之前已经排除了 k - 1个元素
            // 现在两个索引相当于长度为2，这两个之间最小就是中间值
            if (middle == 1) {
                return Math.min(arr1[start1], arr2[start2]);
            }
            // 前面是边界，现在是正常状态下排除元素
            int half = middle / 2;
            int newIndex1 = Math.min(start1 + half, len1) - 1;
            int newIndex2 = Math.min(start2 + half, len2) - 1;
            int pivot1 = arr1[newIndex1];
            int pivot2 = arr2[newIndex2];
            // 判断哪个大
            if (pivot1 <= pivot2) {
                // arr1的前面元素要被排除
                // middle = middle - (最新的元素索引 - 被排除时的开始索引 + 1 越过被排除元素
                middle -= (newIndex1 - start1 + 1);
                start1 = newIndex1 + 1;
            } else {
                middle -= (newIndex2 - start2 + 1);
                start2 = newIndex2 + 1;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {8,11, 13, 15, 16};
        double value = findMedianSortedArrays(arr1, arr2);
        double value2 = search(arr1, arr2);
        System.out.println(value);
        System.out.println(value2);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // define the length
        int start1 = 0;
        int start2 = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        int end1 = len1 - 1;
        int end2 = len2 - 1;
        int middleIndex1 = (len1+len2 + 1) / 2;
        int middleIndex2 = (len1 + len2 + 2) / 2;
        // return even of the two middle
        int result1 = getKth(nums1, start1, end1, nums2, start2, end2, middleIndex1);
        int result2 = getKth(nums1, start1, end1, nums2, start2, end2, middleIndex2);
        return (result1 + result2) / 2.0;
    }

    public static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int middleIndex) {
        // edge
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        if(len1 > len2) {
            return getKth(nums2, start2, end2, nums1, start1, end1, middleIndex);
        }
        if(len1 == 0) {
            return nums2[start2 + middleIndex - 1];
        }
        if(middleIndex == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        // define new startIndex
        int half = middleIndex / 2;
        int newStart1 = start1 + Math.min(half, len1) - 1;
        int newStart2 = start2 + Math.min(half, len2) - 1;
        // 下面这种写法不行，因为len1和len2一直都是减小的
        //int newStart1 = Math.min(start1 + half, len1) - 1;
        //int newStart2 = Math.min(start2 + half, len2) - 1;
        if(nums1[newStart1] > nums2[newStart2]) {
            return getKth(nums1, start1, end1, nums2, newStart2 + 1, end2, middleIndex - (newStart2 - start2 + 1));
        } else{
            return getKth(nums1, newStart1 + 1, end1, nums2, start2, end2, middleIndex - (newStart1 - start1 + 1));
        }
    }
}
