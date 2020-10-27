package com.bruce.exercise;

import com.bruce.node.Node;

/**
 * Author: bruce
 * Date:2020/10/19
 * Version:1.0.0
 */
/*
反转单链表
举例：
    输入：1 --> 2 --> 3 --> null
    输出：3 --> 2 --> 1 --> null
 */
public class NodeTest3 {

    public static Node reverse(Node head) {
        Node curr = head;
        Node prev = null;
        // 循环去反转
        while (curr != null) {
            Node temp = curr.next;
            // 反转
            curr.next = prev;
            // 移动节点
            prev = curr;
            curr = temp;
        }
        // curr == null 现在头节点是prev
        return prev;
    }

    public static Node recursion(Node head) {
        if (head == null || head.next == null) return head;
        // 递归将大问题分解成小问题，将最后一个节点反转，再反转倒数第二个节点....
        Node node = recursion(head.next);
        // 这个放在前面，首先将后一个节点指向前一个节点，再将前一个节点指向null。
        // 这个语句放在 head.next = null 之后会报NullPointerException
        head.next.next = head;
        head.next = null;

        return node;
    }

    public static void main(String[] args) {
        Node head = new Node(4, null);
        head = new Node(3, head);
        head = new Node(2, head);
        head = new Node(1, head);
        Node node1 = head;
        while (node1 != null) {
            System.out.print(node1 + "-->");
            node1 = node1.next;
        }
        System.out.println();
//        Node node2 = reverse(head);
        Node node2 = recursion(head);
        while (node2 != null) {
            System.out.print(node2 + "-->");
            node2 = node2.next;
        }
        System.out.println(node2);
    }
}
