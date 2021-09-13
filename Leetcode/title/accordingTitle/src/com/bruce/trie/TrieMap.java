package com.bruce.trie;

/**
 * Author: bruce
 * Date:2021/2/17
 * Version:1.0.0
 */

import java.util.HashMap;

/**
 * 用map实现trie
 */
public class TrieMap {
    class TrieNode {
        // 定义两个变量 一个map 一个flag map的value存的是下一个节点位置 相当于拉链
        private HashMap<Character, TrieNode> map = new HashMap<>();
        private boolean flag;
    }

    private TrieNode root;

    public TrieMap() {
        root = new TrieNode();
    }

    /*
    插入方法：当插入一个单词要将此单词的结尾打上标记 确认这里是一个单词的结尾
     */
    public void insert(String word) {
        TrieNode node = root;
        char[] ch = word.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            // 判断word是否在trie中
            if (node.map.get(ch[i]) == null) {
                node.map.put(ch[i], new TrieNode());
            }
            node = node.map.get(ch[i]);
        }
        // 添加完后改flag
        node.flag = true;
    }

    /*
    查找是否存在这个单词
     */
    public boolean search(String word) {
        TrieNode node = root;
        char[] ch = word.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (!node.map.containsKey(ch[i])) {
                return false;
            }
            node = node.map.get(ch[i]);
        }
        // 找到了判断当前node的flag
        return node.flag;
    }

    /*
    查找是否以这个词缀开头的元素
     */
    public boolean startWith(String word) {
        TrieNode node = root;
        char[] ch = word.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (node.map.get(ch[i]) == null) {
                return false;
            }
            // 向后遍历node
            node = node.map.get(ch[i]);
        }
        return true;
    }
}
