package com.bruce.exercise;

import com.bruce.node.Node;

/**
 * Author: bruce
 * Date:2020/10/20
 * Version:1.0.0
 */
/*
给定一个链表，删除倒数第 n 个节点。给定的 n 总是有效的。
举例：
         0     1     2      3
    输入：1 --> 2 --> 3 --> 4 --> null,   2
    输出：1 --> 2 --> 4 --> null
 */
public class Homework3 {

    public static Node delete(Node head, int i) {

        /* 首先遍历链表，得到长度
         删除倒数第一个节点, len - 1， 第二个 len - 2
         若删除节点，需要有上一个节点，定义一个哨兵会有帮助*/
        int len = 0;
        Node curr = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        int index = len - i;
        // 删除
        // caution! 要注意删除头节点得情况
        if (index == 0) {
            head = head.next;
            return head;
        }
        curr = head;
        // 需要找到这个索引的前一个元素才能把它删掉
        int j = 1;
        while (j < index) {
            curr = curr.next;
            j++;
        }
        curr.next = curr.next.next;
        return curr;
    }

    public static void main(String[] args) {
        Node head = new Node(4, null);
        head = new Node(3, head);
        head = new Node(2, head);
        head = new Node(1, head);
        Node node = head;
        while (node != null) {
            System.out.print(node + "-->");
            node = node.next;
        }
        System.out.println(node);
        Node node1 = delete(head, 4);
        while (node1 != null) {
            System.out.print(node1 + "-->");
            node1 = node1.next;
        }
        System.out.println(node1);
    }
}
