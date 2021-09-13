package com.bruce.matrix;

/**
 * Author: Qi Gao
 * Date:2021/8/20
 * Version:1.0.0
 */
/*
36. 有效的数独
请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
数独部分空格内已填入了数字，空白格用 '.' 表示。
注意：
一个有效的数独（部分已被填充）不一定是可解的。
只需要根据以上规则，验证已经填入的数字是否有效即可。
board.length == 9
board[i].length == 9
board[i][j] 是一位数字或者 '.'
 */
public class Medium36_Sudoka {
    /*
    重点：判断box，将box分成三列 举例 board[4][7] 属于第五个box 说明位置取决于纵坐标
    j / 3 = 2 第三列box + （i / 3） * 3 得到第几个盒子
    用数组或者哈希表存每个位置的信息
     */
    public boolean isValidSudoku(char[][] board) {
        // 存每行 每列 每个box位置信息 第二个维度有10是为了让9这个下标不会 out of index
        int[][] row = new int[9][10];
        int[][] col = new int[9][10];
        int[][] box = new int[9][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 判断是否为。
                if (board[i][j] == '.') continue;
                // 得到这个位置值 判断 行 列 box是否已有
                int currNum = board[i][j] - '0';
                if (row[i][currNum] == 1) return false;
                if (col[j][currNum] == 1) return false;
                if (box[j / 3 + (i/3) * 3][currNum] == 1) return false;
                // 把board 里的数作为 数组的第二个维度，把这个数作为key 若后面在同样的行列出现 就会在前面return false
                row[i][currNum] = 1;
                col[j][currNum] = 1;
                box[j/3 + (i/3) * 3][currNum] = 1;
            }
        }
        return true;
    }
}
