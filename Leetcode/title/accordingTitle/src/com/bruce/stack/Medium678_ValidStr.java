package com.bruce.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: Qi Gao
 * Date:2021/9/12
 * Version:1.0.0
 */
public class Medium678_ValidStr {
    /*
  用两个栈。一个用来存左括号下标 一个存星号的下标.
  算法思路：遍历字符串, 遇到左括号push(index), 遇到* push(index).
  遇到右括号 : 0. 判断 ( stack是否不为空 不为空就出栈. 1. 若为空 出栈 * stack. 2. 都为空return false 不合法
  遍历完以后，还有判断两个栈中的element, 因为 * 可以当右括号, 判断两个栈都不为空的时候进入循环
  0. 都出栈 left stack 的index 要小于 *  * 才能当右括号 才合法. 否则return false
  1. 直到有一个栈为空,
      1.1 if left stack 还有元素 则return false
      1.2 return true if * stack still have element but left stack is empty
  合并成 一个 return leftStack.isEmpty();
   */
    public boolean checkValidString2(String s) {
        Deque<Integer> leftStack = new LinkedList<>();
        Deque<Integer> starStack = new LinkedList<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                leftStack.push(i);
            }
            if (s.charAt(i) == '*') {
                starStack.push(i);
            }
            if (s.charAt(i) == ')') {
                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                }else {
                    if (!starStack.isEmpty()) {
                        starStack.pop();
                    }else {
                        // 若两个栈都为空 没有和右括号匹配的了
                        return false;
                    }
                }
            }
        }
        // check 两个栈
        while (!leftStack.isEmpty() && !starStack.isEmpty()) {
            int leftIndex = leftStack.pop();
            int startIndex = starStack.pop();
            if (leftIndex > startIndex) return false;
        }
        return leftStack.isEmpty();
    }
}
