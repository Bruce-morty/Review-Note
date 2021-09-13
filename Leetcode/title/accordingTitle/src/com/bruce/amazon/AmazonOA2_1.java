package com.bruce.amazon;

import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/8/27
 * Version:1.0.0
 */
/*
Throttling Gateway:
    There has the following limits:
        The number of transactions in any given second cannot exceed 3
        每10秒 不超过20个request
        一分钟不超过60个request
 */
public class AmazonOA2_1 {
    public static int droppedRequest(List<Integer> requestTime) {
        int ans = 0;
        for (int i = 0; i < requestTime.size(); i++) {
            if (i > 2 && requestTime.get(i) == requestTime.get(i - 3)) {
                ans++;
                // 数组的长度为请求个数 若在20个范围内 里面的请求时间差值 小于 10 说明要drop
                // 因为时间小于等于10以内 只能有20个request
            } else if (i > 19 && (requestTime.get(i) - requestTime.get(i - 20) < 10)) {
                ans++;
                // 1 mins similar to above
            } else if (i > 59 && (requestTime.get(i) - requestTime.get(i - 60) < 60)) {
                ans++;
            }
        }
        return ans;
    }
}