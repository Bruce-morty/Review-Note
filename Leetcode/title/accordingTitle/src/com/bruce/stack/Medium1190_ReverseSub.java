package com.bruce.stack;

import java.util.LinkedList;

/**
 * Author: Qi Gao
 * Date:2021/5/26
 * Version:1.0.0
 */
/*
给出一个字符串 s（仅含有小写英文字母和括号）请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果
注意，您的结果中 不应 包含任何括号
示例 1：
输入：s = "(abcd)"
输出："dcba"
示例 2：
输入：s = "(u(love)i)"
输出："iloveu"
示例 3：
输入：s = "(ed(et(oc))el)"
输出："leetcode"
示例 4：
输入：s = "a(bcdefghijkl(mno)p)q"
输出："apmnolkjihgfedcbq"
 */
public class Medium1190_ReverseSub {
    /*
    碰到括号问题想到用栈去解决
    思路：要把string push到栈里面而不是字符0. 构建一个string 需要重复利用 new sb
         1. 遍历string 遇到左括号 说明有新的内容出现 把原sb入栈 将sb重新置为空
         2. 遇到右括号说明当前内容遍历完 需要反转然后返回给上一层进行拼接 拼接操作需要出栈前一个sb 拼到index=0位置
         3. 遇到字符 sb.append(字符)
     */
    public static String reverseParentheses(String s) {
        // 构建栈
        LinkedList<String> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        // 遍历s
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 先判断左括号
            if (c == '(') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if (c == ')') {
                // 若是右括号 则反战当前的string
                sb.reverse();
<<<<<<< HEAD
                //
=======
>>>>>>> 4d072317d15f53dfa5e27d0be179f26f6d491072
                sb.insert(0, stack.pop());
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
