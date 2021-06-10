package com.bruce.title;

/**
 * Author: bruce
 * Date:2020/10/30
 * Version:1.0.0
 */
/*
Implement atoi which converts a string to an integer.
The string can contain additional characters after those that form the integral number,
which are ignored and have no effect on the behavior of this function.
Example 1:

Input: str = "42"
Output: 42
Example 2:

Input: str = "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign. Then take as many numerical digits as possible, which gets 42.
Example 3:

Input: str = "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.

Example 4:
Input: str = "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical digit or a +/- sign. Therefore no valid conversion could be performed.
Example 5:

Input: str = "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer. Therefore INT_MIN (−231) is returned.
 */
public class Leetcode8 {

    public static int myAtoi(String s) {
        // 重要的是判断边界，像是工作中的参数校验
        // 1.定义start索引，判断是否有空字符，有的话start++，若整个字符为空 return 0
        // 2.定义变量flag，负号的话每次乘以他，判断第一个字符是否为负号，是的话 start++
        // 3.要判断第除了空和正负号第一个字符应该是数字而不是字母
        // 4. 最后判断这个数字不能溢出，判断小于 Integer.MAX_VALUE / 10 大于Integer.MIN_VALUE / 10
        int start = 0;
        int len = s.length();
        char[] chars = s.toCharArray();
        // 越过空格
        while (start < len && chars[start] == ' ' ) {
            start++;
        }
        if (start == len) {
            return 0;
        }
        // 出现符号，记录正负
        int sign = 1;
        if (chars[start] == '-') {
            start++;
            sign = -1;
        } else if (chars[start] == '+'){
            // caution：这里不用else的原因，如果没出现+ 也是正数，但是索引不需要加一
            start++;
        }
        // 开始转换成数字
        int result = 0;
        while (start < len) {
            // 取出第一个字符
            char curr = chars[start];
            if (curr > '9' || curr < '0') {
                break;
            }
            // 判断条件要注意，判断小于最小值的时候，由于curr没有记录符号位，只能转为正数来比较
           if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && (curr -'0') > Integer.MAX_VALUE % 10)) return Integer.MAX_VALUE;
           if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 &&(curr -'0') > -(Integer.MIN_VALUE % 10))) return Integer.MIN_VALUE;
            //if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE && (curr -'0') > 7)) return 0;
            //if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE && (curr -'0') < -8)) return 0;
            // 添加数字之前要判断是否溢出, 将char转换成int 就减去字符0,同时乘上符号位
            result = result * 10 + sign * (curr - '0');
            start++;
        }
        return result;
    }

    public static void main(String[] args) {
       String num = "-91283472332";
        int i = myAtoi(num);
        System.out.println(i);
        String num2 = "   -42";
        int i2 = myAtoi(num2);
        System.out.println(i2);
        String num3 = "4193 with words";
        int i3 = myAtoi(num3);
        System.out.println(i3);
    }
}
