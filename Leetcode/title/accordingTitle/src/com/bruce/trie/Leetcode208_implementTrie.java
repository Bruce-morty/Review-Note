package com.bruce.trie;

/**
 * Author: bruce
 * Date:2021/2/16
 * Version:1.0.0
 */
/*
字典树
 */
public class Leetcode208_implementTrie {

    // private Leetcode208_implementTrie[] next;
    private Leetcode208_implementTrie[] children;
    private boolean isEnd;

    public Leetcode208_implementTrie() {
        children = new Leetcode208_implementTrie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Leetcode208_implementTrie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Leetcode208_implementTrie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Leetcode208_implementTrie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Leetcode208_implementTrie searchPrefix(String prefix) {
        Leetcode208_implementTrie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}
