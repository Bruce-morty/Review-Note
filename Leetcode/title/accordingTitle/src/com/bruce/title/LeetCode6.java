package com.bruce.title;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Author: bruce
 * Date:2020/10/28
 * Version:1.0.0
 */
public class LeetCode6 {
    /*
        将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     */
    public static String convert(String s, int numRows) {
        if (numRows < 2) return s;
        // 分析，若到了第一行要向下，最后一行需要改变方向往右，即边界是索引 == numRows - 1 or numRows == 0
        ArrayList<StringBuilder> listSb = new ArrayList<>();
        for (int i = 0; i < Math.min(s.length(), numRows); i++) {
            // 把每个行都添加一个空串，然后对每个空行在判断添加
            listSb.add(new StringBuilder());
        }
        int currRow = 0;
        // 给一个改变方向的signal
        boolean goingDown = false;
        for (char str : s.toCharArray()) {
            listSb.get(currRow).append(str);
            if (currRow == 0 || numRows - 1 == currRow) goingDown = !goingDown;
            // 添加当前索引字符
            currRow += goingDown ? 1 : -1;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : listSb) {
            result.append(row);
        }
        return result.toString();
    }

/*    public static String revert1(String s, int numRows) {
        if (numRows < 2) return s;
        // 创建list存放每一行的string
        ArrayList<StringBuilder> list = new ArrayList<>();
        // 将list里添加sb
        for (int i = 0; i < numRows; i++) {
            StringBuilder sb = new StringBuilder();
            list.add(sb);
        }

        // 将string转成字符，定义当前行，判断行数等于0 或者 num - 1 改变方向
        int curr = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            list.get(curr).append(c);
            // curr要递增
            if (curr == 0 || curr == numRows - 1) goingDown = !goingDown;
            curr += goingDown ? 1 : -1;
        }
        StringBuilder sb2 = new StringBuilder();
        for (StringBuilder row : list) {
            sb2.append(row);
        }
        return sb2.toString();
    }*/

    public static String convert2(String s, int nums) {
        if (nums < 2) return s;
        // 定义一个list，长度小于给定行数
        // 每一行都给一个空串
        ArrayList<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < Math.min(nums, s.length()); i++) {
            list.add(new StringBuilder());
        }
        // 定义开始行的位置，和需要改变方向的变量
        int curr = 0;
        int flag = -1;
        // 这里根据字符串的长度一个个添加字符，同时找list的索引，curr是行的索引
        // curr的长度是 < num 没添加一个字符，行都要改变
        for (char c : s.toCharArray()) {
            // e.g 当前在索引为0的位置得到空的sb，append字符
            list.get(curr).append(c);
            if (curr == 0 || curr == nums - 1) flag = -flag;
            curr += flag;
        }
        StringBuilder sb = new StringBuilder();
        for(StringBuilder row : list) {
            sb.append(row);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "abcdef";
        int nums = 3;
        String convert = convert2(s, nums);
        System.out.println(convert);
    }
}
