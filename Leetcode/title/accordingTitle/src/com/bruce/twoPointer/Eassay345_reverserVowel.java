package com.bruce.twoPointer;

/**
 * Author: Qi Gao
 * Date:2021/8/19
 * Version:1.0.0
 */
/*
345. 反转字符串中的元音字母
编写一个函数，以字符串作为输入，反转该字符串中的元音字母
示例 1：
输入："hello"
输出："holle"
示例 2：

输入："leetcode"
输出："leotcede"
 */
public class Eassay345_reverserVowel {

    /*
    反转：我们可以想到
    0.用栈
    1. 还有构建新数组存位置 一个个遍历
    2. 双指针，头尾进行交换 逆序
     */
    public String reverseVowels(String s) {
        // 思路：双指针， 一个在头一个在尾，一开始从头开始 碰到元音就停下
        int left = 0;
        int len = s.length();
        int right = len - 1;
        char[] arr = s.toCharArray();
        while (left < right) {
            while (left < right && !isVowel(arr[left])) {
                left++;
            }
            // 移动右指针 找到元音字母就停下
            while(left < right && !isVowel(arr[right])) {
                right--;
            }
            // 判断left是否小于right 小于的话就交换 说明还没遍历完
            if (left < right) {
                swap(arr, left, right);
                // 交换 以后left和right也要改变 说明已经交换过了 判断下一个元素
                left++;
                right--;
            }
        }
        return new String(arr);
    }

    public boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch) >= 0;
    }

    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
