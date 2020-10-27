package com.bruce.exercise;


/**
 * Author: bruce
 * Date:2020/10/20
 * Version:1.0.0
 */
public class LRU {
    private int capacity;
    private int size;
    private Node head;

    public LRU() {
    }

    public LRU(int capacity) {
        this.capacity = capacity;
    }

    private class Node{
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public void add(int data) {
        // LRU 算法：1. 判断是否已存在该元素 2. 判断size 满没有
        boolean flag = false;
        Node curr = head;
        Node prev = null;
        // 遍历链表 判断是否存在data
        while (curr != null) {
            if (curr.value == data) {
                flag = true;
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        // 如果存在，直接更新放到最前面。同时判断是否是第一个元素
        if (flag) {
            // 删掉该节点，在头节点添加
            // prev == null 可知这个元素就在头节点,不用操作
            if (prev != null) {
                // 删掉curr,而prev.next 正是curr，可以避免空指针
                prev.next = prev.next.next;
                // 添加头节点
                //curr已经被删掉了，现在这个curr.next还是指向后面，应改为指向原本的head，这样它才能变成head
                curr.next = head;
                //现在的curr也就是相同元素的节点添加到头节点
                head = curr;
            }
        } else {
            // 判断size
            if (size < capacity) {
                // 在头节点添加
                // 新建node，后面指向原本的head
                   /* Node node = new Node(data, head);
                    // 把node变成head
                    head = node;*/
                // 变成一步
                head = new Node(data, head);
                size++;
            } else {
                //这个缓存满了
                //需要重新遍历，prev在最后一个节点的位置，可以用双向链表
                Node node = head;
                while (node.next.next != null) {
                    node = node.next;
                }
               /*curr = head;
               while (curr.next != null) {
                   prev = curr;
                   curr = curr.next;
               }
               prev.next = null;*/
                //删除尾节点
                node.next = null;
                head = new Node(data, head);
            }
        }
    }
    public static void main(String[] args) {
        LRU cache = new LRU(5);
        cache.add(5);
        cache.add(4);
        cache.add(3);
        cache.add(2);
        cache.add(1);

        Node node = cache.head;
        while (node != null) {
            System.out.print(node.value + " --> ");
            node = node.next;
        }
        System.out.println(node);

        cache.add(4);

        node = cache.head;
        while (node != null) {
            System.out.print(node.value + " --> ");
            node = node.next;
        }
        System.out.println(node);
    }
}
