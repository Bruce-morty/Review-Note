package com.bruce;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: bruce
 * Date:2020/11/25
 * Version:1.0.0
 */
/*
输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4
 */
public class LRUCache {
    // 设计一个LRU 机制
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // 用map来保存节点，因为map的get方法快 不需要遍历整个链表
    private Map<Integer, Node> cache = new HashMap<>();
    // LRU size
    private int size;
    private int capacity;
    // 定义头尾节点
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    /*
    返回map中key对应的value 不存在则返回-1
     */
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        // 不为null，则移动到头节点, 因为这是最新用过的元素
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        // 判断是否在map中已经有这个节点
        if (node != null) {
            // 修改value值，移动到头节点
            node.value = value;
            moveToHead(node);
        } else {
            // 创建新节点,先添加完再判断size和capacity
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            // 放到头节点位置
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 1.删除尾节点 2.在缓存中删除这个节点位置
                Node removeTail = removeTail();
                cache.remove(removeTail.key);
                --size;
            }
        }
    }

    // 移动到头节点
    public void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /*
    添加到头节点位置
     */
    public void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        // 这是head原来的下一个节点
        head.next.prev = node;
        head.next = node;
    }

    public Node removeTail() {
        // 删除尾节点
        Node res = tail.prev;
        removeNode(res);
        return res;
    }
}
