package com.bruce.amazon;

import java.util.Collections;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/8/27
 * Version:1.0.0
 */
/*

    return the number of players who can level up after this round

    example:
    k: an integer denoting the cutoff rank for leveling up a player's character
    n = 4, k = 3, scores = [100, 50, 50, 25]   --> rank = 1, 2, 2, 4
 */
public class AmazonOA2_2 {

    public static int levelUp(int k, List<Integer> score) {
        // need to sort the list
        if (k <= 0) return 0;
        int rank = 1;
        int res = 0;
        Collections.sort(score, Collections.reverseOrder());
        for (int i = 0; i < score.size(); i++) {
            // if i == 0 -> rank = 1
            if (i == 0) rank = 1;
            else if (score.get(i) != score.get(i - 1)) {
                // 有两个或多个相同的分数
                rank += 1;
            }
            if (k >= res && score.get(i) > 0) {
                res++;
            }else {
                break;
            }
        }
        return res;
    }
}
