package com.bruce.nodeExercise;

import com.bruce.node.Node;

/**
 * Author: bruce
 * Date:2020/11/7
 * Version:1.0.0
 */
/*
给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

示例 1:

输入: 1->1->2
输出: 1->2
示例 2:

输入: 1->1->2->3->3
输出: 1->2->3
 */
public class DeleteDuplicate1 {

    public static Node deleteDuplicated(Node head) {
        // 只需要定义一个指针，找到不同的元素就往后移动，相同就指向下一个节点。不用定义两个
        Node node = head;
        while (node != null && node.next != null) {
            if (node.value != node.next.value) {
                // 不相等 往后移动
                node = node.next;
            } else {
                // 删除重复节点，所以while的判断条件要是and
                node.next = node.next.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(4, null);
        head = new Node(4, head);
        head = new Node(3, head);
        head = new Node(2, head);
        head = new Node(2, head);
        head = new Node(1, head);
        Node node = deleteDuplicated(head);
        System.out.println(node);
    }
}
