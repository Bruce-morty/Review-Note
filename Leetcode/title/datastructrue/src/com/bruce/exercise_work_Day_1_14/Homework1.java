package com.bruce.exercise_work_Day_1_14;

/**
 * Author: bruce
 * Date:2020/5/12
 * Version:1.0.0
 */
/*
2. 统计中单词的个数。不是空格的连续字符序列我们就认为是单词
举例：
Input: “Hello, my name is John”
Output: 5
分析：
    a. 定义一个统计变量 wordCount = 0;
    b.
    int len = s.length();
    for(int i = 0; i < len; i++) {
        char c = s.charAt(i);
        if(c != ' ' && (i == 0 || s.charAt(i - 1) == ' ')) {
            wordCount++;
        }
    }
 */
public class Homework1 {

    public static void main(String[] args) {
        String s = "Hello, my name is John";
        System.out.println(wordCount(s));
    }

    public static int wordCount(String s) {
        int wordCount = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            // 提取字符
            char c = s.charAt(i);
            // c不等于空且是第一个字符或者是这个字符之前的一个字符为空，则断定为一个单词
            if (c != ' ' && (i == 0 || s.charAt(i - 1) == ' ')) {
                wordCount++;
            }
        }
        return wordCount;
    }
}
