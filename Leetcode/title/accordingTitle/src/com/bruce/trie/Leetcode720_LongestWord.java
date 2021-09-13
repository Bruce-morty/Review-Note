package com.bruce.trie;

import java.util.HashMap;
import java.util.Stack;

/**
 * Author: bruce
 * Date:2021/2/17
 * Version:1.0.0
 */
/*
给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。
若其中有多个可行的答案，则返回答案中字典序最小的单词。
若无答案，则返回空字符串。
示例 1：
输入：
words = ["w","wo","wor","worl", "world"]
输出："world"
解释：
单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
示例 2：
输入：
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
输出："apple"
解释：
"apply"和"apple"都能由词典中的单词组成。但是"apple"的字典序小于"apply"
 */
public class Leetcode720_LongestWord {
    /*
    用dfs 去找。要注意这个是怎么实现的 注意index
    将所有单词插入 trie，然后从 trie 进行深度优先搜索，每找到一个单词表示该单词的全部前缀均存在，我们选取长度最长的单词。
     */
    public String longestWord(String[] words) {
        Trie trie = new Trie();
        int index = 0;
        for (String s : words) {
            // index start by 1
            trie.insert(s, ++index);
        }
        trie.words = words;
        return trie.dfs();
    }

    class Node {
        private HashMap<Character, Node> child = new HashMap<>();
        // 再定义一个end 通过end确认当前字符的长度
        private int end;
        char c;
        public Node(char c) {
            this.c = c;
        }
    }
    class Trie{
        // 定义字典树
        Node root;
        String[] words;
        public Trie() {
            root = new Node('0');
        }

        public void insert(String word, int index) {
            Node curr = root;
            for (char c : word.toCharArray()) {
                curr.child.putIfAbsent(c, new Node(c));
                curr = curr.child.get(c);
            }
            curr.end = index;
        }

        /**
         dfs 查找最长的符合要求的字符串
         */
        public String dfs() {
            String res = "";
            Stack<Node> stack = new Stack();
            stack.push(root);
            while (!stack.empty()) {
                Node node = stack.pop();
                if (node.end > 0 || node == root) {
                    if (node != root) {
                        // 这一步找到第一个字符
                        String w = words[node.end - 1];
                        if (w.length() > res.length() || w.length() == res.length() && w.compareTo(res) < 0) {
                            res = w;
                        }
                    }
                    for (Node n : node.child.values()) {
                        stack.push(n);
                    }
                }
            }
            return res;
        }
    }
}
