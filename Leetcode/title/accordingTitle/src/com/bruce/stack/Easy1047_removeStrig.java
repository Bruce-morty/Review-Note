package com.bruce.stack;

import java.util.Stack;

/**
 * Author: Qi Gao
 * Date:2021/3/23
 * Version:1.0.0
 */
/*

给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
在 S 上反复执行重复项删除操作，直到无法继续删除。在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
示例：
输入："abbaca"
输出："ca"
解释：
例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。
之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"
 */
public class Easy1047_removeStrig {
    public String removeDuplicates(String S) {
        /* 只需删除重复项即可，因此可以使用栈实现
         * 每次添加时比较是否与栈顶元素相同
         *   - 若相同则删除栈顶元素且不插入
         *   - 若不相同则插入新元素
         * 时间复杂度：O(N)
         * 空间复杂度：O(N)
         */
        char[] s = S.toCharArray();
        int len = s.length;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            if (stack.isEmpty() || s[i] != stack.peek()) {
                stack.push(s[i]);
            } else {
                stack.pop();
            }
        }
        /* 数据的展示可以继续优化 */
        StringBuilder str = new StringBuilder();
        for (Character c : stack) {
            str.append(c);
        }
        return str.toString();
    }
}
