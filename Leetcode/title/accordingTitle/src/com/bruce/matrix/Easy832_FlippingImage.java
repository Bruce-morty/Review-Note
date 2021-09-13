package com.bruce.matrix;

/**
 * Author: Qi Gao
 * Date:2021/7/5
 * Version:1.0.0
 */
/*
给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]
反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]
示例 1：

输入：[[1,1,0],[1,0,1],[0,0,0]]
输出：[[1,0,0],[0,1,0],[1,1,1]]
解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 */
public class Easy832_FlippingImage {
    public int[][] flipAndInvertImage(int[][] image) {
        // 反转行的时候可以顺便判断这个值 然后进行图片翻转
        int row = image.length;
        int col = image.length;
        int[][] res = new int[row][col];
        for (int i = 0; i <row; i++) {
            for (int j = col - 1; j >= 0; j--) {
                // 反转图片
                if (image[i][j] == 0) {
                    res[i][col - j - 1] = 1;
                }
                if (image[i][j] == 1) {
                    res[i][col - j - 1] = 0;
                }
            }
        }
        return res;

        // left + right = n - 1 根据规律 若arr[i][left] == arr[i][right] 则反转元素 逆序了
        // 直接在原数组上操作

        // for (int i = 0; i < n; i++) {
        //     int left = 0;
        //     int right = n - 1;
        //     while(left < right){
        //         if (A[i][left] == A[i][right]) {
        //            A[i][left] ^= 1;
        //            A[i][right] ^= 1;
        //         }
        //         left++;
        //         right--;
        //     }
        //     if (left == right) {
        //         A[i][left] ^= 1;
        //     }
        // }
        // return A;
    }
}
