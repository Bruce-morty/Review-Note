package com.bruce.exercise;

/**
 * Author: bruce
 * Date:2020/5/12
 * Version:1.0.0
 */
/*
3. 给定两个字符串表示的非负整数num1, num2，计算它们的和。
注意：
	a. num1和num2的长度 < 5100.
	b. 字符串num1和num2只包含数字 0-9.
	c. num1 和 num2中不包含前置的0数字
	d. 不能用内置的 BigInteger 类, 且不能将输入直接转换成整数类型。
实现该方法： public String addStrings(String num1, String num2)

分析：
    int len1 = num1.length();
    int len2 = num2.length();

 */
public class Homeword2 {
    public static String addStrings(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        char[] result = new char[Math.max(len1, len2) + 1];
        // 进位的值
        int carry = 0;
        for (int i = 0; i < result.length; i++) {
            // 减去字符0，转换成int
            // 取出第一位上的数
            int c1 = i < len1 ? num1.charAt(len1 - 1 -i) - '0' : 0;
            int c2 = i < len2 ? num2.charAt(len2 - 1 - i) - '0' : 0;
            int sum = c1 + c2 + carry;
            // 放回数组中
            result[result.length - 1 - i] = (char)(sum % 10 + '0');
            carry = sum / 10;
        }
        // 把数组变成字符串
        // 可能进位为0
        return result[0] == 0 ? String.valueOf(result, 1, result.length - 1) :
                String.valueOf(result);
    }

    public static void main(String[] args) {
        String s1 = "987654321";
        String s2 = "20191010";
        System.out.println(addStrings(s1, s2));
    }
}
