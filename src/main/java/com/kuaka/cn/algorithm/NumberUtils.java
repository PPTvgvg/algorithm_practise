package com.kuaka.cn.algorithm;

/**
 * @author liqiang.chen
 * @time 2023/3/16 14:49
 **/
public class NumberUtils {
    /**
     * 随机返回一个奇数或偶数
     * @param min 随机数最大值
     * @param max 随机数最小值
     * @param type 1代表返回奇数，0代表返回偶数
     * @return int
     */
    public static int getEvenOrOdd(int min, int max, int type) {
        int number;
        do {
            number = random(min, max);
        } while (!((number & 1) == type));

        return number;
    }

    /**
     * 返回一个随机数
     * @param min 随机数最大值
     * @param max 随机数最小值
     * @return int
     */
    public static int random(int min, int max) {
        return (int)(Math.random()*max + min);
    }
}
