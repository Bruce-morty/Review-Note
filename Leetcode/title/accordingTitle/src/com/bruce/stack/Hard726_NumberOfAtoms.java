package com.bruce.stack;

import java.util.*;

/**
 * Author: Qi Gao
 * Date:2021/7/5
 * Version:1.0.0
 */
/*
给定一个化学式formula（作为字符串），返回每种原子的数量。
原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。
例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式
一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1）
然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推
示例 1:
输入:
formula = "H2O"
输出: "H2O"
解释:
原子的数量是 {'H': 2, 'O': 1}。
示例 2:
输入:
formula = "Mg(OH)2"
输出: "H2MgO2"
解释:
原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
 */
public class Hard726_NumberOfAtoms {
    /*
    括号问题想到用stack来解决
    从左到右遍历该化学式，并使用哈希表记录当前层遍历到的原子及其数量，因此初始时需将一个空的哈希表压入栈中。对于当前遍历的字符：
    如果是左括号，将一个空的哈希表压入栈中，进入下一层
    如果不是括号，则读取一个原子名称，若后面还有数字，则读取一个数字，否则将该原子后面的数字视作 1然后将原子及数字加入栈顶的哈希表中
    如果是右括号，则说明遍历完了当前层，若括号右侧还有数字，则读取该数字 num，否则将该数字视作 1。然后将栈顶的哈希表弹出，
    将弹出的哈希表中的原子数量与 num 相乘，加到上一层的原子数量中
    遍历结束后，栈顶的哈希表即为化学式中的原子及其个数。遍历哈希表，取出所有「原子-个数」对加入数组中，
    对数组按照原子字典序排序，然后遍历数组，按题目要求拼接成答案。
     */
    int i, n;
    String formula;

    public String countOfAtoms(String formula) {
        this.i = 0;
        this.n = formula.length();
        this.formula = formula;

        Deque<Map<String, Integer>> stack = new LinkedList<Map<String, Integer>>();
        stack.push(new HashMap<String, Integer>());
        while (i < n) {
            char ch = formula.charAt(i);
            if (ch == '(') {
                i++;
                stack.push(new HashMap<String, Integer>()); // 将一个空的哈希表压入栈中，准备统计括号内的原子数量
            } else if (ch == ')') {
                i++;
                int num = parseNum(); // 括号右侧数字
                Map<String, Integer> popMap = stack.pop(); // 弹出括号内的原子数量
                Map<String, Integer> topMap = stack.peek();
                for (Map.Entry<String, Integer> entry : popMap.entrySet()) {
                    String atom = entry.getKey();
                    int v = entry.getValue();
                    topMap.put(atom, topMap.getOrDefault(atom, 0) + v * num);
                    // 将括号内的原子数量乘上 num，加到上一层的原子数量中
                }
            } else {
                String atom = parseAtom();
                int num = parseNum();
                Map<String, Integer> topMap = stack.peek();
                topMap.put(atom, topMap.getOrDefault(atom, 0) + num); // 统计原子数量
            }
        }

        Map<String, Integer> map = stack.pop();
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(map);

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            String atom = entry.getKey();
            int count = entry.getValue();
            sb.append(atom);
            if (count > 1) {
                sb.append(count);
            }
        }
        return sb.toString();
    }

    public String parseAtom() {
        StringBuffer sb = new StringBuffer();
        sb.append(formula.charAt(i++)); // 扫描首字母
        while (i < n && Character.isLowerCase(formula.charAt(i))) {
            sb.append(formula.charAt(i++)); // 扫描首字母后的小写字母
        }
        return sb.toString();
    }

    public int parseNum() {
        if (i == n || !Character.isDigit(formula.charAt(i))) {
            return 1; // 不是数字，视作 1
        }
        int num = 0;
        while (i < n && Character.isDigit(formula.charAt(i))) {
            num = num * 10 + formula.charAt(i++) - '0'; // 扫描数字
        }
        return num;
    }
    /*
    时间复杂度：O(n^2)其中 n是化学式formula 的长度。
    最坏情况下栈有 O(n)O(n) 层，每次出栈时需要更新 O(n)个原子的数量，因此遍历化学式的时间复杂度为 O(n^2)
    遍历结束后排序的时间复杂度为 (nlogn)。
    因此总的时间复杂度为 为 O(n^2+ n * logn)=O(n^2)
    空间复杂度：O(n)。
    空间复杂度取决于栈中所有哈希表中的元素个数之和，而这不会超过化学式formula 的长度，因此空间复杂度为 O(n)
     */
}
