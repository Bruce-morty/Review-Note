package com.bruce.exercise;

import java.util.Arrays;

/**
 * Author: bruce
 * Date:2020/10/20
 * Version:1.0.0
 */
/*
给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
注意：
	最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
	你可以假设除了整数 0 之外，这个整数不会以零开头。
	方法声明如下：
	public int[] plusOne(int[] digits) {
 	}
 */
public class Homework2 {
    public static int[] plusOne(int[] digits) {
        // 取到每一位的数字，从最后一位开始加1，然后判断有没有进位
        // 1. 定义一个进位变量carry = 1 2. 循环条件, i < arr.length
        int carry = 1;
        int n = digits.length - 1;
        // n得合法且carry == 1才进入循环
        while (n >= 0 && carry == 1) {
            int sum = digits[n] + carry;
            digits[n--] = sum % 10;
            carry = sum / 10;
        }
        /*for (int i = digits.length - 1; i >= 0; i--) {
            // 取到个位上的数字
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
        }*/
        if (carry == 1) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }
        return digits;
    }

    public static void main(String[] args) {
        int[] digits = {1, 2, 3};
        plusOne(digits);
        System.out.println(Arrays.toString(digits));

       /* int[] digits = {1, 9, 9};
        int[] result = plusOne(digits);
        System.out.println(Arrays.toString(digits));
        System.out.println(Arrays.toString(result));*/
    }
}
