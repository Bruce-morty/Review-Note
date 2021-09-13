package com.bruce.twoPointer;

/**
 * Author: Qi Gao
 * Date:2021/8/21
 * Version:1.0.0
 */
/*
443. 压缩字符串
给你一个字符数组 chars ，请使用下述算法压缩：

从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：

如果这一组长度为 1 ，则将字符追加到 s 中。
否则，需要向 s 追加字符，后跟这一组的长度。
压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，
如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
请在 修改完输入数组后 ，返回该数组的新长度。
你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
示例 1：
输入：chars = ["a","a","b","b","c","c","c"]
输出：返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
解释：
"aa" 被 "a2" 替代。"bb" 被 "b2" 替代。"ccc" 被 "c3" 替代。
示例 2：

输入：chars = ["a"]
输出：返回 1 ，输入数组的前 1 个字符应该是：["a"]
解释：
没有任何字符串被替代。
示例 3：

输入：chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
输出：返回 4 ，输入数组的前 4 个字符应该是：["a","b","1","2"]。
解释：
由于字符 "a" 不重复，所以不会被压缩。"bbbbbbbbbbbb" 被 “b12” 替代。
注意每个数字在数组中都有它自己的位置。
 */
public class Medium443_CompressStr {

    /*
    两种方法: 思考第一个为什么right 可以 == len
     */
    public int compress(char[] chars) {
        // 先对数组排序 1.用两个个指针往后遍历，后一个是前面不相等的元素 且index就是数量
        // left与right判断字符是否相等：
        // 1.相等的话left 不动 right往后移动 直到遇见不相等的元素， 2. 不相等
        // 判断right - left不小于1的话 把left后面添加1 // 再用for循环把len加上
        // 把right的值放到left后面
        int len = chars.length;
        if (len < 2) return len;
        int left = 0;
        int right = 0;
        int size = 0;
        // right是找到不相等元素位置 所以遍历到尾肯定会出界
        /* while (right <= len) {
             if (right == len || chars[right] != chars[left]) {
                 chars[size++] = chars[left];
                 if (right - left > 1) {
                     for (char c : String.valueOf(right - left).toCharArray()) {
                         chars[size++] = c;
                     }
                 }
                 left = right;
             }
             right++;
         }
         return size;*/
        while (right < len) {
            // 定义tmp计算长度 因为left是存放元素的 right - tmp才是这个重复元素的长度
            int tmp = right;
            while (right < len && chars[tmp] == chars[right]) {
                right++;
            }
            // 判断right - tmp
            int tempLen = right - tmp;
            // 先赋值之后 left再++ 因为tmp相同的元素起点
            chars[left] = chars[tmp];
            left++;
            if (tempLen == 1) continue;
            // 大于1 left++
            String s = String.valueOf(tempLen);
            for (int i = 0; i < s.length(); i++) {
                chars[left] = s.charAt(i);
                left++;
            }
        }
        return left;
    }
}
