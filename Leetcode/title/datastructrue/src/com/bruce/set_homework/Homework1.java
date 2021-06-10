package com.bruce.set_homework;

import java.util.Arrays;

/**
 * Author: bruce
 * Date:2020/10/22
 * Version:1.0.0
 */
/*
给定一个已经排好序的数组 nums, 原地删除重复的元素，并返回删除重复元素后元素的个数。
    比如：
    nums = [1, 1, 2]
    返回 2，并要求数组的前两个元素分别为 1 和 2.
 */
public class Homework1 {
    public static int removeDuplicated(int[] arr) {
        // 要定义两个指针，i一个用来遍历，j一个是不相等元素的索引
        // 1.设从index = 1开始，若不等于index - 1就继续遍历(i++, j++) 否则j不动
        int j = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[j - 1]) {
                arr[j] = arr[i];
                j++;
            }
        }
        return j;
    }

    public static int remove2(int[] arr) {
        // 另一种双指针定义
        int i = 0;
        for (int j = 1; j < arr.length; j++) {
            // j 用来遍历链表，i位置放不相等的元素
            if (arr[i] != arr[j]) {
                // caution 不相等的话i要自增 然后覆盖
                i++;
                arr[i] = arr[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 2, 3};
        System.out.println(removeDuplicated(nums));
        System.out.println(Arrays.toString(nums));
    }
}
