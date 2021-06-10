package com.bruce.nodeExercise;

import com.bruce.node.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Author: bruce
 * Date:2020/11/6
 * Version:1.0.0
 */
/*
Leetcode1019
给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。
每个节点都可能有下一个更大值（next larger value）：对于 node_i，如果其 next_larger(node_i) 是 node_j.val，
那么就有 j > i 且  node_j.val > node_i.val，而 j 是可能的选项中最小的那个。如果不存在这样的 j，那么下一个更大值为 0 。
返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。
注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示，其头节点的值为 2，第二个节点值为 1，第三个节点值为 5 。

示例 1：

输入：[2,1,5]
输出：[5,5,0]

示例 2：

输入：[2,7,4,3,5]
输出：[7,0,5,5,0]
 */
public class NextLargeNodes {

    public static int[] nextLargerNodes(Node head) {
        // 将节点的值存入数组
        List<Integer> list = new ArrayList<>();
        while(head != null) {
            list.add(head.value);
            head = head.next;
        }
        int[] res = new int[list.size()];
        Stack<Integer> stack = new Stack<>();
        // 用栈判断，若找到第一个最小的元素则把它替换当前元素
        for(int i = 0; i < list.size(); i++) {
            while(!stack.empty() && (list.get(stack.peek()) < list.get(i))) {
                // 将栈中存的索引取出，然后替换list中的位置
                int index = stack.pop();
                res[index] = list.get(i);
            }
            // 若为空或者不小于当前元素，添加到stack中
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        Node head = new Node(5, null);
        head = new Node(2, head);
        head = new Node(1, head);
//        head = new Node(8, head);
        int[] ints = nextLargerNodes(head);
        System.out.println(Arrays.toString(ints));
    }
}
