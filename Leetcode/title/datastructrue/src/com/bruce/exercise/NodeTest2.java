package com.bruce.exercise;

import com.bruce.node.Node;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Author: bruce
 * Date:2020/10/19
 * Version:1.0.0
 */
/*
判断链表中是否有环？
 */
public class NodeTest2 {
    public static boolean detectCircle(Node head) {
        if (head.next == null) return false;
        // 用集合去做
      /* Collection c = new ArrayList();
       Node node = head;
       while (node != null) {
           if (c.contains(node.value)) {
               return true;
           } else {
               c.add(node.value);
           }
           node = node.next;
       }
       return false;*/

        // 用快慢指针去做
        Node slow = head;
        Node fast = head;
        // 需要先开始走一步，用do while
        do {
            // 追击问题
            // 若无环，fast会走向终点，有环就终有一天追上slow
            if (fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);
        return true;
    }

    public static Node firstCircleElement(Node head) {
        if (head.next == null) return null;
        // 用集合
        /*Collection c = new ArrayList();
        Node node = head;
        while (node != null) {
            if (c.contains(node)) return node;
            else c.add(node);
            node = node.next;
        }
        return null;*/

        // 快慢指针
        Node slow = head;
        Node fast = head;
        do {
            if (fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);

        // slow 重新回到头节点
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        Node head = new Node(4, null);
        Node node = head;
        node.next = node;
        head = new Node(3, head);
        head = new Node(2, head);
        head = new Node(1, head);
        boolean b = detectCircle(head);
        System.out.println(b);

        Node circleElement = firstCircleElement(head);
        System.out.println(circleElement);
    }
}
