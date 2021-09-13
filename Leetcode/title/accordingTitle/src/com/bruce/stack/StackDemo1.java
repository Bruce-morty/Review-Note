package com.bruce.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Author: Qi Gao
 * Date:2021/6/29
 * Version:1.0.0
 */
/*
计算中缀表达式
输入：tokens = ["2","+","1","*","3"]
输出：9
 */
/*
遇到的bug：0.判断栈b是否为空时，当栈不为空，我们要用while循环判断，可能栈顶元素优先级一直小于当前。需要while
          1.同时由于while的判断条件有栈b元素并且里面的操作会把元素出栈，要判断栈b是否为空，否则可能会nullPointer
          2. 把计算的code抽象成一个方法
 */
public class StackDemo1 {
    // 不判断括号情况，首先遍历数组，构建两个个栈，一个栈存操作数一个存操作符
    // 0.碰到操作数 入栈a 1.碰到操作符 判断是否为空，空入栈。不为空，判断栈顶元素和当前元素优先级，知道把操作符入栈
    // 遍历完后，从栈a中连续出栈两个元素，从栈b中出栈一个，计算值，入栈A。若式子合法，栈b为空，栈A只有一个元素在栈顶，result
    public static int countStack(String[] str) {
        int len = str.length;
        Deque<Integer> countA = new LinkedList<Integer>();
        Deque<String> countB = new LinkedList<String>();
        // 遍历数组
        for (int i = 0; i < len; i++) {
            if (isNumber(str[i])) {
                countA.push(Integer.valueOf(str[i]));
            } else {
                // 判断栈b
                if (countB.isEmpty()) {
                    countB.push(str[i]);
                } else {
                    // 判断优先级
                    // String valu =  countB.peek();
                    // int stackValue = checkValue(valu);
                    int curr = checkValue(str[i]);
                    // 要一直判断这个符号的优先级和栈顶的优先级 若不判断栈是否为空会报NullPointerException
                    while (!countB.isEmpty() && checkValue(countB.peek()) - curr >= 0) {
                        int num2 =  countA.pop();
                        int num1 =  countA.pop();
                        // 计算两个值
                        String pop = countB.pop();
                        // 出栈b中的符号，并且把计算到的值重新放入栈a
                        pushStack(countA, countB, pop, num1,num2);
                    }
                        // 栈顶优先级比当前元素小 直接入栈
                        countB.push(str[i]);
                }
            }
        }
        // 遍历完数组，计算值
        while (!countB.isEmpty()) {
            String letter =  countB.pop();
            // caution num 出栈顺序不能错
            Integer num2 =  countA.pop();
            Integer num1 =  countA.pop();
            pushStack(countA, countB, letter, num1,num2);

        }
        // 栈b已经全部出栈了, 直接返回结果
        Integer res = (Integer) countA.pop();
        return res;
    }

    public static void pushStack(Deque countA, Deque countB, String s, int num1, int num2) {
        switch (s) {
            case "+":
                countA.push(num1 + num2);
                break;
            case "-":
                countA.push(num1 - num2);
                break;
            case "*":
                countA.push(num1 * num2);
                break;
            case "/":
                countA.push(num1 / num2);
                break;
            default:
        }
    }


    private static int checkValue(String valu) {
        // 计算 + - * / 优先级
        if (valu.equals("+") || valu.equals("-")) return 1;
        return 2;
    }

    private static boolean isNumber(String s) {
        return !(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"));
    }

    public static void main(String[] args) {
        // 1 + 2 - 6 * 7 + 9 * 10 = 51
        String[] str = {"2","+","1","-","6", "*", "7", "+", "9", "*", "10"};
        int res = countStack(str);
        System.out.println(res);
    }
}
