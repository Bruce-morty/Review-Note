package com.bruce.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Qi Gao
 * Date:2021/9/1
 * Version:1.0.0
 */
/*
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母
示例 1：

输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
示例 2：

输入：digits = ""
输出：[]
示例 3：

输入：digits = "2"
输出：["a","b","c"]
 */
public class Medium17_CombinationOfPhone {
    // 一般来说 看到combination 会自然想到回溯
    // 创建一个数组 保存每个数字代表的字母， 用map
    private List<String> list = new ArrayList<>();
    private String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private StringBuilder sb = new StringBuilder();
    /*

     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return list;
        backtrack(digits, 0);
        return list;
    }

    public void backtrack(String digits, int index) {
        // 边界 判断长度
        if (digits.length() == index) {
            list.add(sb.toString());
            return;
        }
        // get 当前的路径
        char c = digits.charAt(index);
        String tmp = map[c - '2'];
        // 对这个tmp的字母进行遍历和回溯 回溯
        // 重点 sb作为撤销选择的对象 list是保存结果的结果集
        for (int i = 0; i < tmp.length(); i++) {
            sb.append(tmp.charAt(i));
            backtrack(digits, index + 1);
            sb.deleteCharAt(index);
        }
    }

    /*
    方法2 与 1 是一样的，只是把变量 当成参数放到了方法里面
     */
    public List<String> letterCombinations2(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> map = new HashMap<Character, String>(){
            {
                put('2', "abc");
                put('3', "def");
                put('4', "ghi");
                put('5', "jkl");
                put('6', "mno");
                put('7', "pqrs");
                put('8', "tuv");
                put('9', "wxyz");
            }
        };
        // backtrack method to add the combination to list
        backtrack(combinations, map, digits, 0, new StringBuilder());
        return combinations;
    }

    private void backtrack(List<String> combinations, Map<Character, String> map, String digit, int index, StringBuilder sb) {
        // backtrack edge case
        int len = digit.length();
        if (index == len) {
            combinations.add(sb.toString());
            return;
        } else {
            char c = digit.charAt(index);
            String tmp = map.get(c);
            int n = tmp.length();
            for (int i = 0; i < n; i++) {
                sb.append(tmp.charAt(i));
                backtrack(combinations, map, digit, index + 1, sb);
                sb.deleteCharAt(index);
            }
        }
    }
}
