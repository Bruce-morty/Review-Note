package com.bruce.greedyAlgorithm;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: Qi Gao
 * Date:2021/9/12
 * Version:1.0.0
 */
/*
678. 有效的括号字符串
给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
任何左括号 ( 必须有相应的右括号 )。
任何右括号 ) 必须有相应的左括号 ( 。
左括号 ( 必须在对应的右括号之前 )。
* 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
一个空字符串也被视为有效字符串。
示例 1:
输入: "()"
输出: True
示例 2:
输入: "(*)"
输出: True
 */
public class Medium678_ValidString {
    /*
    方法三：贪心
使用贪心的思想，可以将空间复杂度降到 O(1)。

    从左到右遍历字符串，遍历过程中，未匹配的左括号数量可能会出现如下变化：
    如果遇到左括号，则未匹配的左括号数量加 1；
    如果遇到右括号，则需要有一个左括号和右括号匹配，因此未匹配的左括号数量减 1；
    如果遇到星号，由于星号可以看成左括号、右括号或空字符串，因此未匹配的左括号数量可能加 1、减 1 或不变
    -------------------
    基于上述结论，可以在遍历过程中维护未匹配的左括号数量可能的最小值和最大值，根据遍历到的字符更新最小值和最大值：
    如果遇到左括号，则将最小值和最大值分别加 1；
    如果遇到右括号，则将最小值和最大值分别减 1；
    如果遇到星号，则将最小值减 1，将最大值加 1。
    任何情况下，未匹配的左括号数量必须非负，因此当最大值变成负数时，说明没有左括号可以和右括号匹配，返回 false。
    当最小值为 0 时，不应将最小值继续减少，以确保最小值非负。
    遍历结束时，所有的左括号都应和右括号匹配，因此只有当最小值为 0时，字符串 s 才是有效的括号字符串。
     */

    public boolean checkValidString(String s) {
        // 用stack 如果左右括号个数一样就return true 否则就需要*
        int minCount = 0, maxCount = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                minCount++;
                maxCount++;
            } else if (c == ')') {
                minCount = Math.max(minCount - 1, 0);
                maxCount--;
                if (maxCount < 0) {
                    return false;
                }
            } else {
                minCount = Math.max(minCount - 1, 0);
                maxCount++;
            }
        }
        return minCount == 0;
    }

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
