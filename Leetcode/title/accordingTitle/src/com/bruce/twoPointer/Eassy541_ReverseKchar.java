package com.bruce.twoPointer;

/**
 * Author: Qi Gao
 * Date:2021/8/20
 * Version:1.0.0
 */
/*
541. 反转字符串 II
给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
如果剩余字符少于 k 个，则将剩余字符全部反转。
如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
示例 1：
输入：s = "abcdefg", k = 2
输出："bacdfeg"
示例 2：
输入：s = "abcd", k = 2
输出："bacd"
 */
public class Eassy541_ReverseKchar {

    /*
    重点：0. 有三个区间 [,k], [k,2k], [2k,].
        1. 我们要以 + 2k 的index为递增的循环条件
        2. 反转字符数组[i, k] 若超过len，取len - 1
     */
    public String reverseStr(String s, int k) {
        char[] ch = s.toCharArray();
        int len = ch.length;
        for (int i = 0; i < len; i += 2 * k) {
            // 若len 小于 k，把整个数组传过去反转前 k 个，然后 i += 2k > len 退出循环 符合
            // k <= len < 2k, 把数组前k个反转，后面的不变。i 递增 > len 退出loop
            reverse(ch, i, Math.min(i + k, len) - 1);
        }
        return new String(ch);
    }

    private void reverse(char[] ch, int left, int right) {
        while (left < right) {
            char temp = ch[left];
            ch[right] = ch[left];
            ch[left] = temp;
            left++;
            right--;
        }
    }
}
