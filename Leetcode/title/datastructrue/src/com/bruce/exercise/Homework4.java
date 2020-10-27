package com.bruce.exercise;

import com.bruce.node.Node;

/**
 * Author: bruce
 * Date:2020/10/20
 * Version:1.0.0
 */
/*
合并两个有序的链表, 合并后的链表也是有序的。
举例：
      输入：1 --> 2 --> 5 --> null
	       2 --> 4 --> 7 --> null
      输出：1 --> 2 --> 2 --> 4 --> 5 --> 7 --> null

 */
public class Homework4 {

    public static Node merge(Node l1, Node l2) {
        /*
        1.需要创建一个新的节点，来保存比较之后的节点,如果直接覆盖当前节点会很麻烦
        2. 当前第一个节点应该是什么，就用l1和l2比较大小
        3. 同时需要定义一个节点保存当前头节点，要不然节点一直移动地添加，最后节点不是头节点，而是后面的元素
         */
        // 用哨兵的做法, 定义一个没有意义的
        Node prev = new Node(0, null);
        Node curr = prev;
        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                prev.next = l1;
                // 移动当前节点，为下一次连接节点做准备
                prev = prev.next;
                l1 = l1.next;
            } else {
                prev.next = l2;
                prev = prev.next;
                l2 = l2.next;
            }
        }
        // 如果一个链表遍历完了，后面就接上没有遍历完的，后面的节点都是大的
        prev.next = l1 != null ? l1 : l2;
        return curr.next;
        //  由于没有哨兵，只能这么做, 要不然用node保存节点时，会报空指针，因为node没有赋值
        /*Node node;
        if (l1.value <= l2.value) {
            node = l1;
            l1 = l1.next;
        } else {
            node = l2;
            l2 = l2.next;
        }
        Node node1 = node;
        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                node.next = l1;
                // 移动当前节点，为下一次连接节点做准备
                node = node.next;
                l1 = l1.next;
            } else {
                node.next = l2;
                node = node.next;
                l2 = l2.next;
            }
        }
        // 用三目运算符
        if (l1 != null) {
            node.next = l1;
        }
        if (l2 != null) {
            node.next = l2;
        }
        return node1;*/
    }

    public static void main(String[] args) {
        Node l1 = new Node(5, null);
        l1 = new Node(2, l1);
        l1 = new Node(1, l1);

        Node l2 = new Node(7, null);
        l2 = new Node(4, l2);
        l2 = new Node(2, l2);

        Node head = merge(l1, l2);
        Node node = head;
        while (node != null) {
            System.out.print(node.value + " --> ");
            node = node.next;
        }
        System.out.println(node);

    }
}
