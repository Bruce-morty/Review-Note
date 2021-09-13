package com.bruce.nodeExercise;

import com.bruce.node.Node;

/**
 * Author: bruce
 * Date:2021/2/3
 * Version:1.0.0
 */
/*
给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
输入：head = [1,2,3,4]
输出：[2,1,4,3]
 */
public class Leetcode24 {

    /*
    交换两个节点 需要一个哨兵去保证头节点可以交换 同时遍历整个链表
     */
    public static Node swapParis(Node head) {
        if (head == null) return head;
        Node dumb = new Node(0);
        dumb.next = head;
        Node temp = dumb;
        // 遍历链表 要取到第二个节点 所以判断要判断.next.next
        while (temp.next != null && temp.next.next != null) {
            Node node1 = temp.next;
            Node node2 = temp.next.next;
            // swap 连上后一个节点的节点
            node1.next = node2.next;
            temp.next = node2;
            node2.next = node1;
            // 移动节点
            temp = node1;
        }
        return dumb.next;
    }
}
