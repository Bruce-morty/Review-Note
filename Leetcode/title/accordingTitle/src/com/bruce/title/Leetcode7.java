package com.bruce.title;

/**
 * Author: bruce
 * Date:2020/10/29
 * Version:1.0.0
 */
public class Leetcode7 {
    // 反转一个整数,若大于 2^31 或者小于 -2^31 返回0
    public static int reverse(int num) {
        if (num == 0) return 0;
        // 要考虑溢出的问题，如果最后一位为9的话反转可能会溢出。
        // 不用转换成字符串，可以定义另一个整数去添加它取余出来的数
        int reverNum = 0;
        // 如果有负数的话也要继续反转
        while (num != 0) {
            int carry = num % 10;
            num /= 10;
            // 若是1234567 12345668 前一位数字倒数第一位7大于后一位除以10之后的最后一位6，反转肯定溢出
            // 反转后是 7654321 大于1234566
            if (reverNum > Integer.MAX_VALUE / 10 || (reverNum == Integer.MAX_VALUE / 10 && carry > 7)){
                return 0;
            }
            if (reverNum < Integer.MIN_VALUE /10 || (reverNum == Integer.MIN_VALUE / 10 && carry < -8)) {
                return 0;
            }
            // 每次进位都要*10, 要在之前判断反转后溢出，然后进位添加
            reverNum = reverNum * 10 + carry;
        }
        return reverNum;
    }

    public static void main(String[] args) {
        int num = -2147483642;
        int reverse = reverse(num);
        System.out.println(reverse);

    }
}
