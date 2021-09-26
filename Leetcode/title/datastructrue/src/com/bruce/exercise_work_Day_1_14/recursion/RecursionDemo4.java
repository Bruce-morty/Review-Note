package com.bruce.exercise_work_Day_1_14.recursion;

import java.util.Scanner;

/**
 * Author: bruce
 * Date:2020/4/8
 * Version:1.0.0
 */
/*
约瑟夫环：
	N个人站成一个环, 每隔一个人杀掉一个人, 请问最终剩下编号为几的人？

1	1
2	1
3	3
4	1
5	3
6	5
7	7
8	1
9	3
10	5
...


边界条件：
	f(1) == 1
n是偶数 n = 2k：
	f(2k) = 2 * f(k) - 1;
n是奇数 n = 2k + 1
	f(2k+1) = 2 * f(k) + 1;

*/
public class RecursionDemo4 {

    public static void main(String[] args) {
        System.out.println("请输入人数N：");
        Scanner scanner = new Scanner(System.in);
        int nextInt = scanner.nextInt();
        System.out.println("最终存活的是：" + f(nextInt));
    }

    private static int f(int nextInt) {
        if (nextInt == 1) return 1;
        if ((nextInt & 1) == 0) {
            return 2 * f(nextInt / 2) - 1;
        } else {
            return 2 * f(nextInt / 2) + 1;
        }
    }
}
