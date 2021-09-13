package com.bruce.binarySearchTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: bruce
 * Date:2021/3/12
 * Version:1.0.0
 */
/*
给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。

每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。

你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。

示例 1:

输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
输出: true
示例 2:

输入: "1,#"
输出: false
示例 3:

输入: "9,#,#,1"
输出: false
 */
public class Medium331_Tree {
    /*
    我们可以定义一个概念，叫做槽位。一个槽位可以被看作「当前二叉树中正在等待被节点填充」的那些位置。
    二叉树的建立也伴随着槽位数量的变化。每当遇到一个节点时：
    如果遇到了空节点，则要消耗一个槽位；
    如果遇到了非空节点，则除了消耗一个槽位外，还要再补充两个槽位。此外，还需要将根节点作为特殊情况处理。
    我们使用栈来维护槽位的变化。栈中的每个元素，代表了对应节点处剩余槽位的数量，而栈顶元素就对应着下一步可用的槽位数量。
    当遇到空节点时，仅将栈顶元素减 1；当遇到非空节点时，将栈顶元素减 1 后，再向栈中压入一个 2。无论何时，如
    果栈顶元素变为 0，就立刻将栈顶弹出。
    遍历结束后，若栈为空，说明没有待填充的槽位，因此是一个合法序列; 否则若栈不为空，则序列不合法。
    此外，在遍历的过程中，若槽位数量不足，则序列不合法。
     */
    public static boolean isValidSerialization(String preorder) {
        // 判断是不是前序遍历的序列化
        int n = preorder.length();
        int i = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        // 初始栈中有一个元素 1, 代表pre的root节点
        stack.push(1);
        while (i < n) {
            if (stack.isEmpty()) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#'){
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                i++;
            } else {
                // 读一个数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                stack.push(2);
            }
        }
        return stack.isEmpty();
    }

    /*
    我们可以用int表示栈中元素的变化
     */
    public static boolean isValidSerialization2(String preorder) {
        int n = preorder.length();
        int i = 0;
        int slots = 1;
        while (i < n) {
            if (slots == 0) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#'){
                slots--;
                i++;
            } else {
                // 读一个数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                slots++; // slots = slots - 1 + 2
            }
        }
        return slots == 0;
    }
}
