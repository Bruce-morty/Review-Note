package com.bruce.nodeExercise;

import com.bruce.node.Node;

/**
 * Author: bruce
 * Date:2020/11/5
 * Version:1.0.0
 */
/*
Leetcode 61
给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

示例 1:

输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL
解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL
 */
public class RotateRight {

    public Node rotateRight(Node head, int k) {
        if (head == null || head.next == null) return head;
        Node curr = head;
        // 先找出链表的长度，然后判断该旋转几次，若k大于len很多，取余找出真正旋转几次
        int len = 1;
        while(curr.next != null) {
            len++;
            curr = curr.next;
        }
        // 找到应该旋转的位置, 有两种情况，若index == 0没有反转
        int index = k % len;
        if (index == 0) return head;
        // 当前节点变成头节点
        Node node = head;
        Node prev = null;
        // 写for loop 在里面进行旋转
        // 不用全部都反转，只需要找到要反转的那个节点，尾节点连上头节点，将头节点变成index位置
        int newHead = len - index;
        for (int i = 0; i < newHead; i++) {
            prev = node;
            node = node.next;
        }
        // prev 变成尾节点
        prev.next = null;
        // curr连上原本的头节点,curr在尾节点位置
        curr.next = head;
        // node变成头节点, 不用连了，prev已经断开了，然后又变成了尾，这边的头自然成为了node
        // head = node;
        return node;
    }

    // 另一种解法，把链表变成环，然后计算出新的头节点位置，将新头节点的前一个节点指向null，从新变成无环链表
    public static Node rotate(Node head, int k) {
        if (head == null || head.next == null) return head;
        // 定义长度，找出索引
        int len = 1;
        Node node = head;
        while (node.next != null) {
            len++;
            node = node.next;
        }
        // 变成有环链表
        node.next = head;
        // 找到头节点的前驱节点,和当前节点
        Node newTail = head;
        for (int i = 0; i < len - k % len - 1; i++) {
            newTail = newTail.next;
        }
        // 将newTail变成尾，newHead变成头
        Node newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        Node head = new Node(5, null);
        head = new Node(4, head);
        head = new Node(3, head);
        head = new Node(2, head);
        head = new Node(1, head);

        Node rotate = rotate(head, 3);
        System.out.println(rotate);
    }
}
