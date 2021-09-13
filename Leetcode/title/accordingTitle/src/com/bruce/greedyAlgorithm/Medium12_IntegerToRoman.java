package com.bruce.greedyAlgorithm;

/**
 * Author: Qi Gao
 * Date:2021/8/31
 * Version:1.0.0
 */
/*
12. 整数转罗马数字
罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II
通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV
数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX
这个特殊的规则只适用于以下六种情况：
I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。给你一个整数，将其转为罗马数字
示例 1:
输入: num = 3
输出: "III"
示例 2:
输入: num = 4
输出: "IV"
示例 3:
输入: num = 9
输出: "IX"
示例 4:
输入: num = 58
输出: "LVIII"
解释: L = 50, V = 5, III = 3.
 */
public class Medium12_IntegerToRoman {

    /*
    我们可以用贪心的思路去做，尽可能用当前数 - 大的数字 得到的结果所用的字符可能最少
    可以贪心与可选硬币(纸币)的面值有关 测试用例1800: 返回答案是 MDCCC 而不是CMCM 说明它并不是要求最少字符
    这个算法的宗旨就是消耗 尽可能消耗最大面额数值
     */
    public String intToRoman(int num) {
        // 构建阿拉伯数字与罗马数字的转换
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        // 重点 怎么去遍历，遍历数组长度判断当前数字是否能取到1000，900 ...
        for (int i = 0; i < 13; i++) {
            int val = values[i];
            // 能取几个
            while (num >= val) {
                num -= val;
                sb.append(romans[i]);
            }
            if (num == 0) break;
        }
        return sb.toString();
    }
    /*
    复杂度分析：

    时间复杂度：O(logN)，这里讨论的是一般情况，忽略题目中测试数据范围的限制。
    输入整数的位数，决定了循环的次数，所以复杂度为O(log10N)；
    空间复杂度：O(logN)，阿拉伯数字与罗马数字的对应关系也取决于输入数字的位数。
     */
}
