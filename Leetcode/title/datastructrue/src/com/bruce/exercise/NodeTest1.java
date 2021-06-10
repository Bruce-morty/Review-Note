package com.bruce.exercise;

import com.bruce.node.Node;

/**
 * Author: bruce
 * Date:2020/10/19
 * Version:1.0.0
 */
public class NodeTest1 {

    /*
    求链表的中间节点
    举例：
    输入: 1 --> 2 --> 3 --> null
    输出：2
    输入: 1 --> 2 --> 3 --> 4 --> null
    输出：2

    1. 定义变量求出链表长度
    2. 求出中间节点索引index
    3， 继续遍历链表
     */
    public static Node middleElement(Node head) {
        int len = 0;
        Node node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        // 索引：长度可能是奇数或者偶数，3 / 2 = 2, 2 / 2 = 1
        int index = (len - 1) / 2;
        node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public static void main(String[] args) {
        // test
        Node head = new Node(4, null);
        head = new Node(3, head);
        head = new Node(2, head);
        head = new Node (1, head);
        Node node = middleElement(head);
        System.out.println(node);
    }
}
