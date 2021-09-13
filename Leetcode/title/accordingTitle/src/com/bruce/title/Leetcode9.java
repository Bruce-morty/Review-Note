package com.bruce.title;

/**
 * Author: bruce
 * Date:2020/10/30
 * Version:1.0.0
 */
/*
判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

示例 1:

输入: 121
输出: true
示例 2:

输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
示例 3:

输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
 */
public class Leetcode9 {
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        // 1.定义num 从个位开始添加，相当于一个反转的int，判断是否相等
        int curr = 0;
        int num = x;
        while (num != 0) {
            curr = curr * 10 + num % 10;
            num /= 10;
        }
        return curr == x;
    }

    public static boolean isPalindrome2(int x) {
        if (x < 0) return false;
        // 定义一个数从最个位与首位开始比较
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        // 若最后x递减到小于0则肯定是回文数
        while (x > 0) {
            // 得到首位
            int a = x / div;
            int b = x % 10;
            if (a != b) return false;
            // 比较完两位，要除100
            // 得到新位数比较麻烦，首先对div取余，然后得到了去除第一位的数，再除以10去除个位
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    // 将整数反转，判断是否相等
    public static boolean isPalindrome3(int x) {
        // 这里判断末尾为0就不是回文，是因为第一位肯定不是0
        if (x < 0 || x % 10 == 0 && x != 0) return false;
        int reverNum = 0;
        // 注意判断条件，只取一半
        while (x > reverNum) {
            reverNum = reverNum * 10 + x % 10;
            x /= 10;
        }
        // 最后判断reverNum和x，由于长度有可能偶数和奇数, 奇数需要把原数整除10，去掉一位
        // 因为原来的x的中位数变成最后一位
        return reverNum == x || x == reverNum / 10;
    }

    public static void main(String[] args) {
        int num = 2002;
        System.out.println(isPalindrome2(num));
    }
}
