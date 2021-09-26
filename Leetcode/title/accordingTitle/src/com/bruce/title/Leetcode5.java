package com.bruce.title;

/**
 * Author: bruce
 * Date:2020/10/24
 * Version:1.0.0
 */
public class Leetcode5 {
    // 最长回文字串
    public static String longestPalindrmoe(String s) {
        // 动态规划：1.无后效性；2.状态转移
        // 状态转移 : dp[i][j] = (s[i] == s[j]) && dp[i + 1][j - 1]   在这个式子中 s[i...j]是最左和最右的元素
        // 则可以知道 i 是小于等于 j 遍历从j = 1开始 控制字符串长度.i是小于 j - 1的. i == j 同一个字符已经是true了
        // 填表(二维数组)：将每个子串的状态存储，判断更长字符的时候可以直接用
        // 边界条件：当长度为1的字符全部是回文 j - 1 - (i + 1) + 1< 2 --> j - i < 3
        // 要记录回文的最长字符位置，即每次更新了之后判断当前长度和之前的回文长度大小
        int begin = 0;
        int maxLen = 1;
        int len = s.length();
        if (len < 2) {
            return s;
        }
        boolean[][] dp = new boolean[len][len];
        // 长度为1的全部为true
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        // 控制当前字符串的长度 i <= j
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                /*if (s.charAt(i) == s.charAt(j)) {
                    // 判断边界
                    if (j - i < 3) {
                        // s.(i) == s.(j) 中间只剩一个元素了
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }*/
                 /*  初始化已经是false了，不用管。else {
                    dp[i][j] = false;
                    }
                    if (dp[i][j] && j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        begin = i;
                    }
                */
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

                if (dp[i][j]) {
                    int currLen = j - i + 1;
                    if (currLen > maxLen) {
                        maxLen = currLen;
                        begin = i;
                    }
                }
            }
        }
        return s.substring(begin, maxLen + begin);
    }

    /*
    DP 是暴力枚举了所有情况，中心扩散需要判断长度是奇数还是偶数
     */
    public static String longest(String s) {
        //知道奇偶长度的情况, 中心扩散算法
        int len = s.length();
        if (len < 2) return s;
        int maxLen = 1;
        String result = s.substring(0, 1);
        for (int i = 0; i < len - 1; i++) {
            String oddStr = centerSpread(s, i, i);
            String evenStr = centerSpread(s, i, i + 1);
            String maxLenStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;
            if (maxLenStr.length() > maxLen) {
                maxLen = maxLenStr.length();
                result = maxLenStr;
            }
        }
        return result;
    }

    private static String centerSpread(String s, int left, int right) {
        // 中心扩散，left == right 的时候没用字符，就是回文
        // right = left + 1 的时候此时回文中心是一个空隙，回文串的长度是偶数
        int len = s.length();
        int i = left;
        int j = right;
        while (i >= 0 && j < len) {
            if (s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            } else {
                break;
            }
        }
        // i 和 j 是回文串的初始位置和末尾的索引，
        // 由于退出循环的时候 s.charAt(i) != s.charAt(j)， 包左 不包右 j不动
        return s.substring(i+ 1, j);
    }

    public static void main(String[] args) {
        String s = "abbabccbab";
//        String longest = longest(s);
        String longest = longestPalindrmoe(s);
        System.out.println(longest);
    }
    // 中心扩散
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        String str = addBoundaries(s, '#');
        int sLen = 2 * len + 1;
        int maxLen = 1;

        int start = 0;
        for (int i = 0; i < sLen; i++) {
            int curLen = centerSpread(str, i);
            if (curLen > maxLen) {
                maxLen = curLen;
                start = (i - maxLen) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    private int centerSpread(String s, int center) {
        // left = right 的时候，此时回文中心是一个空隙，回文串的长度是奇数
        // right = left + 1 的时候，此时回文中心是任意一个字符，回文串的长度是偶数
        int len = s.length();
        int i = center - 1;
        int j = center + 1;
        int step = 0;
        while (i >= 0 && j < len && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
            step++;
        }
        return step;
    }


    /**
     * 创建预处理字符串
     *
     * @param s      原始字符串
     * @param divide 分隔字符
     * @return 使用分隔字符处理以后得到的字符串
     */
    private String addBoundaries(String s, char divide) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        if (s.indexOf(divide) != -1) {
            throw new IllegalArgumentException("参数错误，您传递的分割字符，在输入字符串中存在！");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(divide);
            stringBuilder.append(s.charAt(i));
        }
        stringBuilder.append(divide);
        return stringBuilder.toString();
    }

    public String longestPalindromeDPOptim(String s) {
        int len = s.length();
        if(len < 2) return s;
        int begin = 0, max = 1;

        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        //这里将单字符的判断放到主循环中了，即更改了j和i的取值，使其能判断到单字符
        for(int j = 0; j < len; j++) {
            for(int i = 0; i <= j; i++) {
                if(charArray[i] == charArray[j]) {		// 不用对dp数组填充false
                    if(j - i < 3) dp[i][j] = true;
                    else dp[i][j] = dp[i + 1][j - 1];
                }

                if(dp[i][j] && j - i + 1 > max) {
                    begin = i;
                    max = j - i + 1;
                }
            }
        }
        return s.substring(begin, begin + max);
    }
}
