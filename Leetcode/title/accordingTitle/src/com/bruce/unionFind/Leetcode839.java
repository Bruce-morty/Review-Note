package com.bruce.unionFind;


/**
 * Author: bruce
 * Date:2021/1/31
 * Version:1.0.0
 */
/*

如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，
那它们也是相似的。
例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，
但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，
即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？
示例 1：

输入：strs = ["tars","rats","arts","star"]
输出：2
示例 2：

输入：strs = ["omv","ovm"]
输出：1
 */
public class Leetcode839 {
    // 题目问有几个相似的字符串数组。字符串等同于图中的每一个触点 相似看成边
    // 也就是说找出每个相似的字符串，他们是同一个连通分量 问总共有几个连通分量 = 有几个相似数组
    // 相似数组中的另一个不一定与另一个相似 但它们肯定相似于另一个元素 属于同一个连通分量。find() 用路径压缩法
    public int[] f;

    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        // 可以知道数组每个元素的string长度是一样的 求出字符串长度
        int m = strs[0].length();
        // 初始化数组的触点
        f = new int[n];
        int count = n;
        for (int i = 0; i < n; i++) {
            f[i] = i;
        }
        // union方法 查连通分量
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x = find(i);
                int y = find(j);
                // 若相等或属于同一连通分量 遍历下一个元素
                if (x == y) continue;
                if (check(strs[i], strs[j], m)) {
                    // 相似 union合并成同一分量
                    f[x] = y;
                    count--;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] == i) result++;
        }
//        return result;
        return count;
    }

    /*
    判断两个元素是否相似 如果交换两个相似
     */
    private boolean check(String str, String str1, int m) {
        int num = 0;
        for (int i = 0; i < m; i++) {
            if (str.charAt(i) != str1.charAt(i)) {
                num++;
            }
            if (num > 2) return false;
        }
        // 相似
        return true;
    }

    public int find(int x) {
        return x == f[x] ? x : (f[x] = find(f[x]));
    }

    public static void main(String[] args) {
        Leetcode839 test = new Leetcode839();
        String[] grid = {"tars","rats","arts","star"};
        int res = test.numSimilarGroups(grid);
        System.out.println(res);
    }
}
