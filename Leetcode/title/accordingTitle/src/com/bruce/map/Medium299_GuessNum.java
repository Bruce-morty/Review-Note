package com.bruce.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Qi Gao
 * Date:2021/9/18
 * Version:1.0.0
 */
/*
299. 猜数字游戏
你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：

你写出一个秘密数字，并请朋友猜这个数字是多少。
朋友每猜测一次，你就会给他一个提示，告诉他的猜测数字中有多少位属于数字和确切位置都猜对了（称为“Bulls”, 公牛），
有多少位属于数字猜对了但是位置不对（称为“Cows”, 奶牛）。
朋友根据提示继续猜，直到猜出秘密数字。
请写出一个根据秘密数字和朋友的猜测数返回提示的函数，返回字符串的格式为 xAyB ，x 和 y 都是数字，A 表示公牛，用 B 表示奶牛
xA 表示有 x 位数字出现在秘密数字中，且位置都与秘密数字一致。
yB 表示有 y 位数字出现在秘密数字中，但位置与秘密数字不一致。
请注意秘密数字和朋友的猜测数都可能含有重复数字，每位数字只能统计一次
示例 1:

输入: secret = "1807", guess = "7810"
输出: "1A3B"
解释: 1 公牛和 3 奶牛。公牛是 8，奶牛是 0, 1 和 7。
 */
public class Medium299_GuessNum {

    /*
    就是出现相同位置的char 记录为x 不同位置的且在guess出现的store 成y
    */
    public String getHint(String secret, String guess) {
        // 0. 首先创建map 记录bull出现的wrong position次数 1. 若出现在正确位置 把map中的这个次数 - 1
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> guessMap = new HashMap<>();
        int len = secret.length();
        int x = 0;
        int y = 0;
        for (int i = 0; i <len; i++) {
            char c1 = secret.charAt(i);
            char c2 = guess.charAt(i);
            if (c1 == c2) {
                // 若相同位置就不care 直接将x++ 不同位置就记录他们出现的次数 最后比较
                x++;
            }else {
                // map 存错误位置的character
                map.put(c1, map.getOrDefault(c1, 0) + 1);
                // 存guess里面的chara信息, 然后对比错误位置 找出guess里面有多少个是符合的y
                guessMap.put(c2, guessMap.getOrDefault(c2, 0) + 1);
            }
        }
        // 遍历两个map 找到y的信息
        // 找到secret map中有多少个还没被匹配的位置
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char c = entry.getKey();
            int res = entry.getValue();
            // guessmap res
            int v = 0;
            if (guessMap.containsKey(c)) {
                v = guessMap.get(c);
            }
            if (v > 0) {
                if (v < res) {
                    y += v;
                }else {
                    y += res;
                }
            }
        }
        // return string
        StringBuilder sb = new StringBuilder();
        sb.append(x).append("A").append(y).append("B");
        return sb.toString();
    }
}
