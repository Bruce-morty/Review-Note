package com.bruce.nodeExercise;

import com.bruce.node.Node;

import java.util.HashMap;

/**
 * Author: bruce
 * Date:2020/11/13
 * Version:1.0.0
 */
/*
给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
删除完毕后，请你返回最终结果链表的头节点。
你可以返回任何满足题目要求的答案。
（注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。）
示例 1：

输入：head = [1,2,-3,3,1]
输出：[3,1]
提示：答案 [1,2,1] 也是正确的。
示例 2：

输入：head = [1,2,3,-3,4]
输出：[1,2,4]
 */
public class Leetcode1171 {
    public static Node removeZero(Node head) {
        // 用hashMap 把所有节点存入
        Node dummy = new Node(0);
        dummy.next = head;

        HashMap<Integer, Node> map = new HashMap<>();
        // 若同一个和出现多次会覆盖value，记录sum出现的最后节点位置
        int sum = 0;
        for(Node node = dummy; node != null; node = node.next) {
            sum += node.value;
            map.put(sum, node);
        }

        // 第二次遍历，删除sum相等的节点
        sum = 0;
        for(Node node = dummy; node != null; node = node.next) {
            sum += node.value;
            // 若不等，则node == map.get(sum) 将node.next连上自己的下一个节点。
            // e.g 1 -> 2 -> 3 -> -3 -> 3  --------1 -> 2 -> 3
            // map={1: 1, 3: 2, 6: 3, 3: -3, 6: 3} ---map = sum : node
            //所以第二次找到sum = 3时， 3会更新3的位置，node会等于 -3 位置，连上-3下一个节点，删除中间元素
            node.next = map.get(sum).next;
        }
        return dummy.next;
    }
}
