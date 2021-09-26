package com.bruce.exercise_work_Day_1_14.recursion;

/**
 * Author: bruce
 * Date:2020/4/8
 * Version:1.0.0
 */
/*
举例：
10.1你和你女朋友去电影院看电影, 电影院乌漆麻黑, 伸手不见五指。这时候, 你女朋友咬着你耳朵问你：我们在第几排？
这时候你可以踹你前面的哥们：哥们，你在第几排？
前面的哥们就踹他前面的哥们：哥们，你在第几排？
...
第二排的哥们就踹他前面的哥们：哥们，你在第几排？
第一排的哥们：我在第一排。

递：问题往前传递的过程。
归：解答往回传递个过程。

两个明确：
	a. 边界条件
	b. 递推公式

int f(int n);
*/

public class Demo2 {
    public static void main(String[] args) {
        int f = f(7);
    }

    public static int f(int n) {
        if (n == 1) return 1;
        return f(n - 1) + 1;
    }
}
