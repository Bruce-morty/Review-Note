package com.bruce;

/**
 * Author: bruce
 * Date:2020/11/18
 * Version:1.0.0
 */
public class Test1 {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("Ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
