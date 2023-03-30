package com.kuaka.cn.algorithm.sort;

/**
 * @author liqiang.chen
 * @time 2023/3/29 13:39
 **/
public class MergeSortEG {
    /**
     * 给定一个数组，求数组中每个元素中左边元素比自己小的和的总和
     * eg:
     * arr[ 7, 1, 4, 2, 8 ]
     * 左边比7小的数的和：0
     * 左边比1小的数的和：0
     * 左边比4小的数的和：1
     * 左边比2小的数的和：1
     * 左边比8小的数的和：7 + 1 + 4 + 2 = 14
     * 返回结果为 0 + 0 + 1 + 1 + 14 = 16
     **/
    public static int eg1(int[] source) {
        if (source == null || source.length < 2) {
            return source == null || source.length == 0 ? 0 : source[0];
        }

        int sum = 0;
        int length = source.length;
        int mergeSize = 1;
        while (mergeSize < length) {
            int left = 0;
            while (left < length) {
                int mid = left + mergeSize - 1;

                if (mid >= length) {
                    break;
                }

                int right = Math.min(mid + mergeSize, length -1);
                sum += merge(source, left, mid, right);
                left = right + 1;
            }

            if (mergeSize > length / 2) {
                break;
            }

            mergeSize <<= 1;
        }

        return sum;
    }

    private static int merge(int[] source, int left, int mid, int right) {
        int[] help = new int[right -left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int id = 0;
        int sum = 0;
        while (p1 <= mid && p2 <= right) {
            if (source[p1] < source[p2]) {
                sum += (right - p2 + 1) * source[p1];
                help[id++] = source[p1++];
            } else {
                help[id++] = source[p2++];
            }
        }

        while (p1 <= mid) {
            help[id++] = source[p1++];
        }

        while (p2 <= right) {
            help[id++] = source[p2++];
        }

        if (id > 0) System.arraycopy(help, 0, source, left, id);

        return sum;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 1, 6, 8, 10, 3, 5, 4, 1 };
        int sum = eg1(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
        System.out.println(sum);
    }
}
