package com.bruce.nodeExercise;

import com.bruce.node.Node;

import java.util.HashMap;

/**
 * Author: bruce
 * Date:2020/11/14
 * Version:1.0.0
 */
/*
A linked list is given such that each node contains an additional random pointer
which could point to any node in the list or null.Return a deep copy of the list.
The Linked List is represented in the input/output as a list of n nodes.
Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.
 */
public class Leetcode138 {
    public Node copyRandomList(Node head) {
        // 将节点复制一份在自己的旁边，然后遍历连接
        if (head == null) return null;
        Node node = head;
        while(node != null) {
            // copy 了一份, 首先将复制的节点连上原先后面的节点
            Node temp = new Node(node.value);
            temp.next = node.next;
            // 然后将原来的节点连上复制的节点，移动节点到复制之后的节点
            node.next = temp;
            node = temp.next;
        }

        // 将random也复制一份
        Node rand = head;
        while(rand != null) {
            // 这儿random要指向复制好的值，连上它复制好的下一个节点
            rand.next.random = (rand.random != null) ? rand.random.next : null;
            // 前面复制了一份，不怕.next报空指针
            rand = rand.next.next;
        }
        // 断开链表
        /*
         Node dummy = new Node(-1);
        p = head;
        // 有哨兵就不用判断，newNodeList.next 是不是为null了
        Node cur = dummy;
        while(p!=null) {
            cur.next = p.next;
            cur = cur.next;
            p.next = cur.next;
            p = p.next;
        }
         */
        Node newNode = head.next;
        Node oldNode = head;
        Node newNodeList = head.next;
        while (oldNode != null) {
            oldNode.next = oldNode.next.next;
            newNodeList.next = (newNodeList.next != null) ? newNodeList.next.next : null;
            oldNode = oldNode.next;
            newNodeList = newNodeList.next;
        }
        return newNode;
    }

    /*
    另一种方法，用哈希表来保存新复制好的节点，再通过hash表拿到新节点对next和random进行连接
     */
    public static Node copyRandom2(Node head) {
        if (head == null) return null;
        HashMap<Node, Node> map = new HashMap<>();
        Node node = head;
        while(node != null) {
            // 复制当前节点，放入map中
            Node newNode = new Node(node.value);
            map.put(node, newNode);
            node = node.next;
        }
        // 把复制好的节点中的链重新连上 next 和 random
        node = head;
        while(node != null) {
            // 判断原节点的next域，取出value
            if(node.next != null) {
                // 新复制的节点连上后面复制的节点的值
                map.get(node).next = map.get(node.next);
            }
            if(node.random != null) {
                map.get(node).random = map.get(node.random);
            }
            node = node.next;
        }
<<<<<<< HEAD
        // 直接返回頭節點
=======
        // 断开链表
>>>>>>> 4d072317d15f53dfa5e27d0be179f26f6d491072
        return map.get(head);
    }
}
