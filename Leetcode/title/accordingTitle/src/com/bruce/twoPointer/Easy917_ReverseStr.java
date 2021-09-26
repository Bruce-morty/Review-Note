package com.bruce.twoPointer;

/**
 * Author: Qi Gao
 * Date:2021/9/14
 * Version:1.0.0
 */
/*
917. 仅仅反转字母
给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
示例 1：
输入："ab-cd"
输出："dc-ba"
示例 2：
输入："a-bC-dEf-ghIj"
输出："j-Ih-gfE-dCba"
示例 3：
输入："Test1ng-Leet=code-Q!"
输出："Qedo1ct-eeLg=ntse-T!"

 */
public class Easy917_ReverseStr {

    /*
    好好思考用双指针怎么移动！！ 判断当前元素之后 再判断右指针 是letter的话就可以添加 添加之后移动右指针
     */
    public String reverseOnlyLetters(String s) {
        int len = s.length();
        int right = len - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            // move right
            if (Character.isLetter(s.charAt(i))) {
                while(!Character.isLetter(s.charAt(right))) {
                    right--;
                }
                sb.append(s.charAt(right--));
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /*
    time complexity: O(n)
    Space complexity: O(n) 创建了新的数组
     */
    public String reverseOnlyLetters2(String s) {

        char[] str = s.toCharArray();
        int low = 0, high = str.length-1;
        while(low < high){
            if(Character.isLetter(str[low]) && Character.isLetter(str[high])){
                char temp = str[low];
                str[low] = str[high];
                str[high] = temp;
                low++;
                high--;
            }else if(!Character.isLetter(str[low])){
                low++;
            }else if(!Character.isLetter(str[high])){
                high--;
            }
        }

        return String.valueOf(str);
    }
}
