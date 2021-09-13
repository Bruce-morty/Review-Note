package com.bruce.easy;

/**
 * Author: bruce
 * Date:2020/11/23
 * Version:1.0.0
 */
/*
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
说明:
必须在原数组上操作，不能拷贝额外的数组。
尽量减少操作次数。
 */
public class leetcode283 {
    public static  void moveZeroes(int[] arr) {
        // 定义两个指针，我一开始想的是j提前移动一个位置，不为0就交换 并且++
        // 这种写法有问题，多花了一步判断交换后的元素，交换 i和j之后还要判断交换后的是否还是0 这个方法不好
        // 因为我把j定义成下一个元素了，这样子不知道下一个元素的值是不是0 应该两个指针从头开始
        int i = 0;
        int j = 1;
        while (j < arr.length) {
            // 这判断左指针
            if (arr[i] == 0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                // 同时交换完了要判断i的值，i如果还是0就不动
                if (arr[i] != 0) i++;
            }
            j++;
        }

        // 第二种是i和j同时从0开始，i代表左指针，都不为0的数，j的左边到i都是为0
        int left = 0;
        int right = 0;
        while (right < arr.length) {
                // 判断右指针是不是不为0
                if (arr[right] != 0) {
                    int temp = arr[left];
                    arr[left] = arr[right];
                    arr[right] = temp;
                    left++;
            }
            right++;
        }
    }
}
