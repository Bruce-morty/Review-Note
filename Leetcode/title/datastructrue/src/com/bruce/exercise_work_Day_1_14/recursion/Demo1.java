package com.bruce.exercise_work_Day_1_14.recursion;

import java.util.Scanner;

/**
 * Author: bruce
 * Date:2020/3/10
 * Version:1.0.0
 */
public class Demo1 {
    //fibonaci数列
    public static void main(String[] args) {
        //有一对兔子，从出生后第三个月开始每月生一对兔子，
        // 小兔子从第三个月开始每月也生一对兔子，假如是不死神兔，那么第20个月一共生多少对兔子？
        Scanner scanner = new Scanner(System.in);
        Integer s = Integer.valueOf(scanner.nextLine());
        int num = fibo(s);
        System.out.println(num);
    }

    private static int fibo(int s) {
        /*
        1 1
        2 1
        3 2
        4 3
        5 5
        6 8
        7 13
         */
        if (s <= 2) {
            return 1;
        }
        return fibo(s -1 ) + fibo(s - 2);
    }
}
