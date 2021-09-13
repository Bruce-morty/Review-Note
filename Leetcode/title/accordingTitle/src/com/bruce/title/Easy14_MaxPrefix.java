package com.bruce.title;

/**
 * Author: Qi Gao
 * Date:2021/9/9
 * Version:1.0.0
 */
/*
14. 最长公共前缀
编写一个函数来查找字符串数组中的最长公共前缀。
如果不存在公共前缀，返回空字符串 ""
示例 1：

输入：strs = ["flower","flow","flight"]
输出："fl"
示例 2：

输入：strs = ["dog","racecar","car"]
输出：""
解释：输入不存在公共前缀。
 */
public class Easy14_MaxPrefix {
    public String longestCommonPrefix(String[] strs) {
        // 判断每个字是否由下一个字符起始
        if (strs.length == 0) return "";
        String s = strs[0];
        for (String tmp : strs) {
            // 一开始不会进来， 因为自己包含自己 若遇到比自己大的也skip，比自己小的要重新取prefix
            // 拿到每个字符 判断是否是由最小字符开始
            while (!tmp.startsWith(s)) {
                // 判断数组中是否有空字符
                if (s.length() == 0) return "";
                // 缩小s 不匹配让它减小  一直到匹配为止 退出while
                s = s.substring(0, s.length() - 1);
            }
        }
        return s;
        // time complexity: O(m * n) m是字符的长度，n是字符数组的长度
    }

    /*
    类似的写法，思路就是 固定i, 把 i + 1, i + 2 ... str[len-1] 字符进行比较

     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0)
            return "";
        String ans = strs[0];
        for (int i = 1; i < strs.length; i++) {
            // 把j提出来了，ans是最长公共字符串
            int j = 0;
            // 判断条件是不能大于当前最大公共字符长度, 要所有都包含 大于了就break loop
            for (; j < ans.length() && j < strs[i].length(); j++) {
                // 当前的字符j 与起始字符i判断 有不合的 break
                if (ans.charAt(j) != strs[i].charAt(j))
                    break;
            }
            // break 或者遍历结束后就更新ans
            ans = ans.substring(0, j);
            if (ans.equals(""))
                return ans;
        }
        return ans;
    }

    /*
    纵向扫描, 把所有字符从上往下排列，然后比较第一个字符相等，再往后判断。直到有不等的 就return
    好好思考 遍历的过程, 从上到下 loop是怎么包含的关系
     */
    public String longestPrefix3(String[] strs) {
        if (strs == null || strs.length < 1) return "";
        int len = strs.length;
        // 从上到下进行比较 遍历顺序是先拿到string[0]
        // 如果要包含所有的最大公共字符就够了, 肯定不能大过它
        int count = strs[0].length();
        for (int i = 0; i < count; i++) {
            // 比较当前的元素 e.g. leet  lee le 一开始比较 l 拿到c = l
           char c = strs[0].charAt(i);
           // 再对字符数组长度遍历 遍历每一个字符
            for (int j = 1; j < len; j++) {
                // i等于当前字符的长度了
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
