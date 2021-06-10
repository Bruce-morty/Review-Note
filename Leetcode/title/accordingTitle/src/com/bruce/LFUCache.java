package com.bruce;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Author: bruce
 * Date:2020/11/25
 * Version:1.0.0
 */
/*
hard.
Input
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

Explanation
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);
lfu.put(2, 2);
lfu.get(1);      // return 1
lfu.put(3, 3);   // evicts key 2
lfu.get(2);      // return -1 (not found)
lfu.get(3);      // return 3
lfu.put(4, 4);   // evicts key 1.
lfu.get(1);      // return -1 (not found)
lfu.get(3);      // return 3
lfu.get(4);      // return 4

分析：
    用两个哈希表，一个存储fre 的节点，存一个双向链表
    另一个存对应缓存freq在另一个map中的内存地址。
 */
public class LFUCache {
    int minfreq;
    int capacity;
    Map<Integer, Node> key_table;
    Map<Integer, LinkedList<Node>> freq_table;

    class Node{
        int freq;
        int key;
        int value;

        public Node(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }

    public LFUCache(int capacity) {
        this.minfreq = 0;
        this.capacity = capacity;
        key_table = new HashMap<Integer, Node>();
        freq_table = new HashMap<Integer, LinkedList<Node>>();
    }

    /**
     * 返回key所对应的节点的value，同时将使用频率加1
     * @param key
     * @return 存在返回value，否则返回-1
     */
    public int get(int key) {
        if (capacity == 0) return -1;
        if (!key_table.containsKey(key)) return -1;
        // 存在
        Node node = key_table.get(key);
        int val = node.value;
        int freq = node.freq;
        //删除当前频率的node freq_table.get(freq) 得到一个linkedList, delete current node
        freq_table.get(freq).remove(node);
        // 如果当前频率的链表为空，需要在哈希表中删除，删除整个链表。更新minfreq
        if (freq_table.get(freq).size() == 0) {
            freq_table.remove(freq);
            if (minfreq == freq){
                minfreq += 1;
            }
        }
        // 插入到新的频率的链表中
        LinkedList<Node> list = freq_table.getOrDefault(freq + 1, new LinkedList<>());
        list.offerFirst(new Node(key, val, freq + 1));
        freq_table.put(freq + 1, list);
        // 更新key_table的节点，因为频率变化了，由于新加进去，可以确定是第一个元素
        key_table.put(key, freq_table.get(freq + 1).peekFirst());
        return val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        if (!key_table.containsKey(key)) {
            // 缓存已经满了
            if (capacity == key_table.size()) {
                // 删除最小频率的尾节点
                Node node = freq_table.get(minfreq).peekLast();
                // 同时删除freq_table 中的这个节点
                key_table.remove(node.key);
                freq_table.get(minfreq).pollLast();
                if (freq_table.get(minfreq).size() == 0) {
                    // 每次都要判断删除完之后freqtable是否为空
                    freq_table.remove(minfreq);
                }
            }
            LinkedList<Node> list = freq_table.getOrDefault(1, new LinkedList<>());
            list.offerFirst(new Node(key, value, 1));
            freq_table.put(1, list);
            // 同时更新这个key对应的freq中的node值
            key_table.put(key, freq_table.get(1).peekFirst());
            minfreq = 1;
        } else {
            // 和get操作一样 包含这个元素，更新freq 和 freq_table， key_table
            Node node = key_table.get(key);
            int freq = node.freq;
            freq_table.get(freq).remove(node);
            if (freq_table.get(freq).size() == 0) {
                freq_table.remove(freq);
                if (minfreq == freq) {
                    minfreq += 1;
                }
            }
            LinkedList<Node> list = freq_table.getOrDefault(freq + 1, new LinkedList<>());
            list.offerFirst(new Node(key, value, freq + 1));
            freq_table.put(freq + 1, list);
            key_table.put(key, freq_table.get(freq + 1).peekFirst());
        }
    }
}
