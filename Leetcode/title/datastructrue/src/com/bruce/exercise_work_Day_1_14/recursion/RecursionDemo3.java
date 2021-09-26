package com.bruce.exercise_work_Day_1_14.recursion;

import java.util.Scanner;

/**
 * Author: bruce
 * Date:2020/4/8
 * Version:1.0.0
 */

/*
n条直线最多能把平面分成多少个部分？

1	2
2	4
3	7
4	11
...
边界条件
	f(1) = 2
递推关系：
	f(n) = f(n-1) + n;
		= f(n-2) + (n-1) + n
		= f(n-3) + (n-2) + (n-1) + n
		= f(1) + 2 +... + n
		=n(n+1)/2 + 1

扩展:
n条折线最多能把平面分成多少部分？
1	2
2	7
...
把一条折现延长变成两条直线，则多出两个平面
g(n) = f(2n) - 2n;
*/
public class RecursionDemo3 {
    public static void main(String[] args) {
        System.out.println("请输入直线的个数：");
        Scanner scanner = new Scanner(System.in);
        int nextInt = scanner.nextInt();
        System.out.println(nextInt + "条直线最多可以把平面分成" + split(nextInt) + "份");
    }

    private static int split(int nextInt) {
        /*if (nextInt == 1) return 2;
        return split(nextInt - 1) + nextInt;*/
        // 通过通项公式
        return nextInt * (nextInt + 1) / 2 + 1;
    }
}
