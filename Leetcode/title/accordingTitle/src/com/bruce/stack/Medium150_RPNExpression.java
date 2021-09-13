package com.bruce.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Author: Neo
 * Date:2021/3/20
 * Version:1.0.0
 */
/*
根据 逆波兰表示法，求表达式的值。
有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
说明：
整数除法只保留整数部分。
给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
示例 1：
输入：tokens = ["2","1","+","3","*"]
输出：9
解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 */
public class Medium150_RPNExpression {
    public int evalRPN(String[] tokens) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (String token : tokens) {
            if (isNumber(token)) {
                deque.addLast(Integer.valueOf(token));
            } else {
                int b = deque.pollLast();
                int a = deque.pollLast();
                deque.addLast(cal(a, b, token));
            }
        }
        return deque.pollLast();
    }

    private Integer cal(int a, int b, String token) {
        if (token.equals("+")) return a + b;
        if (token.equals("-")) return a - b;
        if (token.equals("*")) return a * b;
        return a / b;
    }

    private boolean isNumber(String token) {
        return !(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/"));
    }
    // 方法2，用栈存储字符串中的后缀表达式
    // 算法：1.遇到操作数入栈 2. 遇到操作符 连续出栈两个元素 计算结果 入栈 3. 遍历结束 栈中保存最后计算结果 弹出结果
    /*
        public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = tokens.length;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return stack.pop();
    }
    */
}
