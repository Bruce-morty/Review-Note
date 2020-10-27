package com.bruce.exercise;

import java.util.Arrays;

/**
 * Author: bruce
 * Date:2020/10/19
 * Version:1.0.0
 */
public class Homework1 {
/*
给定一个数组nums和一个值 val，你需要原地移除所有数值等于val的元素，返回移除后数组的新长度。
注意：
	原地的意思是空间复杂度为O(1)，也就是在原数组上进行修改。
	元素的顺序可以改变。
	你不需要考虑数组中超出新长度后面的元素。(新数据是放在数组的前面)
	方法声明如下：
	public int removeElement(int[] nums, int val) {
	}
 */
    public static int removeElement(int[] nums, int val) {
        // 定义两个指针，一开始都从0，i负责遍历，j位置放不相等的元素
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }

    public static int remove(int[] arr, int val) {
        // 定义两个指针，i在0，j在arr.length - 1, j放置与val相等的值
        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            if (arr[i] != val) {
                // 若不相等就直接判断下一个元素，不像上一个做法需要重复赋值
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,3,5};
        int i = remove(nums, 3);
        System.out.println("i = " + i);
        System.out.println(Arrays.toString(nums));
    }
}
